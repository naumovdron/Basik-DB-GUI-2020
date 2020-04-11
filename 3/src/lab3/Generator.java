package lab3;

import java.util.concurrent.BlockingDeque;

public class Generator implements Runnable {
    public Generator(BlockingDeque<Student> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    if (queue.remainingCapacity() == 0) {
                        queue.wait();
                    }
                    while (queue.remainingCapacity() > 0) {
                        queue.addLast(new Student(generateLabsCount(), generateSubjectName()));
                        System.out.println("add new student");
                    }
                    queue.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
                System.err.println(e.getMessage());
                return;
            }
        }
    }

    final private BlockingDeque<Student> queue;

    private int generateLabsCount() {
        int count = (int) (Math.random() * 3) + 1;
        switch (count) {
            case 1:
            case 2:
                count *= 10;
                break;
            case 3:
                count = 100;
                break;
        }
        return count;
    }

    private String generateSubjectName() {
        int subjectNumber = (int) (Math.random() * 3) + 1;
        switch (subjectNumber) {
            case 1:
                return "Math";
            case 2:
                return "OOP";
            default:
                return "Physics";
        }
    }
}
