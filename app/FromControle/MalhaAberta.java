//package FromControle;
//
//import java.util.List;
//import objects.QuanserController;
//import objects.Onda;
//import play.mvc.Controller;
//
//public class MalhaAberta extends Controller {
//
//    public static Onda onda;
//    public static QuanserController quanserController;
//    public static List lab;
//    public static List valores;
//    public static MAThread maThread;
//    public static SystemData systemData;
//
//    public static class SystemData {
//
//        public double controlOutput;
//        public double valorTq2;
//        public double valorTq1;
//    }
//
//    public static class MAThread extends MyThread {
//
//        public MAThread() {
//            super();
//        }
//
//        public MAThread(boolean b) {
//            super(b);
//        }
//
//        @Override
//        public void run() {
//            //TODO colocar sralimentacao pra controlar o tanque 2 tb #acho que nao
//            while (true) {
//                systemData.valorTq1 = Application.quanserController.read(0) * 6.25;
//                systemData.valorTq2 = Application.quanserController.read(1) * 6.25;
//                systemData.controlOutput = onda.nextPointControled(systemData.valorTq1);
//                onda.update();
//                Application.quanserController.write(0, systemData.controlOutput);
//
//                if (!isExecuting()) {
//                    Application.quanserController.write(0, 0.0);
//                }
//                syncronizeAndDoTheRest();
//            }
//        }
//    }
//
//    public static void execute(int tipo, double amp, int per, double offSet) {
//        MalhaAberta.onda = new Onda();
////        MalhaAberta.onda = new Onda(6, 2, 0, Onda.Formato.QUADRADA);
//        onda.setParametros(tipo, amp, per, offSet);
//        maThread.doResume();
//    }
//
//    public static void getValues() {
//        renderJSON(systemData);
//    }
//}
