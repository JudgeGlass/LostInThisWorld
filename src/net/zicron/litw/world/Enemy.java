package net.zicron.litw.world;

import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Entity;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.TileCollider;

public class Enemy extends Entity{

	private int x;
	private int y;
	private int ox;
	private int oy;
	
	private AABB collider;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		ox = x;
		oy = y;
		
		collider = new AABB(x, y, 16, 16, (byte)254, -1);
		TileCollider.colliders.add(collider);
		
		Renderer.addToEntityQueue(this);
	}
	
	int counter = 0;
	public void tick() {
		x = Level.xOffset + ox;
		y = Level.yOffset + oy + ((int)(Math.sin(counter) * 10));
		collider.x = x;
		collider.y = y;
		
		counter++;
	}

	public void render() {
		Drawer.drawQuad(x, y, 16, 16, 0x0000FF);
		collider.render();
	}

}
