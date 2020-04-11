package lab3;

/*
    Политех, 2100 год. Преподавателей заменили роботами.
	В полуразрушенном гидрокорпусе три робота принимают три вида лабораторных работ:
	вышмат, ООП и физику (одни робот, один предмет). Так как перекрытия гидрокорпуса
	сильно изношены, студенты выстраиваются в очередь в кабинете вместимостью 10
	человек и далее проходят по кабинетам, остальные студенты нервно курят на улице
	и ждут своей очереди. Как только в кабинете освобождается место, заходит
	следующий студент. Мы знаем, что студенты выполнили либо 10, либо 20, либо 100
	заданий по одному из предметов. Робот за одну единицу времени принимает 5 работ.

    1. Сделать данный процесс параллельным используя java.util.concurrent
	2. Синхронизировать потоки и сохранить целостность данных.
		(Именно синхронизировать, а не ограничить)
	3. Разработать генератор студентов имеющий для параметра static int labsCount
		и static String subjectName. Работа генератора не должна зависеть
		от работы роботов.
	4. Общий ресурс должен быть Thread Safe
	5. Потоки не должны быть активными если нет задач.
	6. Потоки не должны держать mutex если нет задач.
 */

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService threads = Executors.newFixedThreadPool(4);

        BlockingDeque<Student> students = new LinkedBlockingDeque<Student>(10);

        threads.submit(new Generator(students));
        threads.submit(new Robot("Math", students));
        threads.submit(new Robot("OOP", students));
        threads.submit(new Robot("Physics", students));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong...");
            System.err.println(e.getMessage());
        } finally {
            threads.shutdownNow();
        }
    }
}
