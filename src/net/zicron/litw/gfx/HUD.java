package net.zicron.litw.gfx;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.text.Font;
import net.zicron.litw.io.Audio;
import net.zicron.litw.world.Player;
import net.zicron.litw.world.WorldItem;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private Player player;


    private WorldItem[] collectedItems;
    private int[] slotAmount;



    public HUD(Player player){
        collectedItems = new WorldItem[8];
        slotAmount = new int[8];
        this.player = player;
    }

    public boolean addItem(final WorldItem worldItem){
        for(int i = 0; i < collectedItems.length; i++){
            for(int j = 0; j < collectedItems.length; j++){
                WorldItem wi = collectedItems[j];
                if(wi != null){
                    if(wi.itemName.equals(worldItem.itemName)){
                        slotAmount[j]++;
                        Audio.SOUND.play("pickup.wav");
                        return true;
                    }
                }
            }

            if(collectedItems[i] == null){
                slotAmount[i]++;
                collectedItems[i] = worldItem;
                Audio.SOUND.play("pickup.wav");
                return true;
            }
        }

        return false;
    }

    public boolean hasItem(byte id){
        for(WorldItem wi: collectedItems){
            if(wi != null){
                if(wi.id == id){
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteItem(byte id){
        for(int i = 0; i < collectedItems.length; i++){
            if(collectedItems[i] != null){
                if(collectedItems[i].id == id){
                    if(slotAmount[i] > 0){
                        slotAmount[i]--;
                        if(slotAmount[0] == 0){collectedItems[i] = null;}
                    }else{
                        collectedItems[i] = null;
                    }
                    break;
                }
            }
        }
    }

    public void tick() {

    }

    public void render() {
        Font.draw(Screen.current.width - 128, Screen.current.height - 64, "Oxygen: " + player.getOxygen() + "%", 0xFF0000, 1, true, LITW.fontTextures);

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

        for(int i = 0; i < 8; i++){
            WorldItem wi = collectedItems[i];
            if(wi == null) continue;

            Font.draw(10 + (i * 27), Screen.current.height - 32, Integer.toString(slotAmount[i]), 0xFFFFFF, 1, false, LITW.fontTextures);
            Drawer.drawTexturedQuad(5 + (i * 27), Screen.current.height - 24, LITW.entityTextures, wi.textureIndex, 3);
        }
    }
}
