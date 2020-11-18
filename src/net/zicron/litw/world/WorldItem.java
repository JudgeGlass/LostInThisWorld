package net.zicron.litw.world;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.logic.AABB;

public abstract class WorldItem extends Entity {
    protected int x;
    protected int y;
    protected String itemName;

    public AABB collider;

    public WorldItem(final int x, final int y, String itemName){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
    }
}
