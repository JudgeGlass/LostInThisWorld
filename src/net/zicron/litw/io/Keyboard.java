package net.zicron.litw.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private static String buffer;

	public static String getBuffer(){
		return Keyboard.buffer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		buffer += e.getKeyChar();
		Log.info(buffer);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		Log.info(e.getKeyChar()+"");
	}
	
}
