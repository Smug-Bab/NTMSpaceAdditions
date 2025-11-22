package com.hbm.entity.mob;

import com.hbm.lib.ModDamageSource;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

import net.minecraft.world.World;

public class EntityReaper extends EntityMob {
    
	public EntityReaper(World world) {
		super(world);
        this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		if ((source == ModDamageSource.oxyprime)) {
			return false;
		}

		return super.attackEntityFrom(source, amount);
	}
	
	

	protected String getLivingSound() {
		return "hbm:entity.reapersay";
	}

	protected String getHurtSound() {
		return "hbm:entity.reaperhurt";
	}

	protected String getDeathSound() {
		return "hbm:entity.reaperdeath";
	}
	
}