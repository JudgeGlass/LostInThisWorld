package net.zicron.litw.logic;

import java.util.ArrayList;
import java.util.List;

public class Tiles {
    public int id;
    public boolean hasCollider;
    public String name;

    private static List<Tiles> gameTiles = new ArrayList<>();

    public Tiles(final int id, final boolean hasCollider, final String name){
        this.id = id;
        this.hasCollider = hasCollider;
        this.name = name;
    }

    public static void init(){
        Tiles.gameTiles.add(new Tiles(255, false, "NULL_TILE"));
        Tiles.gameTiles.add(new Tiles(0, true, "STONE_WALL"));
        Tiles.gameTiles.add(new Tiles(2, false, "STONE_TILE"));
        Tiles.gameTiles.add(new Tiles(1, true, "OUTER_STONE_WALL"));
        Tiles.gameTiles.add(new Tiles(3, false, "WATER_TILE"));
        Tiles.gameTiles.add(new Tiles(4, false, "SAND_TILE"));
    }

    public static Tiles getTileFromID(int id){
        Tiles tile = null;
        for(Tiles t: gameTiles){
            if(t.id == id){
                tile = t;
                break;
            }
        }

        if(tile == null)
            return gameTiles.get(0);

        return tile;
    }
}
