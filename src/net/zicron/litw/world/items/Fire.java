package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.AnimatedTile;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.WorldItem;

public class Fire extends WorldItem {

    private AnimatedTile animatedTile;

    public Fire(int x, int y, String itemName) {
        super(x, y, 36, LITW.createInstanceID(), itemName, Items.FIRE);
        Renderer.addToEntityQueue(this);
        animatedTile = new AnimatedTile(new int[]{36, 36+32}, 10);
        collider = new AABB(x, y, 32, 32, Items.FIRE, LITW.createInstanceID());
    }

    @Override
    public void tick() {
        collider.x = x + Level.xOffset;
        collider.y = y + Level.yOffset;
        animatedTile.tick();
    }

    @Override
    public void render() {
        int x = this.x + Level.xOffset;
        int y = this.y + Level.yOffset;

        animatedTile.render(LITW.entityTextures, x, y, 4);
        collider.render();
    }
}
