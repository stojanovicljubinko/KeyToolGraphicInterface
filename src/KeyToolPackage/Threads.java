package KeyToolPackage;

import static java.lang.Thread.currentThread;
import java.util.LinkedList;
import java.util.Queue;

public class Threads {

    static class ThreadPool {

        Queue<Runnable> tasks = new LinkedList<>();

        public ThreadPool() {
            Thread mainWorker = new Thread(() -> {
                while (true) {
                    Runnable newTaskToBeExecuted = tasks.poll();
                    if (newTaskToBeExecuted != null) {
                        newTaskToBeExecuted.run();
                    }
                }
            }, "Main Worker");
            mainWorker.start();
        }

        public void execute(Runnable newTask) {
            tasks.add(newTask);
        }
    }

    public static void main(String[] args) {
        Runnable firstTask = () -> System.out.println("Prvi zadatak koji se pokreće je nit: " + currentThread().getName());
        Runnable secondTask = () -> System.out.println("Drugi zadatak koji se pokreće je nit: " + currentThread().getName());

        ThreadPool mySingleThreadPool = new ThreadPool();
        mySingleThreadPool.execute(firstTask);
        mySingleThreadPool.execute(secondTask);
    }

}

//if (t1.getState() != Thread.State.TERMINATED) {
//            t1.start();
//        }
