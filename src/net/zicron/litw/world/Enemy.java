package net.zicron.litw.world;

import javafx.application.Platform;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Entity;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.gfx.Screen;
import net.zicron.litw.io.Log;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.Bullet;
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
		if(counter % 20 == 0){
			if((Level.getPlayer().getX()-Level.xOffset) - x < 6*32 && (Level.getPlayer().getY()-Level.yOffset) - y < 6*32)
				new Bullet(x + 15, y + 15, Level.getPlayer().getX()-Level.xOffset-32, Level.getPlayer().getY()-Level.yOffset-32);
		}
		
		counter++;
	}

	public void render() {
		Drawer.drawQuad(x + Level.xOffset, y + Level.yOffset, 16, 16, 0x0000FF);
		collider.render();
	}

}
