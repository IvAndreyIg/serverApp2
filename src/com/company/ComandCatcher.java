package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ComandCatcher implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;

    public  void setCommandSetter(Integer commandSetter){
        updateValue(commandSetter, true);
    }
    public Integer getCommandSetter(){
        return updateValue(CommandSetter, false);
    }
    public Integer updateValue(Integer value,boolean set){
        synchronized(this){
            if(set)
                this.CommandSetter = value;
            return value;
        }
    }


    private Integer CommandSetter;

    public ComandCatcher(int port ) {
        CommandSetter=0;
        try {
             serverSocket = new ServerSocket(port);
             socket = serverSocket.accept();
             scanner = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(),true);
//printWriter.println("bla");/*

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Start(){
        while(scanner.hasNextLine()){

           // CommandSetter=;
          //  printWriter.println("you've send:"+str);
         //   String b=scanner.nextLine();
            int foo;

            try {
                foo = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
                foo = 0;
            }

          // int a=Integer.parseInt(scanner.nextLine());
            System.out.println(foo);
            System.out.println(foo+100000);


        }
    }
    public static void main(String[] args) throws InterruptedException {
        final ComandCatcher comandCatcher = new ComandCatcher(13);
       // comandCatcher.Start();
        System.out.println("start2");
        new Thread(comandCatcher).start();
        System.out.println("start3");
        Thread.sleep(10000);
        System.out.println("spec3:"+comandCatcher.getCommandSetter());
    }

    @Override
    public void run() {
        while(scanner.hasNextLine()){

            // CommandSetter=;
            //  printWriter.println("you've send:"+str);
            //   String b=scanner.nextLine();
            int foo;

            try {
                setCommandSetter(Integer.parseInt(scanner.nextLine()));
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
                CommandSetter = 0;
            }

            // int a=Integer.parseInt(scanner.nextLine());
            //  setCommandSetter(CommandSetter);
            //System.out.println(getCommandSetter());
            //System.out.println(getCommandSetter());


        }
    }
}
