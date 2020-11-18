package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.WorldItem;

public class Key extends WorldItem {
    public Key(int x, int y, String itemName) {
        super(x, y, 4, itemName, Items.KEY);
        Renderer.addToEntityQueue(this);
        collider = new AABB(x, y, 16, 16, Items.KEY);
    }


    public void tick() {
        collider.x = collider.ox + Level.xOffset;
        collider.y = collider.oy + Level.yOffset;

        AABB playerCollider = Level.getPlayer().getCollider();
        if(AABB.checkCollision(playerCollider, collider)){
            if(Level.getPlayer().getHUD().addItem(this)){
                Renderer.entityRemoveQueue.add(this);
            }
        }
    }


    public void render() {
        Drawer.drawTexturedQuad(x + Level.xOffset, y + Level.yOffset, LITW.entityTextures, 4, 2);
        collider.render();
    }
}
