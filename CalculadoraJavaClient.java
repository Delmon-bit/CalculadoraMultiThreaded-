/*
 * CalculadoraJavaClient.java
 * 
 * ANTES DE USAR, LEIA AS INSTRUÇÕES EM NO LEIAME.TXT
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


import java.io.*;
import java.net.*;
import java.util.*;

public class CalculadoraJavaClient {
	
	public static void main (String[] args) throws IOException{
		
		
		try
		{
			Scanner ler = new Scanner(System.in);
			Socket cliente = new Socket ("127.0.0.1", 9999);
			Socket clienteI = new Socket ("127.0.0.1", 9998);
			DataInputStream in = new DataInputStream (cliente.getInputStream());
			DataOutputStream out = new DataOutputStream (cliente.getOutputStream());
			DataInputStream inI = new DataInputStream (clienteI.getInputStream());
			DataOutputStream outI = new DataOutputStream (clienteI.getOutputStream());
			
			String opcao; 
			String expressao="";;
			
			while(true)
			{
						//fazer escolher entre servidor 1 ou 2
						System.out.println("Operações básicas(1) ou especiais(2)?");
						opcao = ler.nextLine();
						if (opcao.equals("1")){
							System.out.println(in.readUTF());
							expressao = ler.nextLine();
							out.writeUTF(expressao);
						}else if (opcao.equals("2")){
							System.out.println(inI.readUTF());
						    expressao = ler.nextLine();
						    outI.writeUTF(expressao);
						}else if (opcao.equals("sair")){
							expressao = "sair";
						}
						
						//fazer escolher entre servidor 1 ou 2
					
						/*System.out.println(inI.readUTF());
						String expressao = ler.nextLine();
						outI.writeUTF(expressao);
					
						System.out.println(in.readUTF());
						String expressao = ler.nextLine();
						out.writeUTF(expressao);*/
				
					
			  
					if (expressao.equals("sair"))
					{
						System.out.println("Closing this connection : " + cliente);
						System.out.println("Closing this connection : " + clienteI);
						cliente.close();
						clienteI.close();
						System.out.println("Connection closed");
						break;
									
					}
					
					if (opcao.equals("1")){
							String resultado = in.readUTF();
							System.out.println(resultado);
						}else if (opcao.equals("2")){
							String resultado = inI.readUTF();
							System.out.println(resultado);
						}
					
					/*String resultado = inI.readUTF();
					String resultado = in.readUTF();
					System.out.println(resultado);*/
			
			};
			ler.close();
			in.close();
			out.close();
			inI.close();
			outI.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

