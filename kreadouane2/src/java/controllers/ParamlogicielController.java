/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Paramlogiciel;
import entities.Cad;
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
import sessions.ArticleFacadeLocal;
import sessions.ParamlogicielFacadeLocal;
import sessions.CadFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class ParamlogicielController implements Serializable {

    @EJB
    private ParamlogicielFacadeLocal paramlogicielFacade;
    private List<Paramlogiciel> listParamlogiciel = new ArrayList<>();
    private Paramlogiciel paramlogiciel = new Paramlogiciel();
    private String operation;
    private String msg;

    public ParamlogicielController() {
    }

    @PostConstruct
    public void init() {
        listParamlogiciel.clear();
        listParamlogiciel.addAll(paramlogicielFacade.findAll());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";

    }

    public void prepareCreate(ActionEvent e) {
        paramlogiciel = new Paramlogiciel();
        msg = "";
        action(e);
    }

    public void saveParamlogiciel() {

        try {
//            check if the account does not exist
            if (paramlogicielFacade.findByLien(paramlogiciel.getLien()) == null) {

//                create the acconier
                paramlogicielFacade.create(paramlogiciel);
                saveMessage("saved");
                RequestContext.getCurrentInstance().execute("PF('wv_user').hide()");
            } else {
                showMessageDialog("ce modelle de declaration exist deja");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String modifyParamlogiciel() {
        try {
            if ("".equals(paramlogiciel.getLien()) || paramlogiciel.getLien().trim().length() == 0 || "".equals(paramlogiciel.getLiendesauvegarde()) || paramlogiciel.getLiendesauvegarde().trim().length() == 0 || "".equals(paramlogiciel.getNombd()) || paramlogiciel.getNombd().trim().length() == 0 || "".equals(paramlogiciel.getPassword()) || paramlogiciel.getPassword().trim().length() == 0 || "".equals(paramlogiciel.getServeur()) || paramlogiciel.getServeur().trim().length() == 0 || "".equals(paramlogiciel.getUser()) || paramlogiciel.getUser().trim().length() == 0) {
                fatal2();
                return null;
            } else {
                if (paramlogicielFacade.findByLien(paramlogiciel.getLien()) == null) {
//                create the acconier
                    paramlogicielFacade.edit(paramlogiciel);
                    return "paramlogiciel.xhtml?faces-redirect=true";
                } else {
                    if (Objects.equals(paramlogicielFacade.findByLien(paramlogiciel.getLien()).getId(), paramlogiciel.getId())) {
                        paramlogicielFacade.edit(paramlogiciel);
                        return "paramlogiciel.xhtml?faces-redirect=true";
                    } else {
                        fatal();
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            init();
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "modify":
                modifyParamlogiciel();
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

    public ParamlogicielFacadeLocal getParamlogicielFacade() {
        return paramlogicielFacade;
    }

    public void setParamlogicielFacade(ParamlogicielFacadeLocal paramlogicielFacade) {
        this.paramlogicielFacade = paramlogicielFacade;
    }

    public List<Paramlogiciel> getListParamlogiciel() {
        return listParamlogiciel;
    }

    public void setListParamlogiciel(List<Paramlogiciel> listParamlogiciel) {
        this.listParamlogiciel = listParamlogiciel;
    }

    public Paramlogiciel getParamlogiciel() {
        return paramlogiciel;
    }

    public void setParamlogiciel(Paramlogiciel paramlogiciel) {
        this.paramlogiciel = paramlogiciel;
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
