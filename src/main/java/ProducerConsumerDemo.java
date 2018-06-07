import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by kkk on 2016/4/19.
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Queue<Student> queue = new LinkedList<>();
        new Thread(new Prunnable(queue,"P1")).start();
        new Thread(new Crunnable(queue,"C1")).start();
        new Thread(new Prunnable(queue,"P2")).start();
        new Thread(new Crunnable(queue,"C2")).start();
        new Thread(new Prunnable(queue,"P3")).start();
        new Thread(new Crunnable(queue,"C3")).start();
        new Thread(new Prunnable(queue,"P4")).start();
        new Thread(new Crunnable(queue,"C4")).start();
    }
}
class Prunnable implements Runnable{
    String name;
    int MaxSize=200;//maxsize
    Queue<Student> queue;
    public Prunnable(Queue<Student> queue,String name){
        this.queue=queue;
        this.name=name;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (queue) {//存放Student队列
                while (queue.size() == MaxSize) {//队列满则wait 用while 不用if
                    System.out.println("queue is full "+name);
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int age = (new Random().nextInt()) % 100;
                Student s = new Student(age, "sb" + age);
                queue.add(s);
                System.out.println(name+"  add : " + s);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.notifyAll();//通知消费者取走
            }
        }
    }
}
class Crunnable implements Runnable{
    String name;
    Queue<Student> queue;
    public Crunnable(Queue<Student> queue,String name){
        this.queue=queue;
        this.name= name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("queue is empty, "+name);
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name+" consume :" + queue.remove());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.notifyAll();
            }
        }
    }
}
