package net.zicron.litw.world.items;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.HUD;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.io.Audio;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.TileCollider;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.WorldItem;

public class KeyHole extends WorldItem {
    private Gate gate;

    public KeyHole(int x, int y, String itemName, Gate gate) {
        super(x, y, 5, LITW.createInstanceID(), itemName, Items.KEYHOLE);
        this.gate = gate;
        Renderer.addToEntityQueue(this);
        collider = new AABB(x, y, 16, 16, Items.KEYHOLE, instance);
    }

    @Override
    public void tick() {
        collider.x = x + Level.xOffset;
        collider.y = y + Level.yOffset;

        AABB playerCollider = Level.getPlayer().getCollider();
        HUD hud = Level.getPlayer().getHUD();
        if(AABB.checkCollision(playerCollider, collider)){
            if(hud.hasItem(Items.KEY)){
                Audio.SOUND.play("gate_open.wav");
                hud.deleteItem(Items.KEY);
                TileCollider.removeCollider(gate.instance);
                Renderer.entityRemoveQueue.add(gate);
                Renderer.entityRemoveQueue.add(this);
            }
        }
    }

    @Override
    public void render() {
        int x = this.x + Level.xOffset;
        int y = this.y + Level.yOffset;

        Drawer.drawTexturedQuad(x, y, LITW.entityTextures, textureIndex, 2);
    }
}
