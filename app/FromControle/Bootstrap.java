//package FromControle;
//
//import controllers.Application;
//import controllers.Cascata;
//import controllers.MalhaAberta;
//import controllers.MalhaFechada;
//import controllers.PID;
//import javax.swing.JOptionPane;
//import objects.Onda;
//import objects.QuanserController;
//import play.jobs.Job;
//import play.jobs.OnApplicationStart;
//
////TODO fazer os inputs de malhaAberta changable
//
//@OnApplicationStart
//public class Bootstrap extends Job {
//
//    @Override
//    public void doJob() {
//        Application.quanserController = new QuanserController();
//        
//        Cascata.pid = new PID();
//        Cascata.pid.MAX_OUT = 27;
//        Cascata.pid.MIN_OUT = 0;
//        Cascata.pid2 = new PID();
//        Cascata.cascThread = new Cascata.CascataThread(false);
//        Cascata.systemData = new Cascata.SystemData();
//        Cascata.cascThread.start();
//        
//        MalhaFechada.pid = new PID();
//        MalhaFechada.mfThread = new MalhaFechada.MFThread(false);
//        MalhaFechada.systemData = new MalhaFechada.SystemData();
//        MalhaFechada.mfThread.start();
//
//        MalhaAberta.onda = new Onda(6, 2, 0, Onda.Formato.QUADRADA);
//        MalhaAberta.maThread = new MalhaAberta.MAThread(false);
//        MalhaAberta.systemData = new MalhaAberta.SystemData();
//        MalhaAberta.maThread.start();
//        
//    }
//}
