package com.hbm.blocks.machine;

import java.util.Random;

<<<<<<< HEAD
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.storage.TileEntityCombatDropPod;
import com.hbm.tileentity.machine.storage.TileEntitySoyuzCapsule;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
=======
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.storage.TileEntityCombatDropPod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CombatDropPod extends BlockContainer {

	public CombatDropPod(Material p_i45386_1_) {
		super(p_i45386_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCombatDropPod();
	}
	
	@Override
<<<<<<< HEAD
	public int getRenderType(){
=======
	public int getRenderType() {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
<<<<<<< HEAD
	

	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
		super.breakBlock(world, x, y, z, block, meta);
    }
=======

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.ingot_steel;
	}

	@Override public int quantityDropped(Random rand) {
		return 16;
	}
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0

}
