/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Assureur;
import sessions.AssureurFacadeLocal;
import entities.Account;
import entities.Assureur;
import entities.Contact;
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
import sessions.AssureurFacadeLocal;
import sessions.ContactFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class AssureurController implements Serializable {

    @EJB
    private AssureurFacadeLocal assureurFacade;
    private List<Assureur> listAssureur = new ArrayList<>();
    private Assureur assureur = new Assureur();
    private String operation;
    private String msg;
    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContacts = new ArrayList<>();
    private static int idAss;

    public AssureurController() {
    }

    @PostConstruct
    public void init() {
        listAssureur.clear();
        listAssureur.addAll(assureurFacade.findByStatus("Active"));

    }

    public void contacts() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByAssureurIdStatus(assureur.getId(), "Active"));
        idAss = assureur.getId();

    }

    public String create() {
        assureur = new Assureur();
        return "formassureur.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        assureur = new Assureur();
        msg = "";
        action(e);
    }

    public String saveAssureur() {

        try {
            if (assureur.getAdressecompany() == null || "".equals(assureur.getNamecompany()) || assureur.getNamecompany().trim().length() == 0 || "".equals(assureur.getEmailcompany()) || assureur.getEmailcompany().trim().length() == 0 || "".equals(assureur.getPhonecontribuable()) || assureur.getPhonecontribuable().trim().length() == 0) {

                fatal2();
                return null;
            } else {

//            check if the account does not exist
                if (assureurFacade.findByName(assureur.getNamecompany()) == null) {

//                create the assureur
                    assureur.setStatus("Active");
                    assureurFacade.create(assureur);
                    return "assureur.xhtml?faces-redirect=true";
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

    public String modifAss() {

        return "formmodassureur.xhtml?faces-redirect=true";

    }

    public String modifyAssureur() {

        try {

            if (assureurFacade.findByName(assureur.getNamecompany()) == null) {

//                create the assureur
                assureur.setStatus("Active");
                assureurFacade.edit(assureur);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(assureurFacade.findByName(assureur.getNamecompany()).getId(), assureur.getId())) {
                    assureurFacade.edit(assureur);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                    showMessageDialog("cet assureur exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "assureur.xhtml?faces-redirect=true";
    }

    public String deleteAssureur() {
        try {

            assureur.setStatus("Not active");
            assureurFacade.edit(assureur);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "assureur.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveAssureur();
                break;

            case "modify":
                modifyAssureur();
                break;

            case "delete":
                deleteAssureur();
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

    public AssureurFacadeLocal getAssureurFacade() {
        return assureurFacade;
    }

    public void setAssureurFacade(AssureurFacadeLocal assureurFacade) {
        this.assureurFacade = assureurFacade;
    }

    public List<Assureur> getListAssureur() {
        return listAssureur;
    }

    public void setListAssureur(List<Assureur> listAssureur) {
        this.listAssureur = listAssureur;
    }

    public Assureur getAssureur() {
        return assureur;
    }

    public void setAssureur(Assureur assureur) {
        this.assureur = assureur;
    }

    public static int getIdAss() {
        return idAss;
    }

    public static void setIdAss(int idAss) {
        AssureurController.idAss = idAss;
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
