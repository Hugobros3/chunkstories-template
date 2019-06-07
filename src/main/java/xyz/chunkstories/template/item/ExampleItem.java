package xyz.chunkstories.template.item;

import xyz.chunkstories.api.entity.Controller;
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
		
		customProperty = definition.resolveProperty("customProperty", "no imagination");
	}

	@Override
	public String getName() {
		return super.getDefinition().store().parent().localization().getLocalizedString("template.example");
	}

	@Override
	public boolean onControllerInput(Entity owner, ItemPile itemPile, Input input, Controller controller) {
		if(input.getName().equals("mouse.left") && controller instanceof Player) {
			((Player)controller).sendMessage(customProperty);
			return true;
		}
		
		return false;
	}

}
