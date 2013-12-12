//package FromControle;
//
//import java.text.DecimalFormat;
//import play.mvc.Controller;
//
//public class MalhaFechada extends Controller {
//
//    public static PID pid;
//    public static MFThread mfThread;
//    public static int sinRealim;
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
//        
//        public double ganhoP;
//        public double ganhoI;
//        public double ganhoD;
//        public double ganhoPID;
//        
//    }
//
//    public static class MFThread extends MyThread {
//
//        public MFThread() {
//            super();
//        }
//
//        public MFThread(boolean b) {
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
//                systemData.controlOutput = pid.processData(systemData.valorPV, systemData.valorTq1);
//                Application.quanserController.write(0, systemData.controlOutput);
//                if (!isExecuting()) {
//                    Application.quanserController.write(0, 0.0);
//                }
//                syncronizeAndDoTheRest();
//            }
//        }
//    }
//
//    public static void change(int sr, double kp, double ki, double kd, double setPoint, int iType, int contrType) {
//        sinRealim = sr;
//        pid.setParametros(kp, ki, kd, 0.1, setPoint, iType, contrType);
//    }
//
//    public static void execute(int sr, double kp, double ki, double kd, double setPoint, int iType, int contrType) {
////        JOptionPane.showMessageDialog(null, "action");
////        MalhaFechada.mfThread.doResume();
//
//        sinRealim = sr;
//        pid.setParametros(kp, ki, kd, 0.1, setPoint, iType, contrType);
//        mfThread.setExecuting(true);
//        mfThread.doResume();
////        JOptionPane.showMessageDialog(null, mfThread.executing);
////        JOptionPane.showMessageDialog(null, mfThread);
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
