package net.zicron.litw.world;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.logic.AABB;

public abstract class WorldItem extends Entity {
    protected int x;
    protected int y;
    public int textureIndex;
    public byte id;
    public String itemName;

    public AABB collider;

    public WorldItem(final int x, final int y, final int textureIndex, String itemName, byte id){
        this.x = x;
        this.y = y;
        this.textureIndex = textureIndex;
        this.itemName = itemName;
        this.id = id;
    }
}
