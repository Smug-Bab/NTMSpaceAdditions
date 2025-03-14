package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.render.util.RenderDecoItem;
import com.hbm.tileentity.machine.TileEntityMachineVacuumCircuit;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderVacuumCircuit extends TileEntitySpecialRenderer implements IItemRendererProvider {
	
	private RenderItem itemRenderer = new RenderDecoItem(this);
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float interp) {
		TileEntityMachineVacuumCircuit solderer = (TileEntityMachineVacuumCircuit) tile;

		GL11.glPushMatrix();
		{

			GL11.glTranslated(x + 0.5, y, z + 0.5);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_CULL_FACE);
	
			switch(tile.getBlockMetadata() - BlockDummyable.offset) {
			case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
			case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
			case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
			case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
			}
			
			bindTexture(ResourceManager.vac_cir_station_tex);
			ResourceManager.vac_cir_station.renderAll();

			if(solderer.display != null) {
				GL11.glPushMatrix();
				{

					GL11.glTranslated(0.625D, 1.25D, 0.5D);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glRotatef(90, 0F, 1F, 0F);
					GL11.glRotatef(-90, 1F, 0F, 0F);
					
					ItemStack stack = solderer.display.copy();
					
					EntityItem item = new EntityItem(null, 0.0D, 0.0D, 0.0D, stack);
					item.getEntityItem().stackSize = 1;
					item.hoverStart = 0.0F;
					
					RenderItem.renderInFrame = true;
					GL11.glScaled(1.5, 1.5, 1.5);
					this.itemRenderer.doRender(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
					RenderItem.renderInFrame = false;

				}
				GL11.glPopMatrix();
			}
			
		}
		GL11.glPopMatrix();
	}

	@Override
	public Item getItemForRenderer() {
		return Item.getItemFromBlock(ModBlocks.machine_vacuum_circuit);
	}

	@Override
	public IItemRenderer getRenderer() {
		return new ItemRenderBase( ) {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(3.5, 3.5, 3.5);
			}
			public void renderCommon() {
				bindTexture(ResourceManager.vac_cir_station_tex);
				ResourceManager.vac_cir_station.renderAll();
			}};
	}
}

