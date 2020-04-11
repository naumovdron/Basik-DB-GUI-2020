package lab3;

import java.util.concurrent.BlockingDeque;

public class Robot implements Runnable {
    public Robot(String subject, BlockingDeque<Student> queue) {
        this.subject = subject;
        this.queue = queue;
    }

    @Override
    public void run() {
        int tempLabsQuantity;
        while (true) {
            try {
                synchronized (queue) {
                    if (queue.isEmpty() || !queue.getFirst().subject.equals(subject)) {
                        queue.wait();
                    }
                    System.out.println(subject);
                    tempLabsQuantity = queue.getFirst().labsQuantity;
                    queue.removeFirst();
                    queue.notifyAll();
                }
                while (tempLabsQuantity > 0) {
                    tempLabsQuantity -= labsPerTime;
                }
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
                System.err.println(e.getMessage());
                return;
            }
        }
    }

    @Override
    public String toString() {
        return subject;
    }

    final int labsPerTime = 5;

    final private BlockingDeque<Student> queue;
    final private String subject;
}
