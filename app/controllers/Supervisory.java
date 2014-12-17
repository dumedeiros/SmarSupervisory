/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Connnector.RDAConnection;
import Connnector.TestExceptionManager;
import Utilities.ExecThread;
import Utilities.Util;
import br.ufrn.lii.commonsdomain.TagItemGroup;
import br.ufrn.lii.commonsdomain.exception.UnregisteredGroupException;
import br.ufrn.lii.commonsdomain.process.ProcessData;
import br.ufrn.lii.commonsdomain.process.TagItem;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import models.TagVars;
import play.mvc.Controller;

/**
 *
 * @author
 */
public class Supervisory extends Controller {

    public static Map<TagItem, ProcessData> opcData;
    public static Map<String, Entry<TagItem, ProcessData>> opcDataMap;
    public static SupervisoryThread thread = new SupervisoryThread(true);
    public static RDAConnection conn;

    private static void OrganizeData() {
        for (Entry<TagItem, ProcessData> entry : opcData.entrySet()) {
            opcDataMap.put(Util.formatData(entry.getKey().getIdStr()), entry);
        }
    }

    public static void getOpcData() {
        renderJSON(opcDataMap);

    }

    public static class SupervisoryThread extends ExecThread {

        public SupervisoryThread() {
            super();
        }

        public SupervisoryThread(boolean b) {
            super(b);
        }

        @Override
        public void run() {
            while (true) {
                Supervisory.opcData = conn.getAllValues();
                Supervisory.OrganizeData();
                syncronizeAndDoTheRest();
            }
        }
    }
}
