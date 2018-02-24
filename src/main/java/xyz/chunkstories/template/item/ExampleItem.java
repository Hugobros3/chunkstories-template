package xyz.chunkstories.template.item;

import io.xol.chunkstories.api.entity.Controller;
import io.xol.chunkstories.api.entity.Entity;
import io.xol.chunkstories.api.input.Input;
import io.xol.chunkstories.api.item.Item;
import io.xol.chunkstories.api.item.ItemDefinition;
import io.xol.chunkstories.api.item.inventory.ItemPile;
import io.xol.chunkstories.api.player.Player;

public class ExampleItem extends Item {

	final String customProperty;
	
	public ExampleItem(ItemDefinition definition) {
		super(definition);
		
		customProperty = definition.resolveProperty("customProperty", "caca");
	}

	@Override
	public String getName() {
		return super.getDefinition().store().parent().localization().getLocalizedString("template.example");
	}

	@Override
	public boolean onControllerInput(Entity owner, ItemPile itemPile, Input input, Controller controller) {
		if(input.equals("mouse.left") && controller != null && controller instanceof Player) {
			((Player)controller).sendMessage(customProperty);
			return true;
		}
		
		return false;
	}

}
