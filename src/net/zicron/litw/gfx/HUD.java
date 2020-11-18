package net.zicron.litw.gfx;

import net.zicron.litw.LITW;
import net.zicron.litw.world.Player;
import net.zicron.litw.world.WorldItem;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private List<WorldItem> collectedItems;
    private Player player;



    public HUD(Player player){
        collectedItems = new ArrayList<>();
        this.player = player;
    }


    public void tick() {

    }

    public void render() {
        int health = player.getHealth();
        for(int i = 0; i < 5; i++){
            if(i < health){
                Drawer.drawTexturedQuad(Screen.current.width - (110 - (i * 20)), 5, LITW.entityTextures, 6, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (102 - (i * 20)), 5, LITW.entityTextures, 7, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (110 - (i * 20)), 13, LITW.entityTextures, 38, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (102 - (i * 20)), 13, LITW.entityTextures, 39, 1);
            }else{
                Drawer.drawTexturedQuad(Screen.current.width - (110 - (i * 20)), 5, LITW.entityTextures, 8, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (102 - (i * 20)), 5, LITW.entityTextures, 9, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (110 - (i * 20)), 13, LITW.entityTextures, 40, 1);
                Drawer.drawTexturedQuad(Screen.current.width - (102 - (i * 20)), 13, LITW.entityTextures, 41, 1);
            }
        }
    }
}
