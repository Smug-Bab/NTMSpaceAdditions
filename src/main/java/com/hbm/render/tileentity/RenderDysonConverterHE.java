package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.tileentity.machine.TileEntityDysonConverterHE;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderDysonConverterHE extends TileEntitySpecialRenderer implements IItemRendererProvider {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		{

			GL11.glTranslated(x + 0.5D, y, z + 0.5D);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_CULL_FACE);

			TileEntityDysonConverterHE converter = (TileEntityDysonConverterHE) tileEntity;

			switch(tileEntity.getBlockMetadata() - BlockDummyable.offset) {
			case 2: GL11.glRotatef(0, 0F, 1F, 0F); break;
			case 4: GL11.glRotatef(90, 0F, 1F, 0F); break;
			case 3: GL11.glRotatef(180, 0F, 1F, 0F); break;
			case 5: GL11.glRotatef(270, 0F, 1F, 0F); break;
			}

			GL11.glShadeModel(GL11.GL_SMOOTH);
			bindTexture(ResourceManager.dyson_he_converter_tex);
			ResourceManager.dyson_he_converter.renderPart("HE_Converter");

			float t = converter.isConverting ? tileEntity.getWorldObj().getTotalWorldTime() + f : 0;

			GL11.glTranslatef(0.0F, 1.5F, 0.0F);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(t, 0, 0, 1);
				GL11.glTranslated(0.0F, -1.5F, 0.0F);
				ResourceManager.dyson_he_converter.renderPart("Coil1");
				ResourceManager.dyson_he_converter.renderPart("Coil3");
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glRotatef(t, 0, 0, -1);
				GL11.glTranslated(0.0F, -1.5F, 0.0F);
				ResourceManager.dyson_he_converter.renderPart("Coil2");
			}
			GL11.glPopMatrix();

			GL11.glShadeModel(GL11.GL_FLAT);

		}
		GL11.glPopMatrix();
	}

	@Override
	public IItemRenderer getRenderer() {
		return new ItemRenderBase() {
			public void renderInventory() {
				GL11.glTranslated(0, -1, 0);
				GL11.glScaled(3.0D, 3.0D, 3.0D);
			}
			public void renderCommon() {
				GL11.glScaled(0.55, 0.55, 0.55);
				GL11.glShadeModel(GL11.GL_SMOOTH);
				bindTexture(ResourceManager.dyson_he_converter_tex);
				ResourceManager.dyson_he_converter.renderAll();
				GL11.glShadeModel(GL11.GL_FLAT);
			}
		};
	}

	@Override
	public Item getItemForRenderer() {
		return Item.getItemFromBlock(ModBlocks.dyson_converter_he);
	}

}
