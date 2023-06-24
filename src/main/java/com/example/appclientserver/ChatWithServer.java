package com.example.appclientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

        import java.nio.file.attribute.UserPrincipal;

        import java.util.Random;

        import java.util.IllegalFormatException;


public class ChatWithServer extends Thread{
    private int ClientNbr;
    private List<Communication> clientsConnectés=new ArrayList<Communication>();
    //envoyer un msg aux clients connectés sur le serveur

    public static void main(String[] args){

        new ChatWithServer().start();
    }
    @Override
    public void run(){

        try {
            ServerSocket ss=new ServerSocket(1234);
            System.out.println("le serveur essaie de démarrer");
            //   chaque client tache en parllèle
            while(true){
                Socket s=ss.accept();
                ++ClientNbr;

                Communication NewCommunincation=  new Communication(s,ClientNbr);//appel la methode run
                clientsConnectés.add(NewCommunincation);
                NewCommunincation.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //classe interne on utlise pour en capssuler les donnéées de la classes externe
    public class  Communication extends Thread{
        private Socket s;
        private int ClientNbr;
        Communication(Socket s,int ClientNbr){
            this.s=s;
            this.ClientNbr=ClientNbr;
        }
        @Override
        public void run(){
            try {
                InputStream is=s.getInputStream();//lire octet
                InputStreamReader isr=new InputStreamReader(is);//caractere
                BufferedReader br=new BufferedReader(isr);
                OutputStream os=s.getOutputStream();//ecrire octet
                String Ip= s.getRemoteSocketAddress().toString();
                System.out.println("le client numéro " + ClientNbr+" et son Ip " +Ip);
                PrintWriter pw=new PrintWriter(os,true);//ecrire chaque chaine seule
                pw.println(" Vous etes le client "+ ClientNbr);
                pw.println(" ************************* ");

                while(true) {
                    String UserRequest = br.readLine();
                    //msg un user  part
                    if(UserRequest.contains("=>")){
                        String[] usermessage=UserRequest.split("=>");
                        if(usermessage.length==2){
                            String message=usermessage[1];
                            int numClient=Integer.parseInt(usermessage[0]);
                            Send(message,s,numClient);
                        }
                    }
                    //envoyer un msg aux users
                    else{
                        Send(UserRequest,s,-1);

                    }



                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //envoyer un message aux users
        void Send(String UserRequest,Socket socket,int numCl) throws IOException {//socket pour filtrer les users
            for(Communication client : clientsConnectés) {
                if (client.s != socket) {
                    if (client.ClientNbr == numCl || numCl == -1) {
                        PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                        pw.println(UserRequest);
                    }

                }
            }

        }

    }
}


