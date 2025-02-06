package me.zaksen.skillify_core.api.item

import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

/**
 * A class for your own items.
 * Do not use variables inside the class, as they are one class used for all items of a certain type.
 * This class is essentially a set of event handlers for a particular item.
 */
abstract class CustomItem {
    /**
     * A method that will be called when the player interacts with this item type.
     * @param event The object of the event class that triggered this action.
     * @see PlayerInteractEvent
     */
    open fun onUse(event: PlayerInteractEvent) {}
    /**
     * A method that is called when you interact with the entity with the given object.
     * @param event The object of the event class that triggered this action.
     * @see PlayerInteractAtEntityEvent
     */
    open fun onUseWithEntity(event: PlayerInteractAtEntityEvent) {}
    /**
     * The method will be called when the player consume the given item (spends it).
     * @param event The object of the event class that triggered this action.
     * @see PlayerItemConsumeEvent
     */
    open fun onConsume(event: PlayerItemConsumeEvent) {}
    /**
     * The method will be invoked when the creature hits any creature with the given item.
     * @param event The object of the event class that triggered this action.
     * @see EntityDamageByEntityEvent
     */
    open fun onHit(event: EntityDamageByEntityEvent) {}
}