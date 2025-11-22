package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelReaper extends ModelBase {
	
    public ModelRenderer head;
    public ModelRenderer rjaw;
    public ModelRenderer ljaw;
    public ModelRenderer uptorso;
    public ModelRenderer lowtorso;
    public ModelRenderer ruparm;
    public ModelRenderer luparm;
    public ModelRenderer rlowarm;
    public ModelRenderer llowarm;
    public ModelRenderer rupleg;
    public ModelRenderer lupleg;
    public ModelRenderer rlowleg;
    public ModelRenderer rlowleg_1;

    public ModelReaper() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 12);
        this.head.setRotationPoint(0.0F, -4.0F, -2.0F);
        this.head.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8, 0.0F);
        this.setRotateAngle(head, 3.447025299072266F, 0.0F, 0.0F);
        this.ruparm = new ModelRenderer(this, 31, 9);
        this.ruparm.setRotationPoint(-2.0F, 0.0F, -2.0F);
        this.ruparm.addBox(-3.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
        this.setRotateAngle(ruparm, -0.39269909262657166F, 0.0F, 0.47996553778648376F);
        this.llowarm = new ModelRenderer(this, 0, 12);
        this.llowarm.setRotationPoint(8.0F, 11.0F, -6.0F);
        this.llowarm.addBox(0.0F, 0.0F, 0.0F, 3, 1, 14, 0.0F);
        this.setRotateAngle(llowarm, -1.204277157783508F, -0.22689279913902283F, 0.05235987901687623F);
        this.lowtorso = new ModelRenderer(this, 23, 24);
        this.lowtorso.setRotationPoint(0.0F, 3.0F, 0.20000000298023224F);
        this.lowtorso.addBox(-3.5F, 0.0F, 0.0F, 7, 9, 4, 0.0F);
        this.setRotateAngle(lowtorso, 0.5235987901687622F, 0.0F, 0.0F);
        this.rjaw = new ModelRenderer(this, 2, 25);
        this.rjaw.mirror = true;
        this.rjaw.setRotationPoint(0.0F, -4.0F, -5.5F);
        this.rjaw.addBox(-1.5F, 0.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotateAngle(rjaw, 0.2617993877991494F, -0.2617993877991494F, 0.6108652381980153F);
        this.luparm = new ModelRenderer(this, 25, 9);
        this.luparm.setRotationPoint(2.0F, 0.0F, -2.0F);
        this.luparm.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
        this.setRotateAngle(luparm, -0.39269909262657166F, 0.0F, -0.47996553778648376F);
        this.ljaw = new ModelRenderer(this, 2, 25);
        this.ljaw.setRotationPoint(0.0F, -4.0F, -5.5F);
        this.ljaw.addBox(-1.5F, 0.0F, -5.0F, 3, 3, 5, 0.0F);
        this.setRotateAngle(ljaw, 0.2617993877991494F, 0.2617993877991494F, -0.6108652381980153F);
        this.rlowleg = new ModelRenderer(this, 18, 34);
        this.rlowleg.setRotationPoint(-1.0F, 18.0F, 3.0F);
        this.rlowleg.addBox(-3.0F, 0.0F, 0.0F, 3, 8, 1, 0.0F);
        this.setRotateAngle(rlowleg, 0.3839724361896515F, -0.471238911151886F, 0.296705961227417F);
        this.rlowleg_1 = new ModelRenderer(this, 6, 34);
        this.rlowleg_1.setRotationPoint(1.0F, 18.0F, 3.0F);
        this.rlowleg_1.addBox(0.0F, 0.0F, 0.0F, 3, 8, 1, 0.0F);
        this.setRotateAngle(rlowleg_1, 0.3839724361896515F, 0.471238911151886F, -0.296705961227417F);
        this.rupleg = new ModelRenderer(this, 12, 34);
        this.rupleg.setRotationPoint(0.0F, 10.0F, 5.0F);
        this.rupleg.addBox(-3.0F, 0.0F, 0.0F, 3, 8, 1, 0.0F);
        this.setRotateAngle(rupleg, -0.3141592741012573F, -0.471238911151886F, 0.27925267815589905F);
        this.lupleg = new ModelRenderer(this, 0, 34);
        this.lupleg.setRotationPoint(0.0F, 10.0F, 5.0F);
        this.lupleg.addBox(0.0F, 0.0F, 0.0F, 3, 8, 1, 0.0F);
        this.setRotateAngle(lupleg, -0.3141592741012573F, 0.471238911151886F, -0.27925267815589905F);
        this.uptorso = new ModelRenderer(this, 25, -1);
        this.uptorso.setRotationPoint(0.5F, 0.0F, -5.0F);
        this.uptorso.addBox(-4.5F, 0.0F, 0.0F, 8, 9, 5, 0.0F);
        this.setRotateAngle(uptorso, 1.0471975803375244F, 0.0F, 0.0F);
        this.rlowarm = new ModelRenderer(this, 0, 0);
        this.rlowarm.setRotationPoint(-8.0F, 11.0F, -6.0F);
        this.rlowarm.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 14, 0.0F);
        this.setRotateAngle(rlowarm, -1.204277157783508F, 0.22689279913902283F, -0.05235987901687623F);
    }


    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rlowleg.render(f5);
        this.ljaw.render(f5);
        this.rjaw.render(f5);
        this.rlowleg_1.render(f5);
        this.rupleg.render(f5);
        this.lupleg.render(f5);
        this.lowtorso.render(f5);
        this.llowarm.render(f5);
        this.head.render(f5);
        this.uptorso.render(f5);
        this.luparm.render(f5);
        this.rlowarm.render(f5);
        this.ruparm.render(f5);
        
        this.rjaw.rotateAngleX = (float)(MathHelper.cos(f2 * 0.1F) * 0.1F) + 0.5F;
        this.ljaw.rotateAngleX = (float)(MathHelper.cos(f2 * 0.1F) * 0.1F) + 0.5F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        
    }
    
}
