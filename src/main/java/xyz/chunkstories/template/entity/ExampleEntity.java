package xyz.chunkstories.template.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import io.xol.chunkstories.api.Location;
import io.xol.chunkstories.api.entity.EntityBase;
import io.xol.chunkstories.api.entity.EntityDefinition;
import io.xol.chunkstories.api.rendering.RenderingInterface;
import io.xol.chunkstories.api.rendering.entity.EntityRenderable;
import io.xol.chunkstories.api.rendering.entity.EntityRenderer;
import io.xol.chunkstories.api.rendering.entity.RenderingIterator;
import io.xol.chunkstories.api.rendering.lightning.Light;
import io.xol.chunkstories.api.rendering.mesh.RenderableMesh;
import io.xol.chunkstories.api.rendering.shader.Shader;
import io.xol.chunkstories.api.world.cell.CellData;

public class ExampleEntity extends EntityBase implements EntityRenderable {

	int TTL = 6000;
	
	public ExampleEntity(EntityDefinition entityType, Location location) {
		super(entityType, location);
	}

	@Override
	public EntityRenderer<ExampleEntity> getEntityRenderer() {
		return new EntityRenderer<ExampleEntity>() {

			@Override
			public int renderEntities(RenderingInterface renderer, RenderingIterator<ExampleEntity> renderableEntitiesIterator) {
				// The new and improved base 'entities' shader works for both shadow and opaque pass, no need for a shadow pass path
				Shader shader = renderer.useShader("entities");
				int counter = 0;
				
				//For all entities inside the player view...
				for(EntityBase entity : renderableEntitiesIterator.getElementsInFrustrumOnly()) {
					float t = renderer.getWorldRenderer().getAnimationTimer() * 0.25f; //timer for animations
					
					//Get the mesh we want to render
					RenderableMesh suzanne = renderer.meshes().getRenderableMesh("./models/example_suzanne/example_suzanne.dae");
					
					//Create a matrix to place the mesh just above the entity position 
					Matrix4f objectMatrix = new Matrix4f();
					objectMatrix.translate((float)entity.getLocation().x, (float)entity.getLocation().y, (float)entity.getLocation().z);
					objectMatrix.translate(0, 1, 0);
					
					//rotate the mesh arround the Y axis
					objectMatrix.rotate(t, new Vector3f(0,1,0));
					
					renderer.setObjectMatrix(objectMatrix); //Give that to the renderer
					
					//Get the voxel lightning data from the world and feed it to the shader
					CellData cell = entity.getWorld().peekSafely(entity.getLocation());
					shader.setUniform2f("worldLightIn", cell.getBlocklight(), cell.getSunlight());
					
					//Render the mesh using it's materials defined in blender
					suzanne.renderUsingMaterials(renderer);
					
					//Create and submit a light that cycles colour
					Light light = new Light(new Vector3f((float)Math.sin(t), (float)Math.sin(t + 3.14159f * 2 / 3), (float)Math.sin(t) + 3.14159f * 1 / 3),
							new Vector3f((float)entity.getLocation().x, (float)entity.getLocation().y, (float)entity.getLocation().z), 0);
					renderer.getLightsRenderer().queueLight(light);
					
					counter++;
				}
				return counter;
			}

			@Override
			public void freeRessources() {
				
			}
			
		};
	}

	@Override
	public void tick() {
		super.tick();
		
		TTL--;
		if(TTL <= 0)
			this.world.removeEntity(this);
	}
	
	
}
