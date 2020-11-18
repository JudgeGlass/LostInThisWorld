package net.zicron.litw.world;

import net.zicron.litw.LITW;
import net.zicron.litw.gfx.Drawer;
import net.zicron.litw.gfx.Entity;
import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.gfx.Screen;
import net.zicron.litw.io.LevelLoader;
import net.zicron.litw.logic.AABB;
import net.zicron.litw.logic.TileCollider;
import net.zicron.litw.logic.Tiles;

import static org.lwjgl.opengl.GL11.*;

public class Level extends Entity{
	
	TileCollider tileCollider = null;
	LevelLoader level1 = null;
	
	public static int xOffset;
	public static int yOffset;
	
	public Level() {
		Renderer.entities.add(this);		
		tileCollider = new TileCollider();
		level1 = new LevelLoader("res/level1.txt");
		//new Enemy(100, 100);
		new Key(50, 50, "KEY");
		
		xOffset-=40;
		addColliders();
	}
	
	private void addColliders() {
		for(int x = 0; x < level1.width; x++) {
			for(int y = 0; y < level1.height; y++) {
				byte data = level1.data[x + y * level1.width];
				Tiles tile = Tiles.getTileFromID(data);
				if(tile.hasCollider) {
					tileCollider.colliders.add(new AABB(x * 32, y * 32, 32, 32, data));
				}
			}
		}
	}
	
	public static boolean isOutOfBounds(int xx, int yy) {
		return !((xx < Screen.current.width) && (xx > -32) && (yy < Screen.current.height) && (yy > -32));
	}
	

	public void tick() {
		//AABB collider = TileCollider.colliders.get(0);
		//collider.x = xOffset;
		tileCollider.updateAABB();
	}

	public void render() {
		//Log.info("Drawing");
		glEnable(GL_TEXTURE_2D);
		for(int x = 0; x < level1.width; x++) {
			for(int y = 0; y < level1.height; y++) {
				byte data = level1.data[x + y * level1.width];
				
				LITW.gameTextures.bind();
				
				int xx = (x * 32) + xOffset;
				int yy = y * 32 + yOffset;
				
				if(!isOutOfBounds(xx, yy)) {
					Drawer.drawTexturedQuad(x, y, xOffset, yOffset, LITW.gameTextures, data);
				}
				
			}
		}
		glDisable(GL_TEXTURE_2D);
		tileCollider.render();
	}

}
