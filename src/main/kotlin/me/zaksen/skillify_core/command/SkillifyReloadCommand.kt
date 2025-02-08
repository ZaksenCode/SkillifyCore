package me.zaksen.skillify_core.command

import me.zaksen.skillify_core.SkillifyCore
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class SkillifyReloadCommand(
    private val skillifyCore: SkillifyCore
): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("command.skillify_core.reload")) {
            return true
        }

        skillifyCore.reload()

        return true
    }
}