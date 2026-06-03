package com.hbm.dim.trait;

<<<<<<< HEAD
import com.hbm.entity.mob.siege.EntitySiegeCraft;
import com.hbm.entity.mob.siege.EntitySiegeUFO;
import com.hbm.entity.mob.siege.SiegeTier;
import com.hbm.items.ModItems;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.config.MobConfig;
import com.hbm.dim.CelestialBody;
import com.hbm.entity.missile.EntityCombatDropPod;
import com.hbm.entity.mob.EntityDuck;
import com.hbm.entity.mob.EntityMaskMan;
=======
import java.util.HashMap;
import java.util.Random;

import com.hbm.dim.CelestialBody;
import com.hbm.dim.WorldProviderCelestial;
import com.hbm.entity.missile.EntityCombatDropPod;
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
import com.hbm.entity.mob.EntityUFO;
import com.hbm.entity.mob.glyphid.EntityGlyphid;
import com.hbm.entity.mob.glyphid.EntityGlyphidBehemoth;
import com.hbm.entity.mob.glyphid.EntityGlyphidBlaster;
import com.hbm.entity.mob.glyphid.EntityGlyphidBrawler;
import com.hbm.entity.mob.glyphid.EntityGlyphidDigger;
<<<<<<< HEAD
import com.hbm.main.MainRegistry;
import com.hbm.util.ContaminationUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
=======
import com.hbm.entity.mob.siege.EntitySiegeCraft;
import com.hbm.entity.mob.siege.EntitySiegeUFO;
import com.hbm.entity.mob.siege.SiegeTier;
import com.hbm.main.MainRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;

<<<<<<< HEAD
public class CBT_Invasion extends CelestialBodyTrait {
=======
public class CBT_Invasion extends CelestialBodyTrait implements IBossDisplayData {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0

	// while i could polymorphize to the heavens, this event is more-or-less
	// "scripted"
	// in the sense that you would be fighting the ufo types we HAVE sequentially to
	// the boss
	// oh dont worry you still get to have fun killing! :)

	public int wave;
	public int kills;
	public int killreq;
	public double waveTime;
	public boolean isInvading;
	public int lastSpawns; // prevent over-lagging the server
	public int spawndelay;
	public int podBurstCounter = 0;
	public int podCooldown = 0;
	public boolean bossSpawned = false;
	public boolean warningPlayed;

	public CBT_Invasion() {
<<<<<<< HEAD
=======
		
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
	}

	public CBT_Invasion(int wave, double waveTime, boolean isInvading) {
		this.wave = wave;
		this.waveTime = waveTime;
		this.isInvading = isInvading;
	}

<<<<<<< HEAD
	public void Prepare() {
		if (!isInvading && waveTime >= 0) {
			waveTime--;
			warningPlayed = true;
			if (waveTime <= 5) {
				warningPlayed = false;
			}
			if (waveTime <= 0) {
				isInvading = true;
			}
		}
		if (isInvading)
=======
	public void prepare() {
		if(!isInvading && waveTime >= 0) {
			waveTime--;
			warningPlayed = true;
			if(waveTime <= 5) {
				warningPlayed = false;
			}
			if(waveTime <= 0) {
				isInvading = true;
			}
		}
		if(isInvading)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			return;
	}

	@Override
	public void update(boolean isRemote, CelestialBody body) {
<<<<<<< HEAD
		if (!isRemote) {
			Prepare();
			World world = DimensionManager.getWorld(body.dimensionId);
			if (isInvading) {
				if (world == null || world.playerEntities.isEmpty())
					return;
				LogicTick(world);
				HandleBurstSpawning(world);
				SpawnAttempt(world);
			}
		} else {
			if (!isInvading && !warningPlayed) {
=======
		if(!isRemote) {
			prepare();

			if(isInvading) {
				World world = DimensionManager.getWorld(body.dimensionId);

				if(world == null || world.playerEntities.isEmpty())
					return;

				logicTick(world);
				handleBurstSpawning(world);
				spawnAttempt(world);
			}
		} else {
			if(!isInvading && !warningPlayed) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				warningPlayed = true;
				MainRegistry.proxy.me().playSound("hbm:alarm.ping", 10F, 1F);
				MainRegistry.proxy.me().addChatComponentMessage(new ChatComponentText("Incoming Invasion!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
			}
<<<<<<< HEAD
		}
	}

	public void SpawnCattle(World world) {
		EntityPlayer player = (EntityPlayer) world.playerEntities
				.get(world.rand.nextInt(world.playerEntities.size()));
		if (!(player instanceof EntityPlayerMP))
			return;
		if (player.posY < 50 && player.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT)
=======
			
			World world = Minecraft.getMinecraft().theWorld;
				
			if(world != null && world.rand.nextInt(Math.max(1, 5 - wave)) == 0 && isInvading) {
				WorldProviderCelestial.Meteor.addMeteor();
			}
		}
	}

	public void spawnCattle(World world) {
		EntityPlayer player = (EntityPlayer) world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));

		if(!(player instanceof EntityPlayerMP))
			return;
		if(player.posY < 50 && player.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			return;

		EntityPlayerMP playerMP = (EntityPlayerMP) player;
		Random rand = world.rand;

		EntityCombatDropPod pod = new EntityCombatDropPod(world);
		pod.posX = playerMP.posX + (rand.nextGaussian() * 15);
		pod.posY = 250;
		pod.posZ = playerMP.posZ + (rand.nextGaussian() * 15);
		pod.motionY = -1.5;

		EntityGlyphid glyph;
		int amount = 1;
<<<<<<< HEAD
		if (wave == 1) {
			amount = 2;
			glyph = new EntityGlyphid(world);
		} else if (wave == 2) {
=======
		if(wave == 1) {
			amount = 2;
			glyph = new EntityGlyphid(world);
		} else if(wave == 2) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			int roll = rand.nextInt(3);
			amount = Math.max(1, rand.nextInt(4));
			switch (roll) {
			case 0:
				glyph = new EntityGlyphid(world);
				break;
			case 1:
				glyph = new EntityGlyphidBrawler(world);
				break;
			default:
				glyph = new EntityGlyphidDigger(world);
				break;
			}
<<<<<<< HEAD
		} else if (wave >= 3) {
=======
		} else if(wave >= 3) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			int roll = rand.nextInt(5);
			amount = Math.max(1, rand.nextInt(6));
			switch (roll) {
			case 0:
				glyph = new EntityGlyphid(world);
				break;
			case 1:
				glyph = new EntityGlyphidBrawler(world);
				break;
			case 2:
				glyph = new EntityGlyphidDigger(world);
				break;
			case 3:
				glyph = new EntityGlyphidBlaster(world);
				break;
			default:
				glyph = new EntityGlyphidBehemoth(world);
				amount = 1;
				break;
			}
		} else {
			glyph = new EntityGlyphid(world);
		}

		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("id", EntityList.getEntityString(glyph));
		glyph.writeToNBT(nbt);

		pod.setPayload(nbt, amount, 2);
		world.spawnEntityInWorld(pod);
	}

<<<<<<< HEAD
	private void HandleBurstSpawning(World world) {
		if (wave > 3)
			return;
		if (podCooldown > 0) {
=======
	private void handleBurstSpawning(World world) {
		if(wave > 3)
			return;

		if(podCooldown > 0) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			podCooldown--;
			return;
		}

<<<<<<< HEAD
		Random rand = world.rand;

		if (world.getTotalWorldTime() % 10 + world.rand.nextInt(3) == 0) {
			SpawnCattle(world);
			podBurstCounter++;
			if (podBurstCounter >= 3 + (wave - 1)) {
=======
		if(world.getTotalWorldTime() % 10 + world.rand.nextInt(3) == 0) {
			spawnCattle(world);
			podBurstCounter++;
			if(podBurstCounter >= 3 + (wave - 1)) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				podBurstCounter = 0;
				podCooldown = 500;
			}
		}
	}

<<<<<<< HEAD
	private void LogicTick(World world) {
		if (!isInvading)
=======
	private void logicTick(World world) {
		if(!isInvading)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			return;

		switch (wave) {
		case 0:
			advanceWave(world);
			break;
		case 1:
<<<<<<< HEAD
			killreq = 80;
			if (kills >= killreq)
=======
			killreq = 20;
			if(kills >= killreq)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				advanceWave(world);
			break;
		case 2:
			killreq = 100;
<<<<<<< HEAD
			if (kills >= killreq)
=======
			if(kills >= killreq)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				advanceWave(world);
			break;
		case 3:
			killreq = 150;
<<<<<<< HEAD
			if (kills >= killreq)
=======
			if(kills >= killreq)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				advanceWave(world);
			break;
		case 4:
			killreq = 1;
<<<<<<< HEAD
			if (!bossSpawned) {
=======
			if(!bossSpawned) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				spawnBoss(world);
				bossSpawned = true;
			}
			break;
		}
	}

	private void advanceWave(World world) {
		wave++;
		kills = 0;
		broadcast(world, "Wave " + (wave == 4 ? "FINAL" : wave) + " is starting!", EnumChatFormatting.GOLD);
	}

<<<<<<< HEAD
	public void SpawnAttempt(World world) {
		if (wave > 3)
			return;
		int timer = 200;
		if (wave == 2)
			timer = 100;
		if (wave == 3)
			timer = 80;

		if (world.getTotalWorldTime() % timer == 0) {
			EntityPlayer player = (EntityPlayer) world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
			
			if (player.posY < 50 && player.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT)
=======
	public void spawnAttempt(World world) {
		if(wave > 3)
			return;
		int timer = 200;
		if(wave == 2)
			timer = 100;
		if(wave == 3)
			timer = 80;

		if(world.getTotalWorldTime() % timer == 0) {
			EntityPlayer player = (EntityPlayer) world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
			
			if(player.posY < 50 && player.worldObj.getWorldInfo().getTerrainType() != WorldType.FLAT)
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				return; 

			double spawnX = player.posX + world.rand.nextGaussian() * 30;
			double spawnZ = player.posZ + world.rand.nextGaussian() * 30;
			double spawnY = player.posY + 30 + world.rand.nextInt(20);

			float bigUfoChance = 0.0F;
<<<<<<< HEAD
			if (wave == 2)
				bigUfoChance = 0.1F;
			else if (wave == 3)
				bigUfoChance = 0.2F;

			float waveFactor = wave * 0.05F;
			if (waveFactor > 0.2F) waveFactor = 0.2F; 
		        float roll = world.rand.nextFloat();

		        if (world.rand.nextFloat() < bigUfoChance) {
		       	    		       	    EntitySiegeCraft bigUfo = new EntitySiegeCraft(world);
		       	    bigUfo.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0.0F);

		       	    float bigRoll = world.rand.nextFloat();
		       	    SiegeTier chosen = (bigRoll < 0.5F - waveFactor) ? SiegeTier.STONE : SiegeTier.IRON;

		       	    bigUfo.setTier(chosen);
		       	    world.spawnEntityInWorld(bigUfo);

		       	} else {
		       	    EntitySiegeUFO smallUfo = new EntitySiegeUFO(world);
		       	    smallUfo.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0.0F);

		       	    float smallRoll = world.rand.nextFloat(); 
		       	    SiegeTier chosen;

		       	    if (smallRoll < 0.15F - (waveFactor * 1.5F))      chosen = SiegeTier.CLAY;  
		       	    else if (smallRoll < 0.35F - (waveFactor * 1.2F)) chosen = SiegeTier.STONE;  
		       	    else if (smallRoll < 0.50F - waveFactor)          chosen = SiegeTier.IRON;   
		       	    else if (smallRoll < 0.65F - (waveFactor * 0.8F)) chosen = SiegeTier.SILVER; 
		       	    else if (smallRoll < 0.80F - (waveFactor * 0.5F)) chosen = SiegeTier.GOLD;   
		       	    else if (smallRoll < 0.90F - (waveFactor * 0.2F)) chosen = SiegeTier.DESH;   
		       	    else if (smallRoll < 0.97F)                       chosen = SiegeTier.SCHRAB;
		       	    else						     chosen = SiegeTier.DNT;
		     
		       	    smallUfo.setTier(chosen);
		       	    world.spawnEntityInWorld(smallUfo);
		       	}
=======
			if(wave == 2)
				bigUfoChance = 0.1F;
			else if(wave == 3)
				bigUfoChance = 0.2F;

			float waveFactor = wave * 0.05F;
			if(waveFactor > 0.2F) waveFactor = 0.2F;

			if(world.rand.nextFloat() < bigUfoChance) {
				EntitySiegeCraft bigUfo = new EntitySiegeCraft(world);
				bigUfo.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0.0F);

				float bigRoll = world.rand.nextFloat();
				SiegeTier chosen = (bigRoll < 0.5F - waveFactor) ? SiegeTier.STONE : SiegeTier.IRON;

				bigUfo.setTier(chosen);
				world.spawnEntityInWorld(bigUfo);
			} else {
				EntitySiegeUFO smallUfo = new EntitySiegeUFO(world);
				smallUfo.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0.0F);

				float smallRoll = world.rand.nextFloat(); 
				SiegeTier chosen;

				if(smallRoll < 0.15F - (waveFactor * 1.5F))			chosen = SiegeTier.CLAY;
				else if(smallRoll < 0.35F - (waveFactor * 1.2F))	chosen = SiegeTier.STONE;
				else if(smallRoll < 0.50F - waveFactor)				chosen = SiegeTier.IRON;
				else if(smallRoll < 0.65F - (waveFactor * 0.8F))	chosen = SiegeTier.SILVER;
				else if(smallRoll < 0.80F - (waveFactor * 0.5F))	chosen = SiegeTier.GOLD;
				else if(smallRoll < 0.90F - (waveFactor * 0.2F))	chosen = SiegeTier.DESH;
				else if(smallRoll < 0.97F)							chosen = SiegeTier.SCHRAB;
				else												chosen = SiegeTier.DNT;
			
				smallUfo.setTier(chosen);
				world.spawnEntityInWorld(smallUfo);
			}

>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			lastSpawns++;
		}
	}

<<<<<<< HEAD
=======
	public void onKill(EntityLivingBase entity, CelestialBody body) {
		if(entity instanceof EntitySiegeUFO) {
			// GOLD and above are worth 2 kills
			int value = entity.getMaxHealth() >= 100 * 0.25 ? 2 : 1;
			kills += value;
			body.modifyTraits(this);
		} else if (entity instanceof EntitySiegeCraft) {
			kills += 10;
			body.modifyTraits(this);
		}
		
		if(wave >= 4 && entity instanceof EntityUFO) {
			HashMap<Class<? extends CelestialBodyTrait>, CelestialBodyTrait> currentTraits = body.getTraits();

			currentTraits.remove(CBT_Invasion.class);

			for(Object obj : entity.worldObj.playerEntities) {
				if(obj instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) obj;
					player.addChatComponentMessage(new ChatComponentText("The Invasion Is Over!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
				}
			}

			body.setTraits(currentTraits);
		}
	}

>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
	private void spawnBoss(World world) {
		EntityPlayer player = (EntityPlayer) world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
		EntityUFO entity = new EntityUFO(world);
		((EntityUFO) entity).scanCooldown = 100;
		EntityLiving entityliving = (EntityLiving) entity;
		entity.setLocationAndAngles(player.posX, player.posY + 50, player.posZ, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		entityliving.rotationYawHead = entityliving.rotationYaw;
		entityliving.renderYawOffset = entityliving.rotationYaw;
		entityliving.onSpawnWithEgg((IEntityLivingData) null);
		world.spawnEntityInWorld(entity);
	}

	private void broadcast(World world, String text, EnumChatFormatting color) {
<<<<<<< HEAD
		for (Object p : world.playerEntities) {
			if (p instanceof EntityPlayer) {
=======
		for(Object p : world.playerEntities) {
			if(p instanceof EntityPlayer) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
				((EntityPlayer) p).addChatComponentMessage(new ChatComponentText(text)
						.setChatStyle(new ChatStyle().setColor(color).setBold(true)));
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("wave", wave);
		nbt.setInteger("kills", kills);
		nbt.setInteger("killreq", killreq);
		nbt.setDouble("waveTime", waveTime);
		nbt.setBoolean("isInvading", isInvading);
		nbt.setBoolean("warningPlayed", warningPlayed);
		nbt.setInteger("podBurst", podBurstCounter);
		nbt.setInteger("podCooldown", podCooldown);
		nbt.setBoolean("bossSpawned", bossSpawned);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		wave = nbt.getInteger("wave");
		kills = nbt.getInteger("kills");
		killreq = nbt.getInteger("killreq");
		waveTime = nbt.getDouble("waveTime");
		isInvading = nbt.getBoolean("isInvading");
		warningPlayed = nbt.getBoolean("warningPlayed");
		podBurstCounter = nbt.getInteger("podBurst");
		podCooldown = nbt.getInteger("podCooldown");
		bossSpawned = nbt.getBoolean("bossSpawned");
	}

	@Override
	public void writeToBytes(ByteBuf buf) {
		buf.writeInt(wave);
		buf.writeInt(kills);
		buf.writeInt(killreq);
		buf.writeDouble(waveTime);
		buf.writeBoolean(isInvading);
		buf.writeBoolean(warningPlayed);
		buf.writeInt(podBurstCounter);
		buf.writeInt(podCooldown);
		buf.writeBoolean(bossSpawned);
	}

	@Override
	public void readFromBytes(ByteBuf buf) {
		wave = buf.readInt();
		kills = buf.readInt();
		killreq = buf.readInt();
		waveTime = buf.readDouble();
		isInvading = buf.readBoolean();
		warningPlayed = buf.readBoolean();
		podBurstCounter = buf.readInt();
		podCooldown = buf.readInt();
		bossSpawned = buf.readBoolean();
	}
<<<<<<< HEAD
=======

	@Override
	public float getMaxHealth() {
		return killreq;
	}

	@Override
	public float getHealth() {
		return killreq - kills;
	}

	@Override
	public IChatComponent func_145748_c_() {
		return new ChatComponentText("Wave " + wave);
	}
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
}