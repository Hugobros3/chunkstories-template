package xyz.chunkstories.template.block;

import xyz.chunkstories.api.block.BlockType;
import xyz.chunkstories.api.content.Content;
import xyz.chunkstories.api.content.json.Json;
import xyz.chunkstories.api.world.chunk.MutableChunkCell;

public class ExampleBlockType extends BlockType {

	public ExampleBlockType(String name, Json.Dict definition, Content content) {
		super(name, definition, content);
	}

	@Override
	public void whenPlaced(MutableChunkCell cell) {
		cell.getWorld().getSoundManager().playSoundEffect("sounds/wow.ogg");
		super.whenPlaced(cell);
	}
}
