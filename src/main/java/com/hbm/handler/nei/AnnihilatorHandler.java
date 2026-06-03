package com.hbm.handler.nei;

<<<<<<< HEAD
import java.util.Map.Entry;

=======
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.recipes.AnnihilatorRecipes;
import com.hbm.items.ModItems;
import com.hbm.util.InventoryUtil;
<<<<<<< HEAD
=======
import com.hbm.util.Tuple.Pair;
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0

import codechicken.nei.NEIServerUtils;
import net.minecraft.item.ItemStack;

public class AnnihilatorHandler extends NEIUniversalHandler {

	public AnnihilatorHandler() {
		super("Annihilator", ModBlocks.machine_annihilator, AnnihilatorRecipes.getRecipes());
	}

	@Override
	public String getKey() {
		return "ntmAnnihilating";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		
<<<<<<< HEAD
		outer: for(Entry<Object, Object> recipe : recipes.entrySet()) {
=======
		outer: for(Pair<Object, Object> recipe : recipes) {
>>>>>>> 5dd015fcd04498e0114669a19ac676855bef33d0
			ItemStack[][] ins = InventoryUtil.extractObject(recipe.getKey());
			ItemStack[][] outs = InventoryUtil.extractObject(recipe.getValue());
			
			for(ItemStack[] array : ins) for(ItemStack stack : array) if(stack.getItem() == ModItems.item_secret) continue outer;
			for(ItemStack[] array : outs) for(ItemStack stack : array) if(stack.getItem() == ModItems.item_secret) continue outer;
			
			match:
			for(ItemStack[] array : outs) {
				for(ItemStack stack : array) {
					if(NEIServerUtils.areStacksSameTypeCrafting(stack, result) && ItemStack.areItemStackTagsEqual(stack, result)) {
						this.arecipes.add(new RecipeSet(ins, outs, recipe.getKey()));
						break match;
					}
				}
			}
		}
	}
}
