/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Acconier;
import sessions.AcconierFacadeLocal;
import entities.Account;
import entities.Contact;
import entities.Transporteur;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.AccountFacadeLocal;
import sessions.ContactFacadeLocal;
import sessions.TransporteurFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class TransporteurController implements Serializable {

    @EJB
    private TransporteurFacadeLocal transporteurFacade;
    private List<Transporteur> listTransporteur = new ArrayList<>();
    private Transporteur transporteur = new Transporteur();
    private String operation;
    private String msg;
    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContacts = new ArrayList<>();
    private static int idTrans;

    public TransporteurController() {
    }

    @PostConstruct
    public void init() {
        listTransporteur.clear();
        listTransporteur.addAll(transporteurFacade.findByStatus("Active"));

    }

    public String contacts() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByTransIdStatus(transporteur.getId(), "Active"));
        idTrans = transporteur.getId();
        return "contactTransporteur.xhtml?faces-redirect=true";

    }

    public String create() {
        transporteur = new Transporteur();
        return "formtransporteur.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        transporteur = new Transporteur();
        msg = "";
        action(e);
    }

    public String saveTransporteur() {

        try {

            if (transporteur.getAdressecompany() == null || "".equals(transporteur.getNamecompany()) || transporteur.getNamecompany().trim().length() == 0 || "".equals(transporteur.getEmailcompany()) || transporteur.getEmailcompany().trim().length() == 0 || "".equals(transporteur.getPhonecompany()) || transporteur.getPhonecompany().trim().length() == 0) {

                fatal2();
                return null;
            } else {

//            check if the account does not exist
                if (transporteurFacade.findByName(transporteur.getNamecompany()) == null) {

//                create the acconier
                    transporteur.setStatus("Active");
                    transporteurFacade.create(transporteur);
                    return "transporteur.xhtml?faces-redirect=true";
                } else {
                    fatal();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            init();
        }

    }

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String modifTrans() {

        return "formmodtranspoteur.xhtml?faces-redirect=true";

    }

    public String modifyTransporteur() {

        try {

            if (transporteurFacade.findByName(transporteur.getNamecompany()) == null) {

                transporteur.setStatus("Active");
                transporteurFacade.edit(transporteur);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(transporteurFacade.findByName(transporteur.getNamecompany()).getId(), transporteur.getId())) {
                    transporteurFacade.edit(transporteur);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                    showMessageDialog("ce transporteur exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "transporteur.xhtml?faces-redirect=true";
    }

    public String deleteTransporteur() {
        try {

            transporteur.setStatus("Not active");
            transporteurFacade.edit(transporteur);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "transporteur.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveTransporteur();
                break;

            case "modify":
                modifyTransporteur();
                break;

            case "delete":
                deleteTransporteur();
                break;

        }
    }

//     public void logFile(String name) {
//        try {
//            Logfile op = new Logfile();
//            op.setDateoperstion(new Date(System.currentTimeMillis()));
//            op.setAddreseip(((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr());
//            op.setOperation(name);
//            op.setTimeoperation(new Time(System.currentTimeMillis()));
//            op.setAccId((Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
//            logfileFacade.create(op);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void saveMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "" + message));
    }

    public ContactFacadeLocal getContactFacade() {
        return contactFacade;
    }

    public void setContactFacade(ContactFacadeLocal contactFacade) {
        this.contactFacade = contactFacade;
    }

    public List<Contact> getListContacts() {
        return listContacts;
    }

    public void setListContacts(List<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    public static int getIdTrans() {
        return idTrans;
    }

    public static void setIdTrans(int idTrans) {
        TransporteurController.idTrans = idTrans;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TransporteurFacadeLocal getTransporteurFacade() {
        return transporteurFacade;
    }

    public void setTransporteurFacade(TransporteurFacadeLocal transporteurFacade) {
        this.transporteurFacade = transporteurFacade;
    }

    public List<Transporteur> getListTransporteur() {
        return listTransporteur;
    }

    public void setListTransporteur(List<Transporteur> listTransporteur) {
        this.listTransporteur = listTransporteur;
    }

    public Transporteur getTransporteur() {
        return transporteur;
    }

    public void setTransporteur(Transporteur transporteur) {
        this.transporteur = transporteur;
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Operation successfull!"));
    }

    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }

    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Operation failed!"));
    }

    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the blank spaces"));
    }
}
