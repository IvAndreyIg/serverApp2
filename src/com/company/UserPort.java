package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class UserPort implements Runnable   {

    private  PrintWriter out;
    private  Scanner in;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private String login;

    public UserPort(Socket clientSocket, ServerSocket serverSocket,
            String login,PrintWriter out,Scanner in) {
        
        this.serverSocket=serverSocket;
        this.clientSocket=clientSocket;
        this.login=login;
        this.in=in;
        this.out=out;
            if (in.hasNext()) {
                String clientMessage = in.nextLine();
                System.out.println("clientMessage"+clientMessage);
                }
        
    }


    private void closeIt(){


    }


    @Override
    public void run() {
      //  try {
            
            /*while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }*/
            
            //out.println("History:"+"fseffsefefsefsef\nfseffef");
             //System.out.println("clientShouldBe");
         //    
            while (true) {
                if (in.hasNext()) {
                String clientMessage = in.nextLine();
                System.out.println("clientMessage"+clientMessage);
                
                }
              }
        /*    
            //System.out.println("EndCon2");
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            // this.close();
        }*/
    }
}
