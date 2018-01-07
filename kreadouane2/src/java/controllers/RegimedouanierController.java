/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Regimedouanier;
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
import sessions.RegimedouanierFacadeLocal;
import sessions.CadFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class RegimedouanierController implements Serializable {

    @EJB
    private RegimedouanierFacadeLocal regimedouanierFacade;
    private List<Regimedouanier> listRegimedouanier = new ArrayList<>();
    private Regimedouanier regimedouanier = new Regimedouanier();
    private String operation;
    private String msg;

    public RegimedouanierController() {
    }

    @PostConstruct
    public void init() {
        listRegimedouanier.clear();
        listRegimedouanier.addAll(regimedouanierFacade.findByStatus("Active"));

    }

    public String create() {
        regimedouanier = new Regimedouanier();
        return "addRegime.xhtml?faces-redirect=true";

    }

    public String modify() {
        return "modifyRegime.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";

    }

    public void prepareCreate(ActionEvent e) {
        regimedouanier = new Regimedouanier();
        msg = "";
        action(e);
    }

    public String saveRegimedouanier() {

        try {
            if ("".equals(regimedouanier.getCode()) || regimedouanier.getCode().trim().length() == 0 || "".equals(regimedouanier.getLibelle()) || regimedouanier.getLibelle().trim().length() == 0) {
                fatal2();
                return null;
            } else {
//            check if the account does not exist
                if (regimedouanierFacade.findByCode(regimedouanier.getCode()) == null) {

//                create the acconier
                    regimedouanier.setStatus("Active");
                    regimedouanierFacade.create(regimedouanier);
                    return "regimedouanier.xhtml?faces-redirect=true";

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

    public String modifyRegimedouanier() {

        try {
            if ("".equals(regimedouanier.getCode()) || regimedouanier.getCode().trim().length() == 0 || "".equals(regimedouanier.getLibelle()) || regimedouanier.getLibelle().trim().length() == 0) {
                fatal2();
                return null;
            } else {
                if (regimedouanierFacade.findByCode(regimedouanier.getCode()) == null) {

//                create the acconier
                    regimedouanier.setStatus("Active");
                    regimedouanierFacade.edit(regimedouanier);
                    return "regimedouanier.xhtml?faces-redirect=true";
                } else {
                    if (Objects.equals(regimedouanierFacade.findByCode(regimedouanier.getCode()).getId(), regimedouanier.getId())) {
                        regimedouanier.setStatus("Active");
                        regimedouanierFacade.edit(regimedouanier);
                        return "regimedouanier.xhtml?faces-redirect=true";

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

    public String deleteRegimedouanier() {
        try {

            regimedouanier.setStatus("Not active");
            regimedouanierFacade.edit(regimedouanier);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "regimedouanier.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveRegimedouanier();
                break;

            case "modify":
                modifyRegimedouanier();
                break;

            case "delete":
                deleteRegimedouanier();
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

    public RegimedouanierFacadeLocal getRegimedouanierFacade() {
        return regimedouanierFacade;
    }

    public void setRegimedouanierFacade(RegimedouanierFacadeLocal regimedouanierFacade) {
        this.regimedouanierFacade = regimedouanierFacade;
    }

    public List<Regimedouanier> getListRegimedouanier() {
        return listRegimedouanier;
    }

    public void setListRegimedouanier(List<Regimedouanier> listRegimedouanier) {
        this.listRegimedouanier = listRegimedouanier;
    }

    public Regimedouanier getRegimedouanier() {
        return regimedouanier;
    }

    public void setRegimedouanier(Regimedouanier regimedouanier) {
        this.regimedouanier = regimedouanier;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "This regim already exist"));
    }

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the blank spaces"));
    }

}
