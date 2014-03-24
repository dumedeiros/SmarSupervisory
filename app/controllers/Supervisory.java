/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Connnector.RDAConnection;
import Utilities.ExecThread;
import br.ufrn.lii.commonsdomain.process.ProcessData;
import br.ufrn.lii.commonsdomain.process.TagItem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JOptionPane;
import models.TagVars;
import play.mvc.Controller;

/**
 *
 * @author
 */
public class Supervisory extends Controller {

    
    public static Map<TagItem, ProcessData> data;
    public static Map<TagItem, ProcessData> dataAquired;
    public static Map<String, Entry<TagItem, ProcessData>> supData;
    public static TagVars tagVars = new TagVars();
    public static ExecutionTrhead thread = new ExecutionTrhead(true);
    public static RDAConnection conn = new RDAConnection();
    private static String d = "";
    private static int i = 0;

    public static void init(String a, ProcessData b) {
    }

    public static void change(String a, ProcessData b) {
    }

    public static void index() {
        render();
    }

    public static void a() {
        thread.doResume();
    }

    public static void s() {
        thread.doSuspend();
    }

    public static void data() {
//        JOptionPane.showMessageDialog(null, "asdasd");
        Map data = new HashMap<Object, Object>();
        data.put("d", d);
        renderJSON(supData);
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

            while (true) {
                
//                 Set<Entry<String, Entry<TagItem, ProcessData>>> di = conn.getAllValuesTest().entrySet();
                 supData = conn.getAllValuesTest();
//                 for (Entry<String, Entry<TagItem, ProcessData>> entry : di) {
////                     System.out.println(entry.getValue().getKey().getIdStr() + " | " + entry.getValue().getValue().);
//                }
                
//                data = conn.getAllValues();
////                data = conn.getSelectedValues();
//                Set<TagItem> a = data.keySet();
//                Iterator<TagItem> itr = a.iterator();
//                System.out.println(data.size());
//
////                System.out.println(a);
//
//                Set<TagItem> keySet = data.keySet();
//                System.out.println("Current Values Obtidos:");
//                for (TagItem key : keySet) {
//                    if (key.toString().contains("OUT010")) {
//                        System.out.println("AQUI....");
//                        System.out.println(key + " | " + data.get(key).getValue().getStringValue());
//                    }
//                }

//                System.out.println(data.get("PD3_HART_02.L3.AI005.AI005.OUT009"));
//                JOptionPane.showMessageDialog(null, data);
                //  System.out.println(data.get("PD3_HART_02.L3AI005.AI005.OUT009"));
//                System.out.println("executing");
                i++;
//                d = i + conn.aquireData();
                d = i + "";
                System.out.println(d);
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
