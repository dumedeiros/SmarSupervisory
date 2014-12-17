/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connnector;

import Utilities.Util;
import br.ufrn.lii.brcollector.callback.AbstractRealtimeProcessCallback;
import br.ufrn.lii.brcollector.connection.CollectorConnectionProvider;
import br.ufrn.lii.brcollector.connection.exception.AlreadyMappedException;
import br.ufrn.lii.brcollector.connection.rmi.Request;
import br.ufrn.lii.brcollector.connection.rmi.RequestRDA;
import br.ufrn.lii.commonsdomain.ProcessVariableSubscription;
import br.ufrn.lii.commonsdomain.TagItemGroup;
import br.ufrn.lii.commonsdomain.exception.UnregisteredGroupException;
import br.ufrn.lii.commonsdomain.process.ProcessData;
import br.ufrn.lii.commonsdomain.process.TagItem;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class RDAConnection {

    private Request request;
    private RequestRDA requestRDA;
    private TagItemGroup allTagsItemGroup;
    private TagItemGroup selectedTagsItemGroup;

    public RDAConnection() {
        requestConnection(null, 0, null);
        Utilities.Util.clearScreen();
        System.out.println(getServersList().get(0));
        connectToServer(getServersList().get(0));
        init();
    }

    public boolean requestConnection(String host, int port, String servName) {
        request = null;
        try {
//            request = CollectorConnectionProvider.connectToCollector(host, port, servName);
            request = CollectorConnectionProvider.connectToCollector(BRCollectorTestConstants.HOST,
                    BRCollectorTestConstants.PORT,
                    BRCollectorTestConstants.SERVICE_NAME);
        } catch (Exception ex) {
            TestExceptionManager.log("oijasiodjasiodErrou ao conectar-se ao coletor.", ex);
            return false;
        }
        return true;
    }

    public List<String> getServersList() {
        List<String> listServerRDA = listServerRDA();
        System.out.println(listServerRDA.size() + " servidor encontrados");
        return listServerRDA;

    }

    public void connectToServer(String serverName) {
        requestRDA = connectServerRDA(serverName);
        System.out.println("Conectado ao servidor: " + serverName);
    }

    public void init() {

        List<TagItem> allTagItens = searchTagsByRegex(".");
        System.out.println("Tags listadas");

//        List<TagItem> selectedTagItens = searchTagsByRegex("(AI001.OUT002|"
//                + "AI002.OUT004|"
//                + "AI003.OUT006|"
//                + "AI004.OUT008|"
//                + "AI005.OUT010|"
//                + "AI006.OUT012)");
//        System.out.println("Tags listadas");

        allTagsItemGroup = registerGroup("novo1");
//        selectedTagsItemGroup = registerGroup("novo2");
        System.out.println("Grupos registrados");

        addItemsToGroup(allTagsItemGroup, allTagItens);
//        addItemsToGroup(selectedTagsItemGroup, selectedTagItens);
        System.out.println("TagsAdicionadas ao grupo");
    }

    private List<String> listServerRDA() {
        try {
            return request.listServersRDA();
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao lista servidores RDA.", ex);
        }

        return null;
    }

    public Map<TagItem, ProcessData> getSelectedValues() {
        return getGroupValues(selectedTagsItemGroup);
    }

    public Map<TagItem, ProcessData> getAllValues() {
        return getGroupValues(allTagsItemGroup);
    }

    public Map<String, Entry<TagItem, ProcessData>> getAllValuesTest() {
        return getValuesTest(allTagsItemGroup);
    }

    private RequestRDA connectServerRDA(String serverName) {
        try {
            return request.connectServerRDA(serverName);
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao conectar ao servidor " + serverName, ex);
        }

        return null;
    }

    private List<TagItem> searchTagsByRegex(String reg) {

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher;

        try {
            List<TagItem> nodes = requestRDA.browseNode(null);
            List<TagItem> searchTagItems = new ArrayList();
            while (nodes.size() > 0) {
                TagItem t = nodes.remove(0);
                if (t.isNode()) {
                    nodes.addAll(0, requestRDA.browseNode(t));
                } else {
                    matcher = pattern.matcher(t.getIdStr());
                    if (matcher.find()) {
                        t.setName(t.getIdStr());
                        searchTagItems.add(t);
                    }
                }
            }
//            List<TagItem> searchTagItems = requestRDA.searchTagItems("AP_NIV_02.LG.COMM.CR0_L/R031.REGISTER");
//            System.out.println("Tags listadas:");
//            for (TagItem tagItem : searchTagItems) {
//                System.out.println(tagItem.getIdStr());
//            }
            return searchTagItems;
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no searchTags RDA.", ex);
        }

        return null;
    }

    private TagItemGroup registerGroup(String name) {
        try {
            return requestRDA.createGroup(name);
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no registro do TagItemGroup RDA.", ex);
        } catch (AlreadyMappedException ex) {
            TestExceptionManager.log("Errou no registro do TagItemGroup RDA.", new Exception(ex));
        }
        return null;
    }

    private void addItemsToGroup(TagItemGroup group, List<TagItem> tagItems) {
        try {
            requestRDA.addItemsToGroup(group, tagItems.toArray(new TagItem[tagItems.size()]));
        } catch (RemoteException ex) {
            TestExceptionManager.log("ASdsadErrou ao adicionar tags ao TagItemGroup RDA.", new Exception(ex));
        }
    }

    
    private Map<TagItem, ProcessData> getGroupValues(TagItemGroup group) {
        try {
            Map<TagItem, ProcessData> currentValues = requestRDA.getCurrentValues(group);

//            Set<TagItem> keySet = currentValues.keySet();
//            System.out.println("Current Values Obtidos:");
//            for (TagItem key : keySet) {
//                System.out.println(key + " | " + currentValues.get(key).getValue().getStringValue());
//            }

            return currentValues;
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        } catch (UnregisteredGroupException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        }
        return null;
    }

    private Map<String, Entry<TagItem, ProcessData>> getValuesTest(TagItemGroup group) {
        try {
            Map<TagItem, ProcessData> currentValues = requestRDA.getCurrentValues(group);
            Map<String, Entry<TagItem, ProcessData>> d = new HashMap<String, Entry<TagItem, ProcessData>>();

            for (Entry<TagItem, ProcessData> entry : currentValues.entrySet()) {
                d.put(Util.formatData(entry.getKey().getIdStr()), entry);
            }
//            Set<TagItem> keySet = currentValues.keySet();
//            System.out.println("Current Values Obtidos:");
//            for (TagItem key : keySet) {
//                System.out.println(key + " | " + currentValues.get(key).getValue().getStringValue());
//            }

            return d;

        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        } catch (UnregisteredGroupException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        }
        return null;
    }

//    private void removeTagItemsFromGroup(RequestRDA requestRDA, TagItemGroup tagItemGroup, List<TagItem> tagItems) {
//        try {
//            requestRDA.removeItemsFromGroup(tagItemGroup, tagItems.toArray(new TagItem[tagItems.size()]));
//        } catch (RemoteException ex) {
//            TestExceptionManager.log("Errou ao remover tags do group RDA", new Exception(ex));
//        }
//    }
//    private void createSubscription(RequestRDA requestRDA, List<TagItem> tagItems) throws AlreadyMappedException {
//        try {
//            ProcessVariableSubscription processVariableSubscription = requestRDA.createSubscription("Subscription teste RDA", true, 1000, 0.1f, new MyRealtimeProcessCallback());
//
//            requestRDA.addItemsToSubscription(processVariableSubscription, tagItems.toArray(new TagItem[tagItems.size()]));
//        } catch (RemoteException ex) {
//            TestExceptionManager.log("Errou no create subscription RDA", ex);
//        }
//    }
//class MyRealtimeProcessCallback extends AbstractRealtimeProcessCallback {
//
//    public MyRealtimeProcessCallback() throws RemoteException {
//    }
//
//    public void onDataChange(HashMap<TagItem, ProcessData> hm) throws RemoteException {
//        Set<TagItem> keySet = hm.keySet();
//
//        System.out.println("Recebeu alguma coisa");
//
//        for (TagItem tagItem : keySet) {
//            System.out.println("Variavel " + tagItem.getIdStr() + " = " + hm.get(tagItem).getValue().getStringValue());
//        }
//    }
}
