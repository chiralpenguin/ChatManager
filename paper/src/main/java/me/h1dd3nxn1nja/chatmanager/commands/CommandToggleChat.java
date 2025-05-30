package me.h1dd3nxn1nja.chatmanager.commands;

import com.ryderbelserion.chatmanager.enums.Messages;
import me.h1dd3nxn1nja.chatmanager.ChatManager;
import com.ryderbelserion.chatmanager.enums.Permissions;
import me.h1dd3nxn1nja.chatmanager.Methods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandToggleChat implements CommandExecutor {

	@NotNull
	private final ChatManager plugin = ChatManager.get();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if (!(sender instanceof Player player)) {
			Methods.sendMessage(sender, "&cError: You can only use that command in-game", true);

			return true;
		}

		if (!cmd.getName().equalsIgnoreCase("togglechat")) return true;

		if (!player.hasPermission(Permissions.TOGGLE_CHAT.getNode())) {
			Messages.NO_PERMISSION.sendMessage(player);

			return true;
		}

		if (args.length == 0) {
			if (this.plugin.api().getToggleChatData().containsUser(player.getUniqueId())) {
				this.plugin.api().getToggleChatData().removeUser(player.getUniqueId());

				Messages.TOGGLE_CHAT_DISABLED.sendMessage(player);

				return true;
			}

			this.plugin.api().getToggleChatData().addUser(player.getUniqueId());

			Messages.TOGGLE_CHAT_ENABLED.sendMessage(player);

			return true;
		}

		Messages.INVALID_USAGE.sendMessage(player, "{usage}", "/togglechat");

		return true;
	}
}