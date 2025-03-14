package com.hbm.dim.laythe.GenLayerLaythe;

import com.hbm.dim.laythe.biome.BiomeGenBaseLaythe;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLaytheIslands extends GenLayer {

	public GenLayerLaytheIslands(long seed, GenLayer genLayer) {
		super(seed);
		this.parent = genLayer;
	}

	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		return this.getIntsOcean(areaX, areaY, areaWidth, areaHeight);
	}

	private int[] getIntsOcean(int p_151626_1_, int p_151626_2_, int p_151626_3_, int p_151626_4_) {
		int i = p_151626_1_ - 1;
		int j = p_151626_2_ - 1;
		int k = 1 + p_151626_3_ + 1;
		int l = 1 + p_151626_4_ + 1;
		int[] aint = this.parent.getInts(i, j, k, l);
		int[] aint1 = IntCache.getIntCache(p_151626_3_ * p_151626_4_);

		for(int i1 = 0; i1 < p_151626_4_; ++i1) {
			for(int j1 = 0; j1 < p_151626_3_; ++j1) {
				this.initChunkSeed((long) (j1 + p_151626_1_), (long) (i1 + p_151626_2_));
				int k1 = aint[j1 + 1 + (i1 + 1) * k];

				if(k1 == BiomeGenBaseLaythe.laytheCoast.biomeID) {
					int l1 = aint[j1 + 1 + (i1 + 1 - 1) * k];
					int i2 = aint[j1 + 1 + 1 + (i1 + 1) * k];
					int j2 = aint[j1 + 1 - 1 + (i1 + 1) * k];
					int k2 = aint[j1 + 1 + (i1 + 1 + 1) * k];
					boolean flag = ((l1 == BiomeGenBaseLaythe.laytheIsland.biomeID)
							|| (i2 == BiomeGenBaseLaythe.laytheIsland.biomeID)
							|| (j2 == BiomeGenBaseLaythe.laytheIsland.biomeID)
							|| (k2 == BiomeGenBaseLaythe.laytheIsland.biomeID));

					boolean islandThreshold = flag && this.nextInt(50) == 0;

					if(!islandThreshold) {
						k1 = BiomeGenBaseLaythe.laytheOcean.biomeID;
					}
				} else {

					if(k1 == BiomeGenBaseLaythe.laytheIsland.biomeID) {
						k1 = this.nextInt(3) == 0 ? BiomeGenBaseLaythe.laytheCoast.biomeID : k1;
					}
				}

				aint1[j1 + i1 * p_151626_3_] = k1;
			}
		}

		return aint1;
	}

}
