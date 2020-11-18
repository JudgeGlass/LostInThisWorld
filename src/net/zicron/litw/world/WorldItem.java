package net.zicron.litw.world;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.logic.AABB;

public abstract class WorldItem extends Entity {
    protected int x;
    protected int y;
    protected byte id;
    protected String itemName;

    public AABB collider;

    public WorldItem(final int x, final int y, String itemName, byte id){
        this.x = x;
        this.y = y;
        this.itemName = itemName;
        this.id = id;
    }
}
