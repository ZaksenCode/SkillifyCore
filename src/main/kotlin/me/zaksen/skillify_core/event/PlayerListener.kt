package me.zaksen.skillify_core.event

import me.zaksen.skillify_core.api.item.CustomItemRegistry
import me.zaksen.skillify_core.api.recipe.RecipeRegistry
import me.zaksen.skillify_core.database.PlayerProfileRepository
import me.zaksen.skillify_core.database.PlayerProfileUuidSpecification
import me.zaksen.skillify_core.database.dao.PlayerProfile
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.player.PlayerLoginEvent
import java.sql.Timestamp
import java.time.ZonedDateTime

class PlayerListener(
    private val profileRepository: PlayerProfileRepository,
    private val itemRegistry: CustomItemRegistry,
    private val recipeRegistry: RecipeRegistry
): Listener {

    @EventHandler
    private fun processPlayerJoining(event: PlayerLoginEvent) {
        val playerProfile = profileRepository.query(PlayerProfileUuidSpecification(event.player.uniqueId))

        if(playerProfile.isEmpty()) {
            profileRepository.add(PlayerProfile(
                -1,
                event.player.uniqueId,
                event.player.name,
                Timestamp.from(ZonedDateTime.now().toInstant())
            ))
        }

        recipeRegistry.revealRecipes(event.player)
    }

    @EventHandler
    private fun processCustomItemUsage(event: PlayerInteractEvent) {
        val stack = event.item ?: return
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onUse(event)
    }

    @EventHandler
    private fun processCustomItemHitting(event: EntityDamageByEntityEvent) {
        val stack = if(event.damager is LivingEntity) {
            (event.damager as LivingEntity).activeItem
        } else {
            return
        }
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onHit(event)
    }

    @EventHandler
    private fun processCustomItemEntityUsage(event: PlayerInteractAtEntityEvent) {
        val stack = event.player.inventory.getItem(event.hand)
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onUseWithEntity(event)
    }

    @EventHandler
    private fun processCustomItemConsuming(event: PlayerItemConsumeEvent) {
        val stack = event.item
        val customItem = itemRegistry.getItem(stack) ?: return

        customItem.onConsume(event)
    }
}