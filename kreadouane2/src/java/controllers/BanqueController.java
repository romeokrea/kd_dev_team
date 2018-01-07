/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Banque;
import entities.Cad;
import entities.Contact;
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
import sessions.BanqueFacadeLocal;
import sessions.CadFacadeLocal;
import sessions.ContactFacadeLocal;


/**
 *
 * @author samourai venimeux
 */
public class BanqueController implements Serializable {

    @EJB
    private BanqueFacadeLocal banqueFacade;
    private List<Banque> listBanque = new ArrayList<>();
    private Banque banque = new Banque();
    private String operation;
    private String msg;
    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContacts = new ArrayList<>();
    private static int idBanq;

    
    public BanqueController() {
    }

    @PostConstruct
    public void init() {
        listBanque.clear();
        listBanque.addAll(banqueFacade.findByStatus("Active"));

    }
    
    public  String contacts() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByBanqueIdStatus(banque.getId(), "Active"));
        idBanq = banque.getId();
        return "contactBanque.xhtml?faces-redirect=true";

    }
    
    public String create() {
        banque = new Banque();
        return "formbanque.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        banque = new Banque();
        msg = "";
        action(e);
    }

    public String saveBanque() {

        try {
            
             if (banque.getAdressecompany() == null || "".equals(banque.getNamecompany()) || banque.getNamecompany().trim().length() == 0 || "".equals(banque.getEmailcompany()) || banque.getEmailcompany().trim().length() == 0 || "".equals(banque.getPhonecontribuable()) || banque.getPhonecontribuable().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
//            check if the account does not exist
            if (banqueFacade.findByName(banque.getNamecompany()) == null) {
                
//                create the acconier
                banque.setStatus("Active");
                banqueFacade.create(banque);
                return "banque.xhtml?faces-redirect=true";
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
    public void showMessageDialog( String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public String modifBanq() {
        
        return "formmodbanque.xhtml?faces-redirect=true";

    }

    public String modifyBanque() {
                
        try {
             if (banqueFacade.findByName(banque.getNamecompany()) == null) {

//                create the acconier
                 banque.setStatus("Active");
                 banqueFacade.edit(banque);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(banqueFacade.findByName(banque.getNamecompany()).getId(), banque.getId())) {
                     banqueFacade.edit(banque);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                   showMessageDialog("cette Banque exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "banque.xhtml?faces-redirect=true";
    }
    
   
    public String deleteBanque() {
        try {

            banque.setStatus("Not active");
            banqueFacade.edit(banque);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "banque.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveBanque();
                break;

            case "modify":
                modifyBanque();
                break;

            case "delete":
                deleteBanque();
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
         
        context.addMessage(null, new FacesMessage("Successful",  "" + message) );
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

    public BanqueFacadeLocal getBanqueFacade() {
        return banqueFacade;
    }

    public void setBanqueFacade(BanqueFacadeLocal banqueFacade) {
        this.banqueFacade = banqueFacade;
    }

    public List<Banque> getListBanque() {
        return listBanque;
    }

    public void setListBanque(List<Banque> listBanque) {
        this.listBanque = listBanque;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
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

    public static int getIdBanq() {
        return idBanq;
    }

    public static void setIdBanq(int idBanq) {
        BanqueController.idBanq = idBanq;
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