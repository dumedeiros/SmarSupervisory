///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package FromControle;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Fox
// */
//public class MyThread extends Thread {
//
//    public MyThread() {
//        super();
//    }
//
//    public MyThread(boolean b) {
//        executing = b;
//    }
//    
//    boolean executing;
//
//    public boolean isExecuting() {
//        return executing;
//    }
//
//    public void doSuspend() {
//        executing = false;
//    }
//
//    public synchronized void doResume() {
//        executing = true;
//        notify();
//    }
//
//    public void setExecuting(boolean executing) {
//        this.executing = executing;
//    }
//
//    public void syncronizeAndDoTheRest() {
//        try {
//            sleep(90);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MalhaFechada.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        synchronized (this) {
//            while (!isExecuting()) {
//                try {
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MalhaFechada.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//}
