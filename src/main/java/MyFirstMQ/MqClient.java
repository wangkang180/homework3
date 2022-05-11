package MyFirstMQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MqClient {
    public static void produce(String msg) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(msg);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String consume() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try{

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("CONSUME");
            out.flush();


        }catch (Exception e){
            e.printStackTrace();
        }
        String msg = in.readLine();
        return  msg;
    }
}
