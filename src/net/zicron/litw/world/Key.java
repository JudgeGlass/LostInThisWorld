package net.zicron.litw.world;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;

public class Key extends WorldItem {
    public Key(int x, int y, String itemName) {
        super(x, y, itemName);
        Renderer.addToEntityQueue(this);
    }


    public void tick() {

    }


    public void render() {
        Drawer.drawTexturedQuad(x, y, LITW.entityTextures, 4, 2);
    }
}
