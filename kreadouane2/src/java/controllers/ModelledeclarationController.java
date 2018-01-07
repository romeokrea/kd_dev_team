/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Modelledeclaration;
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
import sessions.ModelledeclarationFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class ModelledeclarationController implements Serializable {

    @EJB
    private ModelledeclarationFacadeLocal modelledeclarationFacade;
    private List<Modelledeclaration> listModelledeclaration = new ArrayList<>();
    private Modelledeclaration modelledeclaration = new Modelledeclaration();
    private String operation;
    private String msg;

    public ModelledeclarationController() {
    }

    @PostConstruct
    public void init() {
        listModelledeclaration.clear();
        listModelledeclaration.addAll(modelledeclarationFacade.findByStatus("Active"));

    }

    public String create() {
        modelledeclaration = new Modelledeclaration();
        return "addModelDeclaration.xhtml?faces-redirect=true";

    }

    public String modify() {
        return "modifyModelDeclaration.xhtml?faces-redirect=true";

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";

    }

    public void prepareCreate(ActionEvent e) {
        modelledeclaration = new Modelledeclaration();
        msg = "";
        action(e);
    }

    public String saveModelledeclaration() {

        try {
            if ("".equals(modelledeclaration.getCode()) || modelledeclaration.getCode().trim().length() == 0 || "".equals(modelledeclaration.getLibelle()) || modelledeclaration.getLibelle().trim().length() == 0) {
                fatal2();
                return null;
            } else {
//            check if the account does not exist
                if (modelledeclarationFacade.findByCode(modelledeclaration.getCode()) == null) {

//                create the acconier
                    modelledeclaration.setStatus("Active");
                    modelledeclarationFacade.create(modelledeclaration);
                    return "modelledeclaration.xhtml?faces-redirect=true";
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

    public String modifyModelledeclaration() {

        try {
            if ("".equals(modelledeclaration.getCode()) || modelledeclaration.getCode().trim().length() == 0 || "".equals(modelledeclaration.getLibelle()) || modelledeclaration.getLibelle().trim().length() == 0) {
                fatal2();
                return null;
            } else {
                if (modelledeclarationFacade.findByCode(modelledeclaration.getCode()) == null) {

//                create the acconier
                    modelledeclaration.setStatus("Active");
                    modelledeclarationFacade.edit(modelledeclaration);
                    return "modelledeclaration.xhtml?faces-redirect=true";
                } else {
                    if (Objects.equals(modelledeclarationFacade.findByCode(modelledeclaration.getCode()).getId(), modelledeclaration.getId())) {
                        modelledeclaration.setStatus("Active");
                        modelledeclarationFacade.edit(modelledeclaration);
                        return "modelledeclaration.xhtml?faces-redirect=true";

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

    public String deleteModelledeclaration() {
        try {

            modelledeclaration.setStatus("Not active");
            modelledeclarationFacade.edit(modelledeclaration);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "modelledeclaration.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveModelledeclaration();
                break;

            case "modify":
                modifyModelledeclaration();
                break;

            case "delete":
                deleteModelledeclaration();
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

    public ModelledeclarationFacadeLocal getModelledeclarationFacade() {
        return modelledeclarationFacade;
    }

    public void setModelledeclarationFacade(ModelledeclarationFacadeLocal modelledeclarationFacade) {
        this.modelledeclarationFacade = modelledeclarationFacade;
    }

    public List<Modelledeclaration> getListModelledeclaration() {
        return listModelledeclaration;
    }

    public void setListModelledeclaration(List<Modelledeclaration> listModelledeclaration) {
        this.listModelledeclaration = listModelledeclaration;
    }

    public Modelledeclaration getModelledeclaration() {
        return modelledeclaration;
    }

    public void setModelledeclaration(Modelledeclaration modelledeclaration) {
        this.modelledeclaration = modelledeclaration;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "This model already exist"));
    }

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the blank spaces"));
    }

}
