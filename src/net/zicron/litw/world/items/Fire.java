package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.world.WorldItem;

public class Fire extends WorldItem {
    public Fire(int x, int y, String itemName) {
        super(x, y, 37, LITW.createInstanceID(), itemName, Items.FIRE);
        Renderer.addToEntityQueue(this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {
        Drawer.drawTexturedQuad(x, y, LITW.entityTextures, textureIndex, 2);
    }
}
