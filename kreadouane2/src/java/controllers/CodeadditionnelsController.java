/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Codeadditionnels;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.CodeadditionnelsFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class CodeadditionnelsController implements Serializable {

    @EJB
    private CodeadditionnelsFacadeLocal codeadditionnelsFacade;
    private List<Codeadditionnels> listCodeadditionnels = new ArrayList<>();
    private Codeadditionnels codeadditionnels = new Codeadditionnels();
    private String operation;
    private String msg;

    public CodeadditionnelsController() {
    }

    @PostConstruct
    public void init() {
        listCodeadditionnels.clear();
        listCodeadditionnels.addAll(codeadditionnelsFacade.findByStatus("Active"));

    }
    
     public String create() {
        codeadditionnels=new Codeadditionnels();
        return "addCodeAdditionnel.xhtml?faces-redirect=true";
        
    }
    public String modify() {
        return "modifyCodeAdditionnel.xhtml?faces-redirect=true";
        
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
        
    }

    public void prepareCreate(ActionEvent e) {
        codeadditionnels = new Codeadditionnels();
        msg = "";
        action(e);
    }

    public String saveCodeadditionnels() {

        try {
            if ("".equals(codeadditionnels.getCategorie()) || codeadditionnels.getCategorie().trim().length() == 0 || "".equals(codeadditionnels.getDetails()) || codeadditionnels.getDetails().trim().length() == 0) {
                fatal2();
                return null;
            } else {
//            check if the account does not exist
            

//                create the acconier
                codeadditionnels.setStatus("Active");
                codeadditionnelsFacade.create(codeadditionnels);
                return "codeadditionnels.xhtml?faces-redirect=true";
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

    public String modifyCodeadditionnels() {

        try {
           if ("".equals(codeadditionnels.getCategorie()) || codeadditionnels.getCategorie().trim().length() == 0 || "".equals(codeadditionnels.getDetails()) || codeadditionnels.getDetails().trim().length() == 0) {
                fatal2();
                return null;
            } else {

//                create the acconier
                codeadditionnels.setStatus("Active");
                codeadditionnelsFacade.edit(codeadditionnels);
                return "codeadditionnels.xhtml?faces-redirect=true";
           
           }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            init();
            
        }
    }

    public String deleteCodeadditionnels() {
        try {

            codeadditionnels.setStatus("Not active");
            codeadditionnelsFacade.edit(codeadditionnels);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "codeadditionnels.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveCodeadditionnels();
                break;

            case "modify":
                modifyCodeadditionnels();
                break;

            case "delete":
                deleteCodeadditionnels();
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

    public CodeadditionnelsFacadeLocal getCodeadditionnelsFacade() {
        return codeadditionnelsFacade;
    }

    public void setCodeadditionnelsFacade(CodeadditionnelsFacadeLocal codeadditionnelsFacade) {
        this.codeadditionnelsFacade = codeadditionnelsFacade;
    }

    public List<Codeadditionnels> getListCodeadditionnels() {
        return listCodeadditionnels;
    }

    public void setListCodeadditionnels(List<Codeadditionnels> listCodeadditionnels) {
        this.listCodeadditionnels = listCodeadditionnels;
    }

    public Codeadditionnels getCodeadditionnels() {
        return codeadditionnels;
    }

    public void setCodeadditionnels(Codeadditionnels codeadditionnels) {
        this.codeadditionnels = codeadditionnels;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "This code already exist"));
    }

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the blank spaces"));
    }

}
