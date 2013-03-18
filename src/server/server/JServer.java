package server;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import server.file.FileCreate;
import server.file.FileRead;
import server.file.FileWrite;
import server.gui.GUI;
import server.network.Listen;

public class JServer {
	public static Boolean debug = false;
	
	public static final String version = "1.0";
	public static final String rootDir = getRoot() + "\\server\\";
	
	public static int serverPort = 1337;
	
	public static void main(String[] args) {
		// Get and handle Command line arguments
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("debug")) debug = true;
			}
		}

		// Create main GUI
		new GUI();
		GUI.Append("Starting server on: " + GetIP() + ":" + serverPort);
		
		// Handle files
		if (!debug) {
			GUI.Append("Checking for server files..");
			if (!FileCreate.Check()) {
				GUI.Append("  - Files missing! Creating..");
				if (!FileCreate.Create()) CloseWithError("Server could not create files.");
				if (!FileWrite.WriteConfig()) CloseWithError("Could not write to files.");
				GUI.Append("  - Files Created!");
			} else {
				GUI.Append("  - Files found.");
				FileRead.Read();
				if (!FileRead.ReadConfig()) GiveWarning("Config file could not be read!");
			}
		} else {
			GUI.Append("Running in debug mode!");
		}
		
		// Start listening
		GUI.Append("Starting server services");
		new Listen();
	}
	
	// Returns the root directory of the JAR file
	private static String getRoot() {
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		return path;
	}
	
	// Returns the current system time
	public static String getTime() {
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	return sdf.format(cal.getTime());
	}
	
	// Close application with error
	public static void CloseWithError(String err) {
		JOptionPane.showMessageDialog(null, err, "Server Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	// Close application with message
	public static void CloseWithMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		System.exit(0);
	}
	
	// Continue the application with warning
	public static void GiveWarning(String war) {
		JOptionPane.showMessageDialog(null, war, "Server Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	// Ask for close
	public static int AskClose(String que, String queT) {
		int pane = JOptionPane.showConfirmDialog (null, que, queT, JOptionPane.YES_NO_OPTION);
		
		if (pane == JOptionPane.YES_OPTION) {
			return 0;
		} else {
			return 1;
		}
	}
	
	// Get the server's IP adress
	public static String GetIP() {
		String ip = null;
		try {
			URL site = new URL("http://api.exip.org/?call=ip");
			BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
			ip = in.readLine();
		} catch (Exception e) {
			try {
				URL site = new URL("http://icanhazip.com/");
				BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
				ip = in.readLine();
			} catch (Exception ex) {
				GiveWarning("External IP adress could not be resolved");
				ip = "0.0.0.0";
			}
		}
		return ip;
	}
	
	// Get the local IP adress of the server
	public static String GetLocalIP() {
		InetAddress address = null;
		String ip = null;
		
		try {
			address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
		} catch(Exception ex) {
			GiveWarning("Local IP adress could not be resolved");
			ip = "0.0.0.0";
		}
		
		return ip;
	}
}
