package client.network;

import client.gui.GuiChat;

public class NetworkCommand extends Network
{
	public String[][] cmds = {
			{"CMD_CLEAN","c","clear","clearchat","cleanchat","cls","clean"},
			{"CMD_HELP","help","h"}
		};

	
	public void SendClientCommand(String name, String pass, String info)
	{
		String infostring = "command#" + name + "#" + pass + "#" + info;
		sendInfoToServer(infostring);
	}
	
	public void NewCommand(String name, String pass, String info)
	{
		String[] input = info.split(" ");
		String cmdString = "";
		
		for(int i = 0; i < cmds.length; i++)
		{
			for(int j = 1; j < cmds[i].length; j++)
			{
				if(input[0].equals(cmds[i][j]))
				{
					cmdString = cmds[i][0];
				}
			}
		}
		
		if(cmdString.equals("CMD_CLEAN"))
		{
			SendClientCommand(name, pass, "CLEAR_SCREEN");
		} 
		
		if(cmdString.equals("CMD_HELP"))
		{
			if(input.length < 2)
			{
				GuiChat.DisplayMessage("No parameter found, example: /help [page]");
			}
			else
			{
				GuiChat.DisplayMessage("you yelled for help but the server didn't anwser....");
			}
		} 
	}
}