package MyFirstMQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
消息队列服务
 */
public class BrokerServer implements Runnable{

    public static int SERVICE_PORT =9998;
    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true){
                String str = in.readLine();
                if(str==null){
                    continue;
                }
                System.out.println("接收到的原始数据为："+str);
                if(str.equals("CONSUME")){
                    String msg = Broker.consum();
                    out.println(msg);
                    out.flush();
                }else{
                    Broker.produce(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(BrokerServer.SERVICE_PORT);
        while (true){
            BrokerServer bs = new BrokerServer(server.accept());
            new Thread(bs).start();
        }
    }
}
