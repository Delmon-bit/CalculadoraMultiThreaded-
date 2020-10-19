/*
 * GerenciadorDeClientesII.java
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
import java.text.*;

public class GerenciadorDeClientesII extends Thread{
	
	Socket s;
	DataInputStream in; 
	DataOutputStream out;
	
	public GerenciadorDeClientesII(Socket s, DataInputStream in, DataOutputStream out)
	{
		this.s = s;
        this.in = in;
        this.out = out;
	}
		
	
	public void run()
	{
		
		int pos1,pos2,por=-1,rai=-1,pro=-1,pot=-1;
		String expressao, d="",d2="",resultado="";	
		
		while (true)
		{
			try {
					out.writeUTF("Qual a expressao (exemplos: porcentagem(50por100), potencia(5pot2) ou raiz(27rai3))?");
					expressao = in.readUTF();
					System.out.println(expressao); 
				
				if (expressao.equals("sair")){
				
				   System.out.println("Client " + this.s + " sends exit...");
				   System.out.println("Closing this connection.");
				   this.s.close();
				   System.out.println("Connection closed");
				   break; 
				   
				}
				
				por = expressao.indexOf("por");
				rai = expressao.indexOf("rai");
				pot = expressao.indexOf("pot");
				
				if (por != -1){
					pos1 = expressao.indexOf("p");
					pos2 = expressao.indexOf("r",pos1+1);
					d = expressao.substring(0,pos1);
					d2 = expressao.substring(pos2+1);
					resultado = ""+ ((Float.parseFloat(d)/100)* Float.parseFloat(d2));
				}else 
					if (rai != -1){
						pos1 = expressao.indexOf("r");
						pos2 = expressao.indexOf("i",pos1+1);
						d = expressao.substring(0,pos1);
						d2 = expressao.substring(pos2+1);
						resultado = ""+(Math.pow(Float.parseFloat(d), 1/Float.parseFloat(d2)));
					}else 
						if(pot != -1){
							pos1 = expressao.indexOf("p");
							pos2 = expressao.indexOf("t",pos1+1);
							d = expressao.substring(0,pos1);
							d2 = expressao.substring(pos2+1);
							resultado ="" +(Math.pow(Float.parseFloat(d),Float.parseFloat(d2)));
						}
				
				out.writeUTF(resultado);
				
			}
			
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		try
		{
			this.in.close();
			this.in.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
}

