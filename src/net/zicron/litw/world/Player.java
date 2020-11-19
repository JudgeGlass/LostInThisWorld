package net.zicron.litw.world;

import net.zicron.litw.gfx.*;
import net.zicron.litw.world.items.Key;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.text.Font;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.Bullet;
import net.zicron.litw.logic.TileCollider;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class Player extends Entity{
	
	private final int moveSpeed = 4;
	private int x;
	private int y;
	private int vel;
	private int health = 2;
	private int oxygen = 100;
	
	private AnimatedTile aTile;
	private HUD hud;
	private boolean isStill = true;
	private int counter = 0;
	
	private enum Dir {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		NONE
	};
	
	private Dir dir;
	
	
	public static AABB collider = null;
	
	
	public Player() {
		Renderer.entities.add(this);
		
		x = (Screen.current.width / 2) - 8;
		y = (Screen.current.height / 2) - 8;
		
		collider = new AABB(x, y, 32, 32, (byte)-1, -1);
		aTile = new AnimatedTile(new int[] {64, 65}, 10);
		hud = new HUD(this);
	}

	public void tick() {
		hud.tick();
		aTile.tick();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			isStill = false;
			if(!TileCollider.isColliding(-1)) {
				vel = moveSpeed;
				dir = Dir.LEFT;
				moveHor();
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			Random r = new Random(System.nanoTime());
			new Key(Level.toGridX(r.nextInt(20)), Level.toGridY(r.nextInt(20)), "KEY");
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			isStill = false;
			if(!TileCollider.isColliding(0)) {
				vel = -moveSpeed;
				dir = Dir.RIGHT;
				moveHor();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			isStill = false;
			if(!TileCollider.isColliding(1)) {
				vel = moveSpeed;
				dir = Dir.UP;
				moveVer();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			isStill = false;
			if(!TileCollider.isColliding(2)) {
				vel = -moveSpeed;
				dir = Dir.DOWN;
				moveVer();
			}
		}
		
		while(Mouse.next()) {
			if(Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					int mouseX = Mouse.getX();
					int mouseY = (-Mouse.getY() + Screen.current.height);
					

					new Bullet(Screen.current.width/2, Screen.current.height/2, -mouseX-Level.xOffset, -mouseY+Level.yOffset);
				}
			}
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_F) {
				if(Keyboard.getEventKeyState())
					LITW.DRAW_HITBOX = !LITW.DRAW_HITBOX;
			}else if(Keyboard.getEventKey() == Keyboard.KEY_R) {
				if(Keyboard.getEventKeyState())
					glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			}else if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				if(Keyboard.getEventKeyState())
					System.exit(0);
			}
			
			isStill = false;
		}
		
		if(counter % 20 == 0) {
			isStill = true;
			counter = 0;
		}
		counter++;
		vel = 0;
	}
	
	private void moveHor() {
		Level.xOffset = Level.xOffset + vel;
	}
	
	private void moveVer() {
		Level.yOffset = Level.yOffset + vel;
	}
	

	public void render() {
		hud.render();

		Font.draw(1, 1, "Lost_In_This_World v0.0.1 - Hunter Wilcox", 0x000000, 2, false, LITW.fontTextures);
		Font.draw(0, 0, "Lost_In_This_World v0.0.1 - Hunter Wilcox", 0xFFFFFF, 2, false, LITW.fontTextures);

		Font.draw(1, 9, "FPS: " + Renderer.getFPS(), 0x00000, 2, false, LITW.fontTextures);
		Font.draw(0, 8, "FPS: " + Renderer.getFPS(), 0xFFFFFF, 2, false, LITW.fontTextures);
		
		Font.draw(x - 32, y - 16, "JudgeGlass", 0xFFFFFF, 1, true, LITW.fontTextures);
		
		if(dir != Dir.UP) {
			Drawer.drawTexturedQuad(x, y, LITW.entityTextures, 0, 2);
			Drawer.drawTexturedQuad(x+16, y, LITW.entityTextures, 1, 2);
			
		}else {
			Drawer.drawTexturedQuad(x, y, LITW.entityTextures, 2, 2);
			Drawer.drawTexturedQuad(x+16, y, LITW.entityTextures, 3, 2);
			
		}
		
		if(isStill) {
			Drawer.drawTexturedQuad(x, y+16, LITW.entityTextures, 32, 2);
			Drawer.drawTexturedQuad(x+16, y+16, LITW.entityTextures, 33, 2);
		}else {
			if(aTile.getSprite() == 64) {
				Drawer.drawTexturedQuad(x, y+16, LITW.entityTextures, 64, 2);
				Drawer.drawTexturedQuad(x+16, y+16, LITW.entityTextures, 67, 2);
			}else {
				Drawer.drawTexturedQuad(x, y+16, LITW.entityTextures, 66, 2);
				Drawer.drawTexturedQuad(x+16, y+16, LITW.entityTextures, 65, 2);
			}
		}

		glPushAttrib(GL_CURRENT_BIT);
		glColor3f(0f, 1.0f, 1.0f);
		glBegin(GL_LINES);
			glVertex2f(x+8, y+8);
			glVertex2f(Mouse.getX(), (-Mouse.getY() + Screen.current.height));
		glEnd();
		glPopAttrib();
		
		if(LITW.DRAW_HITBOX) {
			collider.render();
		}
	}

	public int getHealth(){
		return health;
	}

	public int getOxygen(){
		return oxygen;
	}

	public int getX(){return x;}
	public int getY(){return y;}

	public HUD getHUD(){
		return hud;
	}

	public AABB getCollider(){
		return collider;
	}

}
