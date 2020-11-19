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
import net.zicron.litw.world.items.*;

import static org.lwjgl.opengl.GL11.*;

public class Level extends Entity{
	
	private TileCollider tileCollider = null;
	private LevelLoader level1 = null;

	private static Player player;
	public static int xOffset;
	public static int yOffset;
	
	public Level() {
		Renderer.entities.add(this);
		tileCollider = new TileCollider();
		level1 = new LevelLoader("res/level1.txt");
		Level.player = null;
		new Enemy(100, 100);
		new Key(150, 150, "KEY");
		new Key(170, 200, "KEY");
		new Key(100, 250, "KEY");
		new Map(130, 150, "MAP");
		new KeyHole(toGridX(20), toGridY(18), "KEYHOLE_1", new Gate(toGridX(20), toGridY(19), "GATE"));
		new Fire(toGridX(15), toGridY(15), "FIRE");
		new Fire(toGridX(16), toGridY(15), "FIRE");
		new Fire(toGridX(17), toGridY(15), "FIRE");
		new Fire(toGridX(15), toGridY(16), "FIRE");
		new Fire(toGridX(15), toGridY(17), "FIRE");
		new Fire(toGridX(15), toGridY(15), "FIRE");
		new Fire(toGridX(16), toGridY(17), "FIRE");

		
		xOffset-=40;
		addColliders();
	}
	
	private void addColliders() {
		for(int x = 0; x < level1.width; x++) {
			for(int y = 0; y < level1.height; y++) {
				byte data = level1.data[x + y * level1.width];
				Tiles tile = Tiles.getTileFromID(data);
				if(tile.hasCollider) {
					tileCollider.colliders.add(new AABB(x * 32, y * 32, 32, 32, data, -1));
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

	public static int toGridX(int x){
		return x * 32;
	}

	public static int toGridY(int y){
		return y * 32;
	}

	public void setPlayer(Player p){
		Level.player = p;
	}

	public static Player getPlayer(){
		return Level.player;
	}
}
