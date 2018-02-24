package xyz.chunkstories.plugin;

import io.xol.chunkstories.api.GameContext;
import io.xol.chunkstories.api.plugin.ChunkStoriesPlugin;
import io.xol.chunkstories.api.plugin.PluginInformation;

public class TemplatePlugin extends ChunkStoriesPlugin {

	public TemplatePlugin(PluginInformation pluginInformation, GameContext pluginExecutionContext) {
		super(pluginInformation, pluginExecutionContext);
	}

	@Override
	public void onEnable() {
		this.pluginExecutionContext.logger().info("Enabled template plugin");
		
		this.getPluginManager().registerCommandHandler("template", new TemplateCommandsHandler(this));
		
		this.getPluginManager().registerEventListener(new TemplateListener(this), this);
	}

	@Override
	public void onDisable() {
		this.pluginExecutionContext.logger().info("Disabled template plugin");
	}

}
