package net.zicron.litw.gfx;

import net.zicron.litw.LITW;

public abstract class Entity extends RenderObject{
	public String username = "PLAYER " + (byte)System.currentTimeMillis();
	public int id = LITW.createInstanceID();
	public int x;
	public int y;
	public byte itemId;
}
