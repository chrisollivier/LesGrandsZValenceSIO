package com.example.lagrandeevasion;


import com.example.lagrandeevasion.ui.home.HomeFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;


public class Client_Web {
	private HomeFragment IHM = null;
	Socket connexion;
	ThreadReception monThreadReception;
	InputStream ReceptionIS;
	InetAddress addr = null;

	public Client_Web(HomeFragment monIHM, String IP, int Port){
		try{	
			IHM = monIHM;
			connexion = new Socket();
			addr = InetAddress.getByName(IP);
			SocketAddress sockaddr = new InetSocketAddress(addr, Port);
			connexion.connect(sockaddr,4000);
			ReceptionIS = connexion.getInputStream();
			monThreadReception = new ThreadReception(IHM,ReceptionIS);
			monThreadReception.start();
			monIHM.setTextInstructions("Connexion réussie");
		}
		catch(UnknownHostException ex){
			monIHM.setTextInstructions("Impossible de se connecter au service\r");
		}
		catch(IOException ex){
			monIHM.setTextInstructions("Problème de communication\r");
		}
	}

	public void Fin_Communication(){
		monThreadReception.close();
		try {
			ReceptionIS.close();
			connexion.close();
			IHM.setTextInstructions("Connexion fermée");
			//IHM.setJlabel_Text(IHM.getJlabel_Text() + "\rConnexion fermée");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


