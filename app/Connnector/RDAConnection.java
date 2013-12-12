/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connnector;

import br.ufrn.lii.brcollector.connection.CollectorConnectionProvider;
import br.ufrn.lii.brcollector.connection.exception.AlreadyMappedException;
import br.ufrn.lii.brcollector.connection.rmi.Request;
import br.ufrn.lii.brcollector.connection.rmi.RequestRDA;
import br.ufrn.lii.commonsdomain.TagItemGroup;
import br.ufrn.lii.commonsdomain.exception.UnregisteredGroupException;
import br.ufrn.lii.commonsdomain.process.ProcessData;
import br.ufrn.lii.commonsdomain.process.TagItem;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class RDAConnection {

    private Request request;
    private RequestRDA requestRDA;
    private TagItemGroup tagItemGroup;

    public RDAConnection() {

        request = null;
        try {
            request = CollectorConnectionProvider.connectToCollector(BRCollectorTestConstants.HOST,
                    BRCollectorTestConstants.PORT,
                    BRCollectorTestConstants.SERVICE_NAME);
        } catch (Exception ex) {
            TestExceptionManager.log("Errou ao conectar-se ao coletor.", ex);
        }
    }

    public void init() {
        List<String> listServerRDA = listServerRDA();
        System.out.println(listServerRDA.size() + " servidor encontrados");

        String serverName = listServerRDA.get(0);
        System.out.println("Servidor " + serverName);

        requestRDA = connectServerRDA(serverName);
        System.out.println("Conectado ao servidor: " + serverName);

        List<TagItem> tagItems = searchTags(requestRDA);
        System.out.println("Tags listadas");

        tagItemGroup = registerGroup(requestRDA);
        System.out.println("Grupo registrado");

        addItemsToGroup(requestRDA, tagItemGroup, tagItems);
        System.out.println("TagsAdicionadas ao grupo");
    }

    public String aquireData() {
        return getCurrentValues(requestRDA, tagItemGroup);
//        System.out.println("Current Values obtidos");
    }

    private List<String> listServerRDA() {
        try {
            return request.listServersRDA();
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao lista servidores RDA.", ex);
        }
        return null;
    }

    private RequestRDA connectServerRDA(String serverName) {
        try {
            return request.connectServerRDA(serverName);
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao conectar ao servidor " + serverName, ex);
        }
        return null;
    }

    private List<TagItem> searchTags(RequestRDA requestRDA) {
        try {
            List<TagItem> searchTagItems = requestRDA.searchTagItems("*");
            System.out.println("Tags listadas:");
            for (TagItem tagItem : searchTagItems) {
                System.out.println(tagItem);
            }
            return searchTagItems;
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no searchTags RDA.", ex);
        }

        return null;
    }

    private TagItemGroup registerGroup(RequestRDA requestRDA) {
        try {
            return requestRDA.createGroup("Grupo tags de teste");
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no registro do TagItemGroup RDA.", ex);
        } catch (AlreadyMappedException ex) {
            TestExceptionManager.log("Errou no registro do TagItemGroup RDA.", new Exception(ex));
        }
        return null;
    }

    private void addItemsToGroup(RequestRDA requestRDA, TagItemGroup tagItemGroup, List<TagItem> tagItems) {
        try {
            requestRDA.addItemsToGroup(tagItemGroup, tagItems.toArray(new TagItem[tagItems.size()]));
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao adicionar tags ao TagItemGroup RDA.", new Exception(ex));
        }
    }

    private String getCurrentValues(RequestRDA requestRDA, TagItemGroup tagItemGroup) {
//    private Map<TagItem, ProcessData> getCurrentValues(RequestRDA requestRDA, TagItemGroup tagItemGroup) {
        String d = "";
        try {
            Map<TagItem, ProcessData> currentValues = requestRDA.getCurrentValues(tagItemGroup);

            Set<TagItem> keySet = currentValues.keySet();
            System.out.println("Current Values Obtidos:");
            for (TagItem key : keySet) {
//                System.out.println(key + " | " + currentValues.get(key).getValue().getStringValue());
                d += key;
            }

            return d;
//            return currentValues;
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        } catch (UnregisteredGroupException ex) {
            TestExceptionManager.log("Errou no getCurrentValues RDA.", new Exception(ex));
        }
        return null;
    }

    private void removeTagItemsFromGroup(RequestRDA requestRDA, TagItemGroup tagItemGroup, List<TagItem> tagItems) {
        try {
            requestRDA.removeItemsFromGroup(tagItemGroup, tagItems.toArray(new TagItem[tagItems.size()]));
        } catch (RemoteException ex) {
            TestExceptionManager.log("Errou ao remover tags do group RDA", new Exception(ex));
        }
    }
}