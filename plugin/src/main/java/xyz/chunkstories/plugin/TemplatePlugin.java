package xyz.chunkstories.plugin;

import xyz.chunkstories.api.GameContext;
import xyz.chunkstories.api.plugin.ChunkStoriesPlugin;
import xyz.chunkstories.api.plugin.PluginInformation;

public class TemplatePlugin extends ChunkStoriesPlugin {

	public TemplatePlugin(PluginInformation pluginInformation, GameContext pluginExecutionContext) {
		super(pluginInformation, pluginExecutionContext);
	}

	@Override
	public void onEnable() {
		this.getGameContext().logger().info("Enabled template plugin");
		
		this.getPluginManager().registerCommand("template", new TemplateCommandsHandler(this));
		
		this.getPluginManager().registerEventListener(new TemplateListener(this), this);
	}

	@Override
	public void onDisable() {
		this.getGameContext().logger().info("Disabled template plugin");
	}

}
