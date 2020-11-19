package net.zicron.litw.logic;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.io.Log;
import net.zicron.litw.world.Level;

import static org.lwjgl.opengl.GL11.*;

public class Bullet extends Entity{

	int rise;
	int run;
	int x;
	int y;
	
	int counter = 0;
	
	private AABB collider;
	
	public Bullet(int x, int y, int rise, int run) {
		this.x = x;
		this.y = y;
		this.rise = rise;
		this.run = run;
		
		collider = new AABB(x, y, 8, 8, (byte)-2, -1);
		
		Renderer.addToEntityQueue(this);
	}
	
	public void tick() {
		int x = this.x + Level.xOffset;
		int y = this.y + Level.yOffset;

		if(counter % 60 == 0) {
			x += run/10;
			y += rise/10;
			//addToPos(run/10, rise/10);
		}

		collider.setX(x);
		collider.setY(y);
		this.x = x;
		this.y = y;
		
		if(TileCollider.isColliding(collider)) {
			Renderer.destroy(this);
		}
		
		counter++;
	}
	
	private void addToPos(int xx, int yy) {
		//x = Level.xOffset + collider.ox + xx;
		Log.info("X: " + x);
		//y = Level.yOffset + collider.oy + yy;
	}

	public void render() {
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
