package net.zicron.litw.gfx.gui;

import net.zicron.litw.gfx.Entity;

import java.awt.*;

public abstract class GUIItem extends Entity {
    public int x;
    public int y;

    public Rectangle rectangle;

    public GUIItem(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x, y, w, h);
    }
}
