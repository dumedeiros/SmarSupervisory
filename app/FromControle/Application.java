//package FromControle;
//
//import javax.swing.JOptionPane;
//import play.mvc.*;
//
//import objects.Onda;
//import objects.QuanserController;
//
//public class Application extends Controller {
//
//    
//
//    static class ControllerMessage {
//
//        String msg;
//
//        public ControllerMessage() {
//        }
//
//        public ControllerMessage(String msg) {
//            this.msg = msg;
//        }
//    }
//    public static QuanserController quanserController;
//
//    public static void index() {
//        Utilities.Util.clearScreen();
//        stopThreads();
//        render();
//    }
//
//    public static void conect(String ip, int porta) {
//        boolean f = quanserController.conect(ip, porta);
//        ControllerMessage m = f ? new ControllerMessage("Ok") : null;
//        renderJSON(m);
//    }
//
//    public static void stop() {
//        stopThreads();
//    }
//    
//    private static void stopThreads() {
//        MalhaFechada.mfThread.doSuspend();
//        MalhaAberta.maThread.doSuspend();
//        Cascata.cascThread.doSuspend();
//    }
//
//}
