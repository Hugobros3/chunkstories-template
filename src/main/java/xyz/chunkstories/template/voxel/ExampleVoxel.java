package xyz.chunkstories.template.voxel;

import xyz.chunkstories.api.events.voxel.WorldModificationCause;
import xyz.chunkstories.api.exceptions.world.WorldException;
import xyz.chunkstories.api.voxel.Voxel;
import xyz.chunkstories.api.voxel.VoxelDefinition;
import xyz.chunkstories.api.world.cell.FutureCell;

public class ExampleVoxel extends Voxel {

	public ExampleVoxel(VoxelDefinition definition) {
		super(definition);
	}

	@Override
	public void onPlace(FutureCell cell, WorldModificationCause cause) throws WorldException {
		cell.getWorld().getSoundManager().playSoundEffect("sounds/wow.ogg");
		super.onPlace(cell, cause);
	}
}
