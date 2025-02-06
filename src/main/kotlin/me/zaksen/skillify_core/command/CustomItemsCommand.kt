package me.zaksen.skillify_core.command

import me.zaksen.skillify_core.api.item.CustomItemRegistry
import me.zaksen.skillify_core.api.util.extensions.toNamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class CustomItemsCommand(
    private val registry: CustomItemRegistry
): TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String> {
        if(args.isNullOrEmpty()) {
            return mutableListOf()
        }

        return when(args.size) {
            1 -> mutableListOf("give")
            2 -> {
                val result = mutableListOf<String>()

                registry.getRegisteredItems().forEach {
                    result.add(it.key.toString())
                }

                result.sortBy {
                    it.compareTo(args[0])
                }

                result
            }
            else -> mutableListOf()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(!sender.hasPermission("command.skillify_core.custom_items")) {
            // TODO - send message -> no permission
            return true
        }

        if(args.isNullOrEmpty()) {
            // TODO - send message -> no subcommand
            return true
        }

        if(sender !is Player) {
            // TODO - send message -> command only for player
            return true
        }

        val subcommand = args[0]

        when(subcommand) {
            "tab" -> {
                // TODO - Add pageable tab menu for items.
            }
            "give" -> {
                if(args.size < 2) {
                    return true
                }

                val itemId = try {
                    args[1].toNamespacedKey()
                } catch (_: Exception) {
                    return true
                }

                val stack = registry.getStack(itemId)

                if(stack == null) {
                    // TODO - send message -> unknown item
                    return true
                }

                sender.inventory.addItem(stack)
            }
        }

        return true
    }
}