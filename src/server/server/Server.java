package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GUIMain;
import gui.GUIStart;
import network.Listen;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Server extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final float version = 0.2f;

	private Timer servertick;
	
	public Server() {
		//new GUIStart();
		new GUIMain();
		new Listen();
		
		servertick = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tick();
            }
        });  
		servertick.start();
	}
	
	public static void main(String[] args) {
		new Server();
    }
	
	public void tick() {
		
	}
}