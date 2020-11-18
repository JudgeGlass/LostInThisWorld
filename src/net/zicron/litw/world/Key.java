package net.zicron.litw.world;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.logic.AABB;

public class Key extends WorldItem {
    public Key(int x, int y, String itemName) {
        super(x, y, itemName);
        Renderer.addToEntityQueue(this);
        collider = new AABB(x, y, 16, 16, Items.KEY);
    }


    public void tick() {
        collider.x = collider.ox + Level.xOffset;
        collider.y = collider.oy + Level.yOffset;

        AABB playerCollider = Level.getPlayer().getCollider();
        if(AABB.checkCollision(playerCollider, collider)){
            Renderer.entityRemoveQueue.add(this);
        }
    }


    public void render() {
        Drawer.drawTexturedQuad(x + Level.xOffset, y + Level.yOffset, LITW.entityTextures, 4, 2);
        collider.render();
    }
}
