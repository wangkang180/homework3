package MyFirstMQ;

import java.io.IOException;

public class ConsumeClient {
    public static void main(String[] args) throws IOException {
        MqClient client = new MqClient();
        String msg = client.consume();
        System.out.println("获取的消息为："+msg);
    }
}
