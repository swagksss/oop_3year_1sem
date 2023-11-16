package org.third.task;

public class Main {

    public static void main(String[] args) {
        // Create an instance of the ThreadService class
        ThreadService threadService = new ThreadService();

        // Create thread groups
        ThreadGroup threadGroup1 = new ThreadGroup("FIRST GROUP");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "SECOND GROUP");
        ThreadGroup threadGroup3 = new ThreadGroup(threadGroup2, "THIRD GROUP");

        // Create and start a thread in the first thread group
        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "firstThread").start();

        // Create and start two threads in the second thread group
        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "secondThread").start();

        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thirdThread").start();

        // Create and start a thread in the third thread group
        new Thread(threadGroup3, () -> {
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fourthThread").start();

        // Print information about threads in each thread group
        threadService.printTreadsInfo(threadGroup1);
        threadService.printTreadsInfo(threadGroup2);
        threadService.printTreadsInfo(threadGroup3);
    }
}
