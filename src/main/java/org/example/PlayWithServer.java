package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.IllegalFormatException;
import java.util.Random;


public class PlayWithServer  extends Thread {

    private int ClientNbre;

    private int SecretNbre;
    private boolean fin;
    private String Winner;

    public static void main(String[] args) {
        new PlayWithServer().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            SecretNbre = new Random().nextInt(100);
            System.out.println(SecretNbre);
            System.out.println("Le serveur essaie de démarrer ....");
            while (true) {
                Socket s = ss.accept(); //Accept the client
                ++ClientNbre;
                new Communication(s,ClientNbre).start(); //Communication => class raha thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Classe interne
    public class Communication extends Thread {
        private Socket s;
        private int ClientNumber;

        Communication(Socket s,int ClientNumber){
            this.s = s;
            this.ClientNumber = ClientNumber;
        }
        public void start() {

        }
        @Override()

        public void run() {
            try {
                InputStream is = s.getInputStream(); //Octet
                InputStreamReader isr = new InputStreamReader(is); //Caractère
                BufferedReader br = new BufferedReader(isr); //Kat9ra une chaîne de caractère
                OutputStream os = s.getOutputStream(); //Kanktb Octet
                String Ip = s.getRemoteSocketAddress().toString();
                System.out.println("le Client numéro"+ClientNumber+" et son IP "+Ip);
                PrintWriter pw = new PrintWriter(os,true);
                pw.println("Vous etes le client"+ClientNumber);
                pw.println("Devinez le nombre secret ... :)");

                while(true) {
                    String UserRequest = br.readLine();
                    boolean RequestFormat = false;
                    int UserNbre = 0;
                    try {
                        UserNbre = Integer.parseInt(UserRequest);
                        RequestFormat = true;
                    } catch (NumberFormatException e) {
                        pw.println("3tini chi ra9m machi joumla !!");
                    }
                    if (RequestFormat) {


                    System.out.println("la Client" + ClientNbre+" a  envoyé le nombre" + UserNbre);
                    if (!fin) {
                        if (UserNbre > SecretNbre) pw.println("Votre nombre est supérieur au nombre secret");
                        else if (UserNbre < SecretNbre) pw.println("Votre nombre est inférieur au nombre secret");
                        else {
                            pw.println("A tbarkallah 3lik ..... jbtiha");
                            System.out.println("Le gagnant est le Client numéro" + ClientNumber + " et son IP " + Ip);
                            fin = true;
                        }
                    } else {
                        pw.println("Safi salina ..... :( w li Rab7 howa l client" + ClientNumber);
                    }

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

