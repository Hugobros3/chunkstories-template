package xyz.chunkstories.template.entity;

import org.joml.Vector3d;
import xyz.chunkstories.api.entity.Entity;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import xyz.chunkstories.api.entity.EntityDefinition;
import xyz.chunkstories.api.entity.traits.TraitRenderable;
import xyz.chunkstories.api.graphics.representation.Model;
import xyz.chunkstories.api.graphics.representation.ModelInstance;
import xyz.chunkstories.api.graphics.representation.ModelPosition;
import xyz.chunkstories.api.graphics.representation.PointLight;
import xyz.chunkstories.api.graphics.systems.dispatching.RepresentationsGobbler;
import xyz.chunkstories.api.world.World;
import xyz.chunkstories.api.world.cell.CellData;

import java.util.Collections;

public class ExampleEntity extends Entity {

	private int timeToLive = 6000;
	
	public ExampleEntity(EntityDefinition definition, World world) {
		super(definition, world);

		new TraitRenderable<ExampleEntity>(this) {

			Model suzanneModel = definition.getStore().getParent().getModels().getOrLoadModel("models/example_suzanne/example_suzanne.dae");

			@Override public void buildRepresentation(@org.jetbrains.annotations.NotNull RepresentationsGobbler representationsGobbler) {
				float t = representationsGobbler.getFrame().getAnimationTimer() * 0.25f;

				ModelPosition modelPosition = new ModelPosition(new Matrix4f().translate((float)getLocation().x, (float)getLocation().y, (float)getLocation().z));
				ModelInstance suzanneInstance = new ModelInstance(suzanneModel, modelPosition, Collections.emptyMap(), -1, null);
				representationsGobbler.acceptRepresentation(suzanneInstance, -1);

				PointLight light = new PointLight(getLocation(), new Vector3d(Math.sin(t), Math.sin(t + 3.14159f * 2 / 3), Math.sin(t) + 3.14159f * 1 / 3));
				representationsGobbler.acceptRepresentation(light, -1);
			}
		};
	}

	/*@Override
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
	}*/

	@Override
	public void tick() {
		timeToLive--;
		if(timeToLive <= 0)
			getWorld().removeEntity(getId());
	}
}
