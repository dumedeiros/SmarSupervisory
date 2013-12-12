//package FromControle;
//
//import javax.swing.JOptionPane;
//import play.mvc.Controller;
//
//public class Cascata extends Controller {
//
//    public static PID pid;
//    public static PID pid2;
//    public static CascataThread cascThread;
//    public static int sinRealim;
//    public static int sinRealim2;
//    public static SystemData systemData;
//
//    //Valores das variaveis do tanque
//    public static class SystemData {
//
//        public double valorPV;
//        public double controlOutput;
//        public double percenOvshoot;
//        public double tOvershoot;
//        public double tSubida;
//        public double tAcomodacao;
//        public double valorTq2;
//        public double valorTq1;
//        public double ganhoP;
//        public double ganhoI;
//        public double ganhoD;
//        public double ganhoPID;
//    }
//
//    public static class CascataThread extends MyThread {
//
//        public CascataThread() {
//            super();
//        }
//
//        public CascataThread(boolean b) {
//            super(b);
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                systemData.valorTq1 = Application.quanserController.read(0) * 6.25;
//                systemData.valorTq2 = Application.quanserController.read(1) * 6.25;
//
//                systemData.valorPV = sinRealim == 0 ? systemData.valorTq1 : systemData.valorTq2;
//                pid2.setPoint = pid.processData(systemData.valorPV, systemData.valorTq1);
//                double pv2 = sinRealim2 == 0 ? systemData.valorTq1 : systemData.valorTq2;
//                systemData.controlOutput = pid2.processData(pv2, systemData.valorTq1);
//                
//                Application.quanserController.write(0, systemData.controlOutput);
//                if (!isExecuting()) {
//                    Application.quanserController.write(0, 0.0);
//                }
//                syncronizeAndDoTheRest();
//            }
//        }
//    }
//
//    public static void execute(int sr, int sr2, double kp, double ki, double kd, double kp2, double ki2, double kd2, double setPoint, int iType, int iType2, int contrType, int contrType2) {
////        sinRealim = sr;
////        sinRealim2 = sr2;
////        System.out.println(sr);
////        System.out.println(sr2);
////        System.out.println(kp);
////        System.out.println(ki);
////        System.out.println(kd);
////        System.out.println(kp2);
////        System.out.println(ki2);
////        System.out.println(kd2);
////        System.out.println(setPoint);
////        System.out.println(iType);
////        System.out.println(iType2);
////        System.out.println(contrType);
////        System.out.println(contrType2);
//        
//        pid.setParametros(kp, ki, kd, 0.1, setPoint, iType, contrType);
//        
//        //TODO !!! IMPORTANTE !!! MUDAR O SET POINT AQUI EM BAIXO 
//        pid2.setParametros(kp2, ki2, kd2, 0.1, 0.1, iType2, contrType2);
//        cascThread.setExecuting(true);
//        cascThread.doResume();
//    }
//
//    public static void getValues() {
//
//        systemData.percenOvshoot = pid.percenOvshoot;
//        systemData.tOvershoot = pid.tovershoot;
//        systemData.tSubida = pid.tsubida;
//        systemData.tAcomodacao = pid.tacomodacao;
//        renderJSON(systemData);
//    }
//
//    public static void main(String[] args) {
//    }
//}
