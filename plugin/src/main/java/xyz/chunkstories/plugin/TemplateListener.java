package xyz.chunkstories.plugin;

import xyz.chunkstories.api.events.EventHandler;
import xyz.chunkstories.api.events.Listener;
import xyz.chunkstories.api.events.player.PlayerDeathEvent;
import xyz.chunkstories.api.server.Server;

public class TemplateListener implements Listener {
	final TemplatePlugin templatePlugin;
	
	public TemplateListener(TemplatePlugin templatePlugin) {
		this.templatePlugin = templatePlugin;
	}
	
	@EventHandler
	public void handlePlayerDeathMethodNameDoesntMatterOnlyTheArgumentsAndTheAnnotation(PlayerDeathEvent event) {
		if( templatePlugin.getGameContext() instanceof Server ) {
			((Server)templatePlugin.getGameContext()).broadcastMessage("RIP, "+event.getPlayer().getDisplayName());
		}
	}
}
