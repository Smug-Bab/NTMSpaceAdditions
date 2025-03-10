package com.hbm.entity.mob;

import com.hbm.lib.ModDamageSource;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.world.World;

public class EntityReaper extends EntityMob {
    
	public EntityReaper(World world) {
		super(world);
        this.tasks.addTask(0, new EntityAILookIdle(this));
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