package xyz.chunkstories.plugin;

import io.xol.chunkstories.api.events.EventHandler;
import io.xol.chunkstories.api.events.Listener;
import io.xol.chunkstories.api.events.player.PlayerDeathEvent;
import io.xol.chunkstories.api.server.ServerInterface;

public class TemplateListener implements Listener {
	final TemplatePlugin templatePlugin;
	
	public TemplateListener(TemplatePlugin templatePlugin) {
		this.templatePlugin = templatePlugin;
	}
	
	@EventHandler
	public void handlePlayerDeathMethodNameDoesntMatterOnlyTheArgumentsAndTheAnnotation(PlayerDeathEvent event) {
		if( templatePlugin.getPluginExecutionContext() instanceof ServerInterface ) {
			((ServerInterface)templatePlugin.getPluginExecutionContext()).broadcastMessage("RIP, "+event.getPlayer().getDisplayName());
		}
	}
}
