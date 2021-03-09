package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UserPort /*implements Runnable*/ {


   /* public UserPort(Socket socket, Runner runner) {

        users.add("admin2");

        try {
            clients_count++;
            this.runner = runner;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream(),true);
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            *//*while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }*//*
            int waiting=0;
            while (true) {
                // Если от клиента пришло сообщение
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();

                    //  clientMessage.indexOf("log:")
                    if(clientMessage.contains("log:") && clientMessage.contains("pass:")) {

                        String login=clientMessage.substring(4, clientMessage.indexOf("pass:") - 1),
                                pass=clientMessage.substring(clientMessage.indexOf("pass:") + 5, clientMessage.length());
                        System.out.println(
                                "Log:" + login +
                                        "\npasss:" + pass


                        );

                        //outMessage.println("Error:" +"user is logged");
                        System.out.println("this:");
                        System.out.println(users.contains(login));
                        if(users.contains(login)){
                            outMessage.println("Error:" + "user is logged");



                        }else{
                            boolean isCorrect = Main.DP.checkUser(login, pass);
                            if (isCorrect) {
                                outMessage.println("ok:" + "user is cor");


                            } else {
                                outMessage.println("Error:" + "login or pass is fail");
                            }

                        }






                        //users.contains(login)  ;


                    }


                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    *//*if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }*//*
                    // выводим в консоль сообщение (для теста)
                    // System.out.println(clientMessage);
                    // отправляем данное сообщение всем клиентам
                    // server.sendMessageToAllClients(clientMessage);
                }else {
                    System.out.println("NOPE");
                    waiting++;
                    if(waiting>10)
                        break;
                }

                // останавливаем выполнение потока на 100 мс
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
    }*/
}
