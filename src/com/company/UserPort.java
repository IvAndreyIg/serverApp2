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

    public UserPort(Socket clientSocket, ServerSocket serverSocket) {
        this.serverSocket=serverSocket;
        this.clientSocket=clientSocket;

        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(),true);
            this.in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void closeIt(){


    }


    @Override
    public void run() {
        try {
            /*while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }*/
            int waiting=0;
            while (true) {
                if (in.hasNext()) {

                    String clientMessage = in.nextLine();
                    System.out.println("clientMessage"+clientMessage);
                    out.println("give chat ");
                }
                else {
                        System.out.println("NOPE");
                        waiting++;
                        if(waiting>10)
                            break;
                    }
                Thread.sleep(200);
            }
            System.out.println("EndCon");
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            // this.close();
        }
    }
}
