/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author
 */
public class ExecThread extends Thread {

    boolean executing;

    public ExecThread() {
        super();
    }

    public ExecThread(boolean b) {
        executing = b;
    }

    public boolean isExecuting() {
        return executing;
    }

        public void doSuspend() {
        executing = false;
    }

    public synchronized void doResume() {
        executing = true;
        notify();
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    public void syncronizeAndDoTheRest() {
        try {
            sleep(500);
        } catch (InterruptedException ex) {
        }
        synchronized (this) {
            while (!isExecuting()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
