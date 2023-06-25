package org.example;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MyFirstServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        //On a cree un server socket le port lighantconnectaw bih
        System.out.println("Rani Kan tsana Khouna ytocenncta :) :)");
        Socket s = ss.accept();//Instruction bloquante
        //On accepte la connexion du client
        InputStream is = s.getInputStream(); // Lecture //prendre les inf du client = baghi
        //ytconecta l serveur 'serverSocket' avec le port 1234
        OutputStream os = s.getOutputStream(); // écrire
        //Retourner wahad l object OutoputStream bach labghit nktb 3nd l client
        System.out.println("On attent number :)"+s.getRemoteSocketAddress());
        int number = is.read();
        System.out.println("On a number :  "+number);
        int calc = number * 5;
        System.out.println("Ansift l réponse  "+calc); // Serveur aysift l reponse ayktb b 'os'
        os.write(calc);
        //un simple traitement le client donne un nombre on fait un calcule le serveur retourne la
        //valeur ayktb b 'write'
        s.close();




    }
}
