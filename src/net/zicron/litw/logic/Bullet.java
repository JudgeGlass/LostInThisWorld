package net.zicron.litw.logic;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.io.Log;
import net.zicron.litw.world.Level;

import static org.lwjgl.opengl.GL11.*;

public class Bullet extends Entity{

	int x;
	int y;
	int x1;
	int y1;
	int x2;
	int y2;
	
	int counter = 0;
	
	private AABB collider;
	
	public Bullet(int x1, int y1, int x2, int y2) {
		this.x = x1;
		this.y = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		collider = new AABB(x1, y1, 8, 8, (byte)-2, -1);
		
		Renderer.addToEntityQueue(this);
	}
	
	public void tick() {
		if(counter % 2 == 0){
			x += (x2 - x1) / 8D;
			y += (y2 - y1) / 8;
		}
		

		
		counter++;
	}

	public void render() {
		int x = this.x + Level.xOffset;
		int y = this.y + Level.yOffset;

		glPushAttrib(GL_CURRENT_BIT);
		glColor3f(1.0f, 0.5f, 1.0f);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + 8, y);
			glVertex2f(x + 8, y + 8);
			glVertex2f(x, y + 8);
		glEnd();
		glPopAttrib();
		collider.render();
	}

}
