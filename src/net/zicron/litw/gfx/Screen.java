package net.zicron.litw.gfx;

import net.zicron.litw.io.Keyboard;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	
	public static Screen current = null;
	
	public int width;
	public int height;

	private JFrame frame;
	private Canvas canvas;
	
	public Screen(int sw, int sh) {
		width = sw;
		height = sh;
		Screen.current = this;
		frame = new JFrame("LWJGL");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		canvas = new Canvas();
		canvas.addKeyListener(new Keyboard());
		canvas.setFocusable(true);
		frame.add(canvas, BorderLayout.CENTER);

	}
	
	public void init() {
		try {
			frame.setVisible(true);
			frame.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					width = canvas.getWidth();
					height = canvas.getHeight();
					Renderer.screenRefresh = true;
				}
			});
			Display.setParent(canvas);

			Display.create(new PixelFormat(), new ContextAttribs(1,1));
		}catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
}
