package net.zicron.litw.io;

public class TileTexture {
	public static final float[] STONE_TILE =
	{ 
	  0f, 0f,
	  1f/16f, 0,
	  1f/16f, 1f/16f,
	  0f, 1f/16f
	};
	
	public static final float[] STONE_WALL =
	{
	  1f/16f, 0f,
	  2f/16f, 0,
	  2f/16f, 1f/16f,
	  1f/16f, 1f/16f
	};

	public static final float[] OUTER_STONE_WALL =
	{
			2f/16f, 0f,
			3f/16f, 0f,
			3f/16f, 1f/16f,
			2f/16f, 1f/16f
	};

	public static final float[] WATER_TILE =
			{
					12f/16f, 15f/16f,
					13f/16f, 15f/16f,
					13f/16f, 1f,
					12f/16f, 1f
			};

	public static final float[] SAND_TILE = {
			3f/16f, 0f,
			4f/16f, 0f,
			4f/16f, 1f/16f,
			3f/16f, 1f/16f
	};
	
	public static final float[] NULL =
	{ 
	  0f, 15f/16f,
	  1f/16f, 15f/16f,
	  1f/16f, 16f/16f,
	  0f, 16f/16f
	};

	
	public static float[] getTextureById(byte id) {
		switch(id) {
			case 0:
				return STONE_WALL;
			case 2:
				return STONE_TILE;
			case 1:
				return OUTER_STONE_WALL;
			case 3:
				return WATER_TILE;
			case 4:
				return SAND_TILE;
			default:
				return NULL;
		}
	}
}
