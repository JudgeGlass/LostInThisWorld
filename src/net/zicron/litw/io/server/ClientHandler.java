package net.zicron.litw.io.server;

import net.zicron.litw.gfx.Entity;
import net.zicron.litw.io.LevelLoader;
import net.zicron.litw.io.Log;
import net.zicron.litw.world.Player;
import org.lwjgl.examples.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler(Socket client) {
		this.client = client;
		
		try {
			setStream();
			listenLoop();
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void close() throws IOException {
		output.close();
		input.close();
		client.close();
	}
	
	private void setStream() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		input = new ObjectInputStream(client.getInputStream());

	}
	
	private void sendData(Object c) throws IOException {
		output.writeObject(c);
		output.flush();
	}

	private void sendInt(int i) throws IOException {
		output.writeInt(i);
		output.flush();
	}

	private void sendString(String i) throws IOException {
		output.writeUTF(i);
		output.flush();
	}

	private void sendByteArray(byte[] array) throws IOException{
		sendInt(array.length);
		output.write(array);
		output.flush();
	}
	
	private void listenLoop() throws IOException {
		while(client.isConnected()) {
			Log.info("Waiting...");
			String c = input.readUTF();
			Log.info("SERVER: " + c);
			parseCommand(c);
		}
	}

	private void parseCommand(String command) throws IOException {
		if(command.equals("GET_LEVEL")){
			LevelLoader ll = new LevelLoader("res/level1.txt");
			sendInt(ll.width);
			sendInt(ll.height);
			sendByteArray(ll.data);
		}else if(command.equals("REG_PLAYER")){
			int x = input.readInt();
			int y = input.readInt();
			int id = input.readInt();
			Entity e = new Player(x, y, id);
			e.itemId = -1;

			GameData.entities.add(e);
		}else if(command.equals("GET_ENTITY_LIST")){
			sendInt(GameData.entities.size());
			for(Entity e: GameData.entities){
				sendInt(e.itemId);
				sendInt(e.x);
				sendInt(e.y);
				sendInt(e.id);
			}
		}else if(command.equals("UPDATE_POS")){
			int id = input.readInt();
			int x = input.readInt();
			int y = input.readInt();

		}
	}
}
