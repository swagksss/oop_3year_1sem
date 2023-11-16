package org.third.task;

public class ThreadService {

    // Interval for printing thread information
    private final int INTERVAL = 5000;

    // Public method to initiate the thread for printing thread information
    public void printTreadsInfo(ThreadGroup threadGroup){
        new Thread(() -> printInfoWithIntervals(threadGroup)).start();
    }

    // Private method to continuously print thread information at intervals
    private void printInfoWithIntervals(ThreadGroup threadGroup) {
        while(threadGroup.activeCount() > 0){
            printInfoAboutGroup(threadGroup, 0);
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Private method to recursively print information about the thread group and its threads
    private synchronized void printInfoAboutGroup(ThreadGroup threadGroup, int levelOfThread) {
        // Print the name of the current thread group with proper indentation
        printWithIndent(threadGroup.getName(), levelOfThread);

        // Get and print information about threads in the current thread group
        Thread[] threadsInCurrentGroup = new Thread[threadGroup.activeCount()];
        int threadsCount = threadGroup.enumerate(threadsInCurrentGroup, false);
        if(threadsCount > 0){
            printWithIndent("Threads at " + threadGroup.getName() + ":", levelOfThread);
            for(int i = 0; i < threadsCount; i++ ){
                printWithIndent(threadsInCurrentGroup[i].getName(), levelOfThread + 1);
            }
        }

        // Get and print information about sub-thread groups in the current thread group
        ThreadGroup[] threadGroups = new ThreadGroup[threadGroup.activeGroupCount()];
        int groupsCount = threadGroup.enumerate(threadGroups, false);
        if(groupsCount > 0){
            printWithIndent("Thread groups in " + threadGroup.getName() + ":", levelOfThread);
            for(int i = 0; i < groupsCount; i++){
                printInfoAboutGroup(threadGroups[i], levelOfThread + 1);
            }
        }
    }

    // Private method to print a string with proper indentation
    private void printWithIndent(String name, int levelOfThread) {
        for(int i = 0; i < levelOfThread; i++){
            System.out.print("  ");
        }
        System.out.print(name + System.lineSeparator());
    }
}
