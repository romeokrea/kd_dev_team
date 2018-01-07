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
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class AcconierController implements Serializable {

    @EJB
    private AcconierFacadeLocal acconierFacade;
    private List<Acconier> listAcconier = new ArrayList<>();
    private Acconier acconier = new Acconier();
    private String operation;
    private String msg;
    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContacts = new ArrayList<>();
    private static int idAcc;
    private String error1 = "";
    private String error2 = "";

    public AcconierController() {
    }

    @PostConstruct
    public void init() {
        listAcconier.clear();
        listAcconier.addAll(acconierFacade.findByStatus("Active"));

    }
    public void contacts() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByAccIdStatus(acconier.getId(), "Active"));
        idAcc = acconier.getId();

    }
    public String create() {
        acconier = new Acconier();
        return "formacconier.xhtml?faces-redirect=true";

    }
    
    

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        acconier = new Acconier();
        msg = "";
        action(e);
    }

    public String saveAcconier() {

        try {
            if (acconier.getAdressecompany() == null || "".equals(acconier.getNamecompany()) || acconier.getNamecompany().trim().length() == 0 || "".equals(acconier.getEmailcompany()) || acconier.getEmailcompany().trim().length() == 0 || "".equals(acconier.getPhonecontribuable()) || acconier.getPhonecontribuable().trim().length() == 0) {

                fatal2();
                return null;
            } else {

//            check if the account does not exist
                if (acconierFacade.findByName(acconier.getNamecompany()) == null) {
//                create the acconier
                    acconier.setStatus("Active");
                    acconierFacade.create(acconier);
                    return "acconier.xhtml?faces-redirect=true";

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
    
    public String modifAcc() {
        
        return "formmodacconier.xhtml?faces-redirect=true";

    }

    public String modifyAcconier() {

        try {

            if (acconierFacade.findByName(acconier.getNamecompany()) == null) {

//                create the acconier
                acconier.setStatus("Active");
                acconierFacade.edit(acconier);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(acconierFacade.findByName(acconier.getNamecompany()).getId(), acconier.getId())) {
                    acconierFacade.edit(acconier);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                    showMessageDialog("cet acconier exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "acconier.xhtml?faces-redirect=true";
    }

    public String deleteAcconier() {
        try {

            acconier.setStatus("Not active");
            acconierFacade.edit(acconier);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "acconier.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveAcconier();
                break;

            case "modify":
                modifyAcconier();
                break;

            case "delete":
                deleteAcconier();
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

    public AcconierFacadeLocal getAcconierFacade() {
        return acconierFacade;
    }

    public void setAcconierFacade(AcconierFacadeLocal acconierFacade) {
        this.acconierFacade = acconierFacade;
    }

    public List<Acconier> getListAcconier() {
        return listAcconier;
    }

    public void setListAcconier(List<Acconier> listAcconier) {
        this.listAcconier = listAcconier;
    }

    public Acconier getAcconier() {
        return acconier;
    }

    public void setAcconier(Acconier acconier) {
        this.acconier = acconier;
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

    public static int getIdAcc() {
        return idAcc;
    }

    public static void setIdAcc(int idAcc) {
        AcconierController.idAcc = idAcc;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
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
