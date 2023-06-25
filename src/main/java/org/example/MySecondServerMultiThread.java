package org.example;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MySecondServerMultiThread extends Thread {
//bach t9dar t lancer plus tâchrs simultan khasha t hérithui mn Thread ou l'interface runable

    private int ClientNbre;
    public static void main(String[] args) {

        new MySecondServerMultiThread().start();
        // ncriyiw nv objet naplikiw 3 lih la meth start=> at9alab 3la run ou t executi l code likin tma
    }

    @Override
    public void run() {
        // hana kin  l code libghinah t executa par serveur
        //Une fois dak thread kadir lih start kayexeécuti dakchi likin f la métho 'run'
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Le serveur essaie de démarrer ....");
            while (true) {
                Socket s = ss.accept(); //Accept the client
                ++ClientNbre; // Katgénéra chi connexionki tincrémenta
                new Communication(s,ClientNbre).start(); //Communication => class raha thread
               //t s -> socket dial lclient on a creer une comm avec le client
                //lanciw thread dial serveur
            }// finma yjini chi clients ntconnecta lih
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
        }//cpnstructeur kayakhoud s soket ta3 client
        public void start() {

        }
        public void run() {
            try {
                InputStream is = s.getInputStream(); //Octet
                InputStreamReader isr = new InputStreamReader(is); //Caractère
                BufferedReader br = new BufferedReader(isr); //Kat9ra une chaîne de caractère
                OutputStream os = s.getOutputStream(); //Kanktb Octet
                String Ip = s.getRemoteSocketAddress().toString();
                System.out.println("le Client numéro"+ClientNumber+" et son IP "+Ip);
                PrintWriter pw = new PrintWriter(os,true);// TRUE = println
                pw.println("Vous etes le client"+ClientNumber);
                while(true) {
                    String UserRequest = br.readLine();// String octet-> car-> string
                    pw.println("La Taille de votre chaînes de caractère"+UserRequest.length());
                }// Kat9ra l'utilisateur katjawou
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
