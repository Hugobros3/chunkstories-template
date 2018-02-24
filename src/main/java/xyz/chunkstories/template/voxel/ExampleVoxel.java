package xyz.chunkstories.template.voxel;

import io.xol.chunkstories.api.events.voxel.WorldModificationCause;
import io.xol.chunkstories.api.exceptions.world.WorldException;
import io.xol.chunkstories.api.voxel.Voxel;
import io.xol.chunkstories.api.voxel.VoxelDefinition;
import io.xol.chunkstories.api.world.cell.FutureCell;

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
