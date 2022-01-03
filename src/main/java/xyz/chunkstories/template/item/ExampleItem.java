package xyz.chunkstories.template.item;

import xyz.chunkstories.api.content.json.JsonKt;
import xyz.chunkstories.api.entity.Entity;
import xyz.chunkstories.api.input.Input;
import xyz.chunkstories.api.item.Item;
import xyz.chunkstories.api.item.ItemDefinition;
import xyz.chunkstories.api.item.inventory.ItemPile;
import xyz.chunkstories.api.player.Player;

public class ExampleItem extends Item {

	private final String customProperty;
	
	public ExampleItem(ItemDefinition definition) {
		super(definition);

		// TODO the new Kotlin API makes this significantly less ergonomic. Do we care to fix it ?
		String s = JsonKt.getAsString(definition.getProperties().get("customProperty"));
		if (s == null)
			s = "no imagination";
		customProperty = s;
	}

	@Override
	public boolean onPlayerInput(Entity owner, ItemPile itemPile, Input input, Player player) {
		if(input.getName().equals("mouse.left")) {
			player.sendMessage(customProperty);
			return true;
		}
		
		return false;
	}

}
