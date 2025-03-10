package com.hbm.render.entity.mob;

import com.hbm.lib.RefStrings;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import com.hbm.entity.mob.EntityReaper;
import net.minecraft.util.ResourceLocation;

public class RenderReaper extends RenderLiving {
	
	private static final ResourceLocation reaperTextures = new ResourceLocation(RefStrings.MODID, "textures/entity/reaper.png");
	
	public RenderReaper(ModelBase model, float f) {
		super(model, f);
	}
	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return reaperTextures;
	}
	
}