/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import sessions.CadFacadeLocal;
import sessions.ContactFacadeLocal;


/**
 *
 * @author samourai venimeux
 */
public class CADController implements Serializable {

    @EJB
    private CadFacadeLocal cadFacade;
    private List<Cad> listCad = new ArrayList<>();
    private Cad cad = new Cad();
    private String operation;
    private String msg;
    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContacts = new ArrayList<>();
    private static int idCad;

    
    public CADController() {
    }

    @PostConstruct
    public void init() {
        listCad.clear();
        listCad.addAll(cadFacade.findByStatus("Active"));

    }
    
    public  String contacts() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByCadIdStatus(cad.getId(), "Active"));
        idCad = cad.getId();
        return "contactCAD.xhtml?faces-redirect=true";

    }
    
    public String create() {
        cad = new Cad();
        return "formcad.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        cad = new Cad();
        msg = "";
        action(e);
    }

    public String saveCad() {

        try {
            
            if (cad.getAdressecompany() == null || "".equals(cad.getNamecompany()) || cad.getNamecompany().trim().length() == 0 || "".equals(cad.getEmailcompany()) || cad.getEmailcompany().trim().length() == 0 || "".equals(cad.getPhonecontribluable()) || cad.getPhonecontribluable().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
//            check if the account does not exist
            if (cadFacade.findByName(cad.getNamecompany()) == null) {
                
//                create the acconier
                cad.setStatus("Active");
                cadFacade.create(cad);
                return "cad.xhtml?faces-redirect=true";
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
    
    public String modifCad() {
        
        return "formmodcad.xhtml?faces-redirect=true";

    }

    public String modifyCad() {
                
        try {
             if (cadFacade.findByName(cad.getNamecompany()) == null) {

//                create the acconier
                 cad.setStatus("Active");
                 cadFacade.edit(cad);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(cadFacade.findByName(cad.getNamecompany()).getId(), cad.getId())) {
                     cadFacade.edit(cad);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                   showMessageDialog("ce CAD exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "cad.xhtml?faces-redirect=true";
    }
    
   
    public String deleteCad() {
        try {

            cad.setStatus("Not active");
            cadFacade.edit(cad);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "cad.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveCad();
                break;

            case "modify":
                modifyCad();
                break;

            case "delete":
                deleteCad();
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

    public CadFacadeLocal getCadFacade() {
        return cadFacade;
    }

    public void setCadFacade(CadFacadeLocal cadFacade) {
        this.cadFacade = cadFacade;
    }

    public List<Cad> getListCad() {
        return listCad;
    }

    public void setListCad(List<Cad> listCad) {
        this.listCad = listCad;
    }

    public Cad getCad() {
        return cad;
    }

    public void setCad(Cad cad) {
        this.cad = cad;
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

    

    

    public static int getIdCad() {
        return idCad;
    }

    public static void setIdCad(int idCad) {
        CADController.idCad = idCad;
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
