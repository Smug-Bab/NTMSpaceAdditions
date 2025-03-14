package com.hbm.handler.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.dim.SolarSystem;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.special.ItemBedrockOreNew;
import com.hbm.items.special.ItemBedrockOreNew.BedrockOreGrade;
import com.hbm.items.special.ItemBedrockOreNew.CelestialBedrockOre;
import com.hbm.items.special.ItemBedrockOreNew.CelestialBedrockOreType;

import net.minecraft.item.ItemStack;

public class OreSlopperHandler extends NEIUniversalHandler {

	public OreSlopperHandler() {
		super(ModBlocks.machine_ore_slopper.getLocalizedName(), ModBlocks.machine_ore_slopper, getRecipes());
	}

	@Override
	public String getKey() {
		return "ntmOreSlopper";
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap getRecipes() {
		HashMap<Object, Object> recipes = new HashMap<Object, Object>();
		
		for(SolarSystem.Body body : SolarSystem.Body.values()) {
			if(body == SolarSystem.Body.ORBIT) continue;

			List<ItemStack> outputs = new ArrayList<>();
			for(CelestialBedrockOreType type : CelestialBedrockOre.get(body).types) outputs.add(ItemBedrockOreNew.make(BedrockOreGrade.BASE, type));
			outputs.add(ItemFluidIcon.make(Fluids.SLOP, 1000));

			recipes.put(new ItemStack[] {ItemFluidIcon.make(Fluids.WATER, 1000), new ItemStack(ModItems.bedrock_ore_base, 1, body.getDimensionId())}, outputs.toArray(new ItemStack[0]));
		}
		
		return recipes;
	}
}
