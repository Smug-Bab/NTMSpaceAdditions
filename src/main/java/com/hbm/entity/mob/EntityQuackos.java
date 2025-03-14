package com.hbm.entity.mob;

import com.hbm.config.GeneralConfig;
import com.hbm.items.ModItems;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * BOW
 */
public class EntityQuackos extends EntityDuck implements IBossDisplayData {

	/**
	 * BOW
	 */
	public EntityQuackos(World world) {
		super(world);
		this.setSize(0.3F * 25, 0.7F * 25);
	}

	/**
	 * BOW
	 */
	protected String getLivingSound() {
		return "hbm:entity.megaquacc";
	}

	/**
	 * BOW
	 */
	protected String getHurtSound() {
		return "hbm:entity.megaquacc";
	}

	/**
	 * BOW
	 */
	protected String getDeathSound() {
		return "hbm:entity.megaquacc";
	}

	/**
	 * BOW
	 */
	public EntityQuackos createChild(EntityAgeable entity) {
		return new EntityQuackos(this.worldObj);
	}

	/**
	 * BOW
	 */
	public boolean isEntityInvulnerable() {
		return true;
	}

	private int killCounter = 0;

	/**
	 * pluck
	 */
	public void setDead() {
		if(worldObj.isRemote) {
			super.setDead();
		} else if(GeneralConfig.enableSacrilege) {
			// if killed once per tick for a second OR if killed sequentially 10 times
			// this is so that if the software absolutely must kill the bastard, it eventually will get culled
			// sorry, invincibility is a burden on server operators
			killCounter += 2;
			if(killCounter > 20) {
				super.setDead();
			}
		}
	} //prank'd

	/**
	 * BOW
	 */
	public void setHealth(float f) {
		super.setHealth(this.getMaxHealth());
	} //prank'd

	/**
	 * BOW
	 */
	public boolean interact(EntityPlayer player) {

		if(super.interact(player)) {
			return true;

		} else {

			if(!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == player)) {
				player.mountEntity(this);
				return true;
			}

			return false;
		}
	}
	
	/**
	 * BOW
	 */
	public void despawn() {
		
		if(!worldObj.isRemote) {
			for(int i = 0; i < 150; i++) {
				NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "bf");
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data,
						posX + rand.nextDouble() * 20 - 10,
						posY + rand.nextDouble() * 25,
						posZ + rand.nextDouble() * 20 - 10),
						new TargetPoint(dimension, posX, posY, posZ, 150));
			}
			
			dropItem(ModItems.spawn_duck, 3);
		}
		this.isDead = true;
	}

	/**
	 * BOW
	 */
	public void updateRiderPosition() {

		super.updateRiderPosition();
		float f = MathHelper.sin(this.renderYawOffset * (float) Math.PI / 180.0F);
		float f1 = MathHelper.cos(this.renderYawOffset * (float) Math.PI / 180.0F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		this.riddenByEntity.setPosition(this.posX + (double) (f2 * f), this.posY + (double) (this.height - 0.125F) + this.riddenByEntity.getYOffset() + (double) f3, this.posZ - (double) (f2 * f1));

		if(this.riddenByEntity instanceof EntityLivingBase) {
			((EntityLivingBase) this.riddenByEntity).renderYawOffset = this.renderYawOffset;
		}
	}

	/**
	 * BOW
	 */
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 7.5F;
	}

	/**
	 * BOW
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if(!worldObj.isRemote && this.posY < -30) {
			this.setPosition(this.posX + rand.nextGaussian() * 30, 256, this.posZ + rand.nextGaussian() * 30);
		}

		if(killCounter > 0) killCounter--;
	}

	/**
	 * BOW
	 */
	@Override
	public void onDeath(DamageSource sourceOrRatherLackThereof) { }

	@Override
	public boolean allowLeashing() {
		return false;
	}
}
