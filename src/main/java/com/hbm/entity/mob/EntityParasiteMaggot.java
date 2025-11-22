package com.hbm.entity.mob;

import api.hbm.entity.ISuffocationImmune;
import com.hbm.lib.ModDamageSource;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityParasiteMaggot extends EntityMob implements ISuffocationImmune {

	public EntityParasiteMaggot(World world) {
		super(world);
		this.setSize(0.3F, 0.7F);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		if ((source == ModDamageSource.oxyprime || source == ModDamageSource.acid)) {
			return false;
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected Entity findPlayerToAttack() {
		return this.worldObj.getClosestVulnerablePlayerToEntity(this, 16);
	}

	@Override
	public void onUpdate() {
		this.renderYawOffset = this.rotationYaw;
		super.onUpdate();
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
