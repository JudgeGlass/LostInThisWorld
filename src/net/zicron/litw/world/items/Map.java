package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.WorldItem;

public class Map extends WorldItem {
    public Map(int x, int y, String itemName) {
        super(x, y, 42, itemName, Items.MAP);
        Renderer.addToEntityQueue(this);
        collider = new AABB(x, y, 16, 16, Items.MAP);
    }

    @Override
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

    @Override
    public void render() {
        Drawer.drawTexturedQuad(x + Level.xOffset, y + Level.yOffset, LITW.entityTextures, textureIndex, 2);
        collider.render();
    }
}
