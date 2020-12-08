package net.zicron.litw.io.server;

import java.util.ArrayList;
import java.util.List;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.io.LevelLoader;
import net.zicron.litw.world.Player;

public class GameData {
	private LevelLoader level;
	public static List<Player> players = new ArrayList<>();
	public static List<Entity> entities = new ArrayList<>();
}
