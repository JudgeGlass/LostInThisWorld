package net.zicron.litw.gfx;

import net.zicron.litw.io.Texture;
import net.zicron.litw.io.TileTexture;

import static org.lwjgl.opengl.GL11.*;

public class Drawer {
	public static void drawTexturedQuad(int x, int y, int xOffset, int yOffset, Texture t, byte tile) {

		
		float[] uvs = TileTexture.getTextureById(tile);
				
		glBegin(GL_QUADS);
			glTexCoord2f(uvs[0], uvs[1]);
			glVertex2f(x * 32 + xOffset, y * 32 + yOffset);
			glTexCoord2f(uvs[2], uvs[3]);
			glVertex2f((x * 32) + 32 + xOffset, y * 32 + yOffset);
			glTexCoord2f(uvs[4], uvs[5]);
			glVertex2f((x * 32) + 32 + xOffset, (y * 32) + 32 + yOffset);
			glTexCoord2f(uvs[6], uvs[7]);
			glVertex2f(x * 32 + xOffset, (y * 32) + 32 + yOffset);
		glEnd();
	}
	
	public static void drawTexturedQuad(int x, int y, Texture t, int index, int scale) {
		t.bind();
		
		int xx = index % 32;
		int yy = index / 32;
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f((xx / 32f), (yy / 32f));
			glVertex2f(x, y);
			glTexCoord2f((xx + 1) / 32f, yy / 32f);
			glVertex2f((x+8 * scale), y);
			glTexCoord2f((xx+1) / 32f, (yy+1) / 32f);
			glVertex2f((x + 8 * scale), (y + 8 * scale));
			glTexCoord2f(xx / 32f, (yy+1) / 32f);
			glVertex2f(x, (y + 8 * scale));
		glEnd();
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
	}
	
	public static void drawLine(int x1, int y1, int x2, int y2) {
		glBegin(GL_LINES);
			glVertex2i(x1, y1);
			glVertex2i(x2, y2);
		glEnd();
	}
	
	public static void drawQuad(int x, int y, int w, int h, int color) {
		int r = (color & 0xFF0000) >> 16;
	    int g = (color & 0xFF00) >> 8;
	    int b = (color & 0xFF);

	    glPushAttrib(GL_CURRENT_BIT);
	    glColor3f(r/255f, g/255f, b/255f);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + w, y);
			glVertex2f(x + w, y + h);
			glVertex2f(x, y + h);
		glEnd();
		glPopAttrib();
	}
	
	public static void enable(int function) {
		glEnable(function);
	}
	
	public static void disable(int function) {
		glDisable(function);
	}
}
