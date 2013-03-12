package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import client.gui.*;
import client.sender.*;

 
public class Client extends JFrame
{   
	private static final long serialVersionUID = 1L;
	private Timer clienttick;
	
	private JLabel label1, label2, label3;
	private JTextField inputField, inputIP, inputPort;
    private JButton sendButton;
    
    private SendMessage message = new SendMessage();
    private SendCommand command = new SendCommand();
    public GuiChat chat;
    
    public int ClientID = 1;
    public String ClientName = "";
    public String ClientPass = "";
    public String ServerIP = "";
    public int ServerPort = 1;
 
    public Client()
    {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("DerpChat 2 - Client");
		setResizable(false);
		setVisible(true);
		
		chat = new GuiChat();
		
		label1 = new JLabel("MESSAGE:");
		label2 = new JLabel("HOST IP:");
		label3 = new JLabel("HOST PORT:");
		
    	inputField = new JTextField(15);
    	inputIP = new JTextField(15);
    	inputPort = new JTextField(15);
    	sendButton = new JButton("Send");
    	
    	label1.setBounds(10, 10, 200, 20); 
    	inputField.setBounds(10, 30, 200, 20); 
    	label2.setBounds(10, 60, 200, 20); 
    	inputIP.setBounds(10, 80, 200, 20); 
    	label3.setBounds(10, 110, 200, 20); 
    	inputPort.setBounds(10, 130, 200, 20); 
    	sendButton.setBounds(55, 170, 100, 20); 
    	
    	sendButton.addActionListener(new ActionListener()  
    	{
            public void actionPerformed(ActionEvent e)  
            {
            	char cmdcheck = inputField.getText().charAt(0);
            	if (cmdcheck == '/')
    			{
            		command.NewCommand("client-name", "client-pass", inputField.getText().substring(1), inputIP.getText(), Integer.parseInt(inputPort.getText()));
    			}
            	else
            	{
            		message.SendClientMessage("client-name", "client-pass", inputField.getText(), inputIP.getText(), Integer.parseInt(inputPort.getText()));
            	}
            	inputField.setText("");
            }
    	});

    	add(label1);
    	add(inputField);
    	add(label2);
    	add(inputIP);
    	add(label3);
    	add(inputPort);
    	add(sendButton);
		repaint();
		
		clienttick = new Timer(1000, new ActionListener()  
		{
            public void actionPerformed(ActionEvent e)  
            {
            	tick();
            }
        });  
		clienttick.start();
    }
    
    public void tick()
    {
    	
    }
    
    public static void main(String args[]) 
    {
    	new Client();
    }
}