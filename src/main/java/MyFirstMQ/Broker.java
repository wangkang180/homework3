package MyFirstMQ;

import java.util.concurrent.ArrayBlockingQueue;

/*
    消息处理中心
     */
public class Broker {

    private final static int MAX_SIZE = 5;

    private static ArrayBlockingQueue<String> MessageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    public  static  void  produce(String msg){
        if(MessageQueue.offer(msg)){
            System.out.println("成功向消息中心投递消息："+msg+ ",当前暂存消息数目为"+MessageQueue.size());
        }else{
            System.out.println("消息中心已满，不能继续放入消息！ ");
        }
        System.out.println("====================================");
    }

    public static String consum(){
        String msg = MessageQueue.poll();
        if(msg!=null){
            System.out.println("已经消费消息： "+msg +"，当前暂存消息数目为"+MessageQueue.size());
        }else{
            System.out.println("消息处理中心已经没有消息可供消费！");
        }
        System.out.println("=====================================");
        return msg;

    }




}
