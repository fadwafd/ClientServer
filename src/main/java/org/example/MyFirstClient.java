package org.example;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyFirstClient {
    public static void main(String[] args) throws IOException {
        System.out.println("AN tconnecta l serveur");
        Socket s = new Socket("localhost",1234);
        // We create the socket of the client we need 2thing @ip of the server= localhost and the the port nbr
        InputStream is = s.getInputStream(); // we read smth from the server
        OutputStream os = s.getOutputStream(); // write smth and give it to the server
        //Chi haja atkhrouj mn 3ndi
        Scanner sc = new Scanner(System.in);
        System.out.println("ara lina chi number");
        int nbre = sc.nextInt();
        System.out.println("ghadi nsift le nombre l serveur "+nbre);
        os.write(nbre);  // anktbou f serveur
        System.out.println("Kantsna serveur y jawbni :)"); // On doit récupérer la réponse
        int jawab = is.read(); //Récupérer
        System.out.println("La reponse dial serveur"+jawab);


    }
}
