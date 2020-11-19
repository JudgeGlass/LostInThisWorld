package net.zicron.litw.logic;

import java.util.ArrayList;
import java.util.List;

import net.zicron.litw.LITW;
import net.zicron.litw.io.Log;
import net.zicron.litw.world.Level;
import net.zicron.litw.world.Player;

public class TileCollider {
	public static List<AABB> colliders = new ArrayList<>();
	private static AABB t = null;
	
	public static boolean checkCollision() {
		for(AABB collider: TileCollider.colliders) {
			int xx = collider.x * 32 + Level.xOffset;
			int yy = collider.y * 32 + Level.yOffset;
			Log.info("CX: " + xx);
			if(Level.isOutOfBounds(collider.x, collider.y)) {
				Log.info("SKIPPED");
				continue;
			}
			
			if(AABB.checkCollision(collider, Player.collider)) {
				Log.info("Player Hit");
				return true;
			}
		}
		return false;
	}
	
	public static boolean isColliding(int dir) {
		AABB playerCol = Player.collider;
		
		if(dir == 0)
			t = new AABB(playerCol.x, playerCol.y, 34, 32, (byte)-1, -1);
		else if(dir == -1)
			t = new AABB(playerCol.x-4, playerCol.y, 32, 32, (byte)-1, -1);
		else if(dir == 1)
			t = new AABB(playerCol.x, playerCol.y-4, 32, 32, (byte)-1, -1);
		else if(dir == 2)
			t = new AABB(playerCol.x, playerCol.y, 32, 34, (byte)-1, -1);
		
		for(AABB collider: colliders) {
			if(Level.isOutOfBounds(collider.x, collider.y)) {
				continue;
			}			
			
			if(AABB.checkCollision(collider, t)) {
				t = null;
				return  true;
			}
		}
		
		return false;
	}

	public static void removeCollider(int instance){
		int removeIndex = -1;
		for(int i = 0; i < colliders.size(); i++){
			if(colliders.get(i).instance == instance){
				removeIndex = i;
				break;
			}
		}

		if(removeIndex != -1){
			colliders.remove(removeIndex);
		}
	}
	
	public static boolean isColliding(AABB b1) {
		for(AABB collider: colliders) {
			if(AABB.checkCollision(collider, b1)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void updateAABB() {
		for(AABB collider: TileCollider.colliders) {
			collider.x = Level.xOffset + (collider.ox);
			collider.y = Level.yOffset + (collider.oy);
		}
	}
	
	public void render() {
		if(LITW.DRAW_HITBOX) {
			if(t != null) {
				t.render();
			}
			for(AABB collider: TileCollider.colliders) {
				collider.render();
			}
		}
	}
}
