/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Connnector.RDAConnection;
import Utilities.ExecThread;
import java.util.HashMap;
import java.util.Map;
import models.TagVars;
import play.mvc.Controller;

/**
 *
 * @author
 */
public class Supervisory extends Controller {

    public static TagVars tagVars = new TagVars();
    public static ExecutionTrhead thread = new ExecutionTrhead(false);
//    private static RDAConnection conn = new RDAConnection();
    private static String d = "";
    private static int i = 0;

    public static void a() {
        thread.doResume();
    }

    public static void s() {
        thread.doSuspend();
    }

    public static void data() {
        Map data = new HashMap<Object, Object>();
        data.put("d", d);
        renderJSON(data);
    }

    public static class ExecutionTrhead extends ExecThread {

        public ExecutionTrhead() {
            super();
        }

        public ExecutionTrhead(boolean b) {
            super(b);
        }

        @Override
        public void run() {
//            conn.init();

            while (true) {
//                System.out.println("executing");
                i++;
//                d = i + conn.aquireData();
                d = i + "";
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
                syncronizeAndDoTheRest();
            }
        }
    }
}
