package com.hbm.tileentity.machine;

import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.tileentity.IFluidCopiable;
import com.hbm.tileentity.TileEntityMachineBase;

import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.fluid.IFluidStandardTransceiver;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDeuteriumExtractor extends TileEntityMachineBase implements IEnergyReceiverMK2, IFluidStandardTransceiver, IFluidCopiable {
	
	public long power = 0;
	public FluidTank[] tanks;

	public TileEntityDeuteriumExtractor() {
		super(0);
		tanks = new FluidTank[2];
		tanks[0] = new FluidTank(Fluids.WATER, 1000);
		tanks[1] = new FluidTank(Fluids.HEAVYWATER, 100);
	}

	@Override
	public String getName() {
		return "container.deuterium";
	}

	@Override
	public void updateEntity() {
		
		if(!worldObj.isRemote) {
			
			this.updateConnections();
			
			if(hasPower()&& this.power > 200 && hasEnoughWater() && tanks[1].getMaxFill() > tanks[1].getFill()) {
				int convert = Math.min(tanks[1].getMaxFill(), tanks[0].getFill()) / 50;
				convert = Math.min(convert, tanks[1].getMaxFill() - tanks[1].getFill());
				
				tanks[0].setFill(tanks[0].getFill() - convert * 50); //dividing first, then multiplying, will remove any rounding issues
				tanks[1].setFill(tanks[1].getFill() + convert);
				power -= this.getMaxPower() / 100;
			}
			
			this.subscribeToAllAround(tanks[0].getTankType(), this);
			this.sendFluidToAll(tanks[1], this);
			
			this.networkPackNT(50);
		}
	}
	
	protected void updateConnections() {
		
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			this.trySubscribe(worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir);
	}

	@Override
	public void serialize(ByteBuf buf) {
		super.serialize(buf);
		buf.writeLong(power);
		tanks[0].serialize(buf);
		tanks[1].serialize(buf);
	}

	@Override
	public void deserialize(ByteBuf buf) {
		super.deserialize(buf);
		this.power = buf.readLong();
		tanks[0].deserialize(buf);
		tanks[1].deserialize(buf);
	}

	public boolean hasPower() {
		return power >= this.getMaxPower() / 100;
	}

	public boolean hasEnoughWater() {
		return tanks[0].getFill() >= 100;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.power = nbt.getLong("power");
		tanks[0].readFromNBT(nbt, "water");
		tanks[1].readFromNBT(nbt, "heavyWater");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setLong("power", power);
		tanks[0].writeToNBT(nbt, "water");
		tanks[1].writeToNBT(nbt, "heavyWater");
	}

	@Override
	public void setPower(long i) {
		power = i;
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public long getMaxPower() {
		return 10_000;
	}

	@Override
	public FluidTank[] getSendingTanks() {
		return new FluidTank[] { tanks[1] };
	}

	@Override
	public FluidTank[] getReceivingTanks() {
		return new FluidTank[] { tanks[0] };
	}

	@Override
	public FluidTank[] getAllTanks() {
		return tanks;
	}

	@Override
	public FluidTank getTankToPaste() {
		return null;
	}
}