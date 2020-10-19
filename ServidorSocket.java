/*
 * ServidorSocket.java
 * 
 * 
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * ANTES DE USAR, LEIA AS INSTRUÇÕES EM LEIAME.TXT
 */


import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorSocket {
	
	public static void main (String[] args) throws IOException{
		
	ServerSocket serverSocket = new ServerSocket(9999);
	
	while (true){
		
		Socket s = null;	
		
		try{
				System.out.println("Iniciando o servidor");	
				s = serverSocket.accept();
				System.out.println("Servidor iniciado: " + s );
				
				DataInputStream in = new DataInputStream (s.getInputStream());
                DataOutputStream out = new DataOutputStream (s.getOutputStream());
				
				System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread GerenciadorDeClientesI = new GerenciadorDeClientesI(s, in, out);

                // Invoking the start() method
                GerenciadorDeClientesI.start();
             
				
				
		
		}catch (IOException err){
			
			serverSocket.close();	
			System.err.println(err);	
			
		}
		
	}	
		
	}
	
	
	
}

