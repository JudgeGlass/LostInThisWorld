package net.zicron.litw.gfx.gui;

import static org.lwjgl.opengl.GL11.*;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.gfx.Screen;
import net.zicron.litw.gfx.text.Font;
import net.zicron.litw.io.Log;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class TextField extends GUIItem{
    private String text = "";

    private boolean isFocused = false;
    private int characterLimit = 0;

    public TextField(int x, int y, int w, int h) {
        super(x, y, w, h);
        //Renderer.addToEntityQueue(this);
        characterLimit = ((w - 16) / 8);
    }

    @Override
    public void tick() {
        int mouseX = Mouse.getX();
        int mouseY = (-Mouse.getY() + Screen.current.height);

        while(Mouse.next()){
            if(Mouse.getEventButton() == 0){
                if(rectangle.contains(mouseX, mouseY)){
                    isFocused = true;
                }else{
                    isFocused = false;
                }
            }
        }


        while(Keyboard.next() && isFocused){
            if(Keyboard.getEventKey() == Keyboard.KEY_BACK){
                if(Keyboard.getEventKeyState()) {
                    if (text.length() > 0) {
                        text = text.substring(0, text.length() - 1);
                    }
                }
            }else {
                if(Keyboard.getEventKeyState()) {
                    if(text.length() <= characterLimit) {
                        text += (Keyboard.getEventCharacter() == 0) ? "" : Keyboard.getEventCharacter();
                    }
                }
            }
        }

    }

    public String getText(){
        return text;
    }

    @Override
    public void render() {
        Drawer.drawQuad(x, y, rectangle.width, rectangle.height, 0x000000);
        glPushAttrib(GL_CURRENT_BIT);
        if(isFocused)
            glColor3f(1f, 1f, 0f);
        else
            glColor3f(1f, 1f, 1f);
        glLineWidth(1);
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glBegin(GL_POLYGON);
            glVertex2f(x, y);
            glVertex2f(x + rectangle.width, y);
            glVertex2f(x + rectangle.width, y + rectangle.height);
            glVertex2f(x, y + rectangle.height);
        glEnd();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        glPopAttrib();

        Font.draw(x + 5, y+10, text, 0xFFFFFF, 1, false, LITW.fontTextures);
    }
}
