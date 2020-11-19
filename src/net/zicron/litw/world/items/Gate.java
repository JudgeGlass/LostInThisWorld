package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.io.Log;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.TileCollider;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.Player;
import net.zicron.litw.world.WorldItem;

public class Gate extends WorldItem {
    public Gate(int x, int y, String itemName) {
        super(x, y, 43, LITW.createInstanceID(), itemName, Items.GATE);
        Renderer.addToEntityQueue(this);
        collider = new AABB(x, y, 96,  16, Items.GATE, instance);
        TileCollider.colliders.add(collider);
    }

    @Override
    public void tick() {
        collider.x = x + Level.xOffset;
        collider.y = y + Level.yOffset;


        AABB playerCollider = Level.getPlayer().getCollider();

        if(AABB.checkCollision(playerCollider, collider)){
            Log.info("HITTING THE WAALFD");
        }

    }

    @Override
    public void render() {
        int x = this.x + Level.xOffset;
        int y = this.y + Level.yOffset;

        Drawer.drawTexturedQuad(x, y, LITW.entityTextures, textureIndex, 2);
        Drawer.drawTexturedQuad(x + 16, y, LITW.entityTextures, textureIndex+1, 2);
        Drawer.drawTexturedQuad(x + 16*2, y, LITW.entityTextures, textureIndex+1, 2);
        Drawer.drawTexturedQuad(x + 16*3, y, LITW.entityTextures, textureIndex+1, 2);
        Drawer.drawTexturedQuad(x + 16*4, y, LITW.entityTextures, textureIndex+1, 2);
        Drawer.drawTexturedQuad(x + 16*5, y, LITW.entityTextures, textureIndex+2, 2);
        collider.render();
    }
}
