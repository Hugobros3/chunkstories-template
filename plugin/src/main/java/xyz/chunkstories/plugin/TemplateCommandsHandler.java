package xyz.chunkstories.plugin;

import io.xol.chunkstories.api.plugin.commands.Command;
import io.xol.chunkstories.api.plugin.commands.CommandEmitter;
import io.xol.chunkstories.api.plugin.commands.CommandHandler;

public class TemplateCommandsHandler implements CommandHandler {

	final TemplatePlugin templatePlugin;
	
	public TemplateCommandsHandler(TemplatePlugin templatePlugin) {
		this.templatePlugin = templatePlugin;
	}

	@Override
	public boolean handleCommand(CommandEmitter emitter, Command command, String[] arguments) {
		emitter.sendMessage("Hello World!");
		return true;
	}

}
