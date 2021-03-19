package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserPass implements Runnable {

    private static int start=Integer.parseInt(Main.property.getProperty("users.port.start"));

    private static int end=start+Integer.parseInt(Main.property.getProperty("users.port.count"));

    public static HashSet<String> users = new HashSet<String>();

    public static HashMap<Integer,UserPort> connections=new HashMap<Integer,UserPort>();

    private Runner runner;
    // исходящее сообщение
    private PrintWriter outMessage;
    // входящее собщение
    private Scanner inMessage;

    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиента в чате, статичное поле
    private static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер


    public boolean newConnnection(String login){
        int newPort=0;
        for (int p=start;p<end;p++){
            if(!connections.containsKey(p)){
                newPort=p;
                break;
            }
        }
       // System.out.print("end:"+end);
       //  System.out.print("new:"+newPort);
        if(newPort==0)return false;
        try {
            ServerSocket serverSocket = new ServerSocket(newPort);

            outMessage.println("port:" + newPort);
            
            
            
            
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            Scanner in = new Scanner(clientSocket.getInputStream());
            
           UserPort userPort=new UserPort(clientSocket,serverSocket,login,out,in);
            
           //new Thread(userPort).start();
            connections.put(newPort,userPort);
            users.add(login);
            out.println("History:"+"dwdwdw");
            int waiting=0;
              while (true) {
                if (in.hasNext()) {
                String clientMessage = in.nextLine();
              //  System.out.println("clientMessage"+clientMessage);
                
                if(clientMessage.contains("Message:")){
                
               // clientMessage.substring(8);
                Main.DP.newMessage(login, "all", clientMessage.substring(8));
                }
                
                if(clientMessage.contains("give:Online")){
                
                    String list="";
                    
                    
                            
                     for (String s : users) {
                    list+=s+"]";
                     }
               // clientMessage.substring(8);
               out.println("Online:"+list);
                //Main.DP.newMessage(login, "all", clientMessage.substring(8));
                }
                
                if(clientMessage.contains("give:History")){
                     
                     
                   // System.out.println("Hist"+Main.DP.getChat());
                    //System.out.println("Histot");
                out.println("History:"+Main.DP.getChat());
               // clientMessage.substring(8);
              //  Main.DP.newMessage(login, "all", clientMessage.substring(8));
                }
                
                
                
                
                }else {
                   // System.out.println("NOPEs3");
                    waiting++;
                    if(waiting>10)
                    break;
                }
              }
              clientSocket.close();
              serverSocket.close();
              users.remove(login);
              connections.remove(newPort);
          //  System.out.print("local"+clientSocket.getInetAddress());


            
           /* try{
               Thread.sleep(500);  
            }
            catch(Exception e){}*/
            
           
         //   

            

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public UserPass(Socket socket, Runner runner) {

        

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
            /*while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }*/
            int waiting=0;
            while (true) {
               
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
                             //   outMessage.println("ok:" + "user is cor");
                              //  newConnnection(login);
                               if(newConnnection(login)){
                                try {
                                    clientSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                               break;}
                               else{
                                   outMessage.println("Error:" + "ports are busy");
                               }
                            } else {
                                outMessage.println("Error:" + "login or pass is fail");
                            }

                        }






                        //users.contains(login)  ;


                    }


                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    /*if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }*/
                    // выводим в консоль сообщение (для теста)
                   // System.out.println(clientMessage);
                    // отправляем данное сообщение всем клиентам
                   // server.sendMessageToAllClients(clientMessage);
                }else {
                   // System.out.println("NOPE");
                    waiting++;
                    if(waiting>10){
                        try{
                            clientSocket.close();
                        }catch(Exception e){
                        }
                        
                    break;
                    
                    }
                    
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
    }
}
