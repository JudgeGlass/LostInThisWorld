package net.zicron.litw.world;

import net.zicron.litw.gfx.Entity;

public abstract class WorldItem extends Entity {
    protected int x;
    protected int y;
    protected String itemName;

    public WorldItem(final int x, final int y, String itemName){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
    }
}
