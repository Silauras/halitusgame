package com.mygdx.game.world;

import com.mygdx.game.world.block.Block;

public class World {

    private Block[][][] voxels = new Block[256][256][1024];

    public Block[][][] getVoxels() {
        return voxels;
    }

    public void setVoxels(Block[][][] voxels) {
        this.voxels = voxels;
    }
}
