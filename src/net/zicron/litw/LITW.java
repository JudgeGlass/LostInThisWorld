package net.zicron.litw;

import net.zicron.litw.gfx.Renderer;
import net.zicron.litw.gfx.Screen;
import net.zicron.litw.gfx.gui.TextField;
import net.zicron.litw.io.Audio;
import net.zicron.litw.io.Texture;
import net.zicron.litw.logic.Tiles;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.Player;

import java.util.Random;


// Lost in this World
public class LITW {
	
	public static boolean DRAW_HITBOX = false;
	public static boolean isRunning = false;
	
	public static Texture gameTextures;
	public static Texture fontTextures;
	public static Texture entityTextures;
	
	public static void main(String[] args) {
		Screen gameScreen = new Screen(800, 480);
		gameScreen.init();
		
		Renderer gameRenderer = new Renderer();
		
		isRunning = true;
		
		//new Thread(() -> new Server(800).start()).start();
		
		gameTextures = new Texture("res/atlas.png");
		entityTextures = new Texture("res/entityAtlas.png");
		entityTextures.pw = 8;
		entityTextures.ph = 8;
		fontTextures = new Texture("res/fontAtlas.png");
		fontTextures.pw = 8;
		fontTextures.ph = 8;


		Tiles.init();
		Level level = new Level();
		Player player = new Player();
		level.setPlayer(player);
		gameRenderer.enableVsync(true);
		gameRenderer.startLoop();
	}

	public static int createInstanceID(){
		Random r = new Random(Integer.MAX_VALUE);
		r.setSeed(System.nanoTime());
		return r.nextInt();
	}
}
