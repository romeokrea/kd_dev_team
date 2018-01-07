/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Paramsociete;
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
import sessions.ParamsocieteFacadeLocal;
import sessions.CadFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class ParamsocieteController implements Serializable {

    @EJB
    private ParamsocieteFacadeLocal paramsocieteFacade;
    private List<Paramsociete> listParamsociete = new ArrayList<>();
    private Paramsociete paramsociete = new Paramsociete();
    private String operation;
    private String msg;
    private Paramsociete nom = new Paramsociete();
    private Paramsociete adresse = new Paramsociete();
    private Paramsociete email = new Paramsociete();
    private Paramsociete numero = new Paramsociete();
    private Paramsociete regime = new Paramsociete();
    private Paramsociete valeur = new Paramsociete();
  
    public ParamsocieteController() {
    }

    @PostConstruct
    public void init() {
        listParamsociete.clear();
        listParamsociete.addAll(paramsocieteFacade.findAll());
        
        

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
        
    }

    public void prepareCreate(ActionEvent e) {
        paramsociete = new Paramsociete();
        msg = "";
        action(e);
    }

   

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public String modifParam() {
        
        return "formmodparamso.xhtml?faces-redirect=true";

    }

    
      public String modifyParamsociete() {
                
        try {
             if (paramsocieteFacade.findByNom(paramsociete.getNom()) == null) {

//                create the acconier
                paramsocieteFacade.edit(paramsociete);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(paramsocieteFacade.findByNom(paramsociete.getNom()).getId(), paramsociete.getId())) {
                        paramsocieteFacade.edit(paramsociete);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                   
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
        return "Paramsociete.xhtml?faces-redirect=true";
    }

   

    public void persist() {
        System.out.println(operation);
        switch (operation){

            case "modify":
                modifyParamsociete();
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

    public ParamsocieteFacadeLocal getParamsocieteFacade() {
        return paramsocieteFacade;
    }

    public void setParamsocieteFacade(ParamsocieteFacadeLocal paramsocieteFacade) {
        this.paramsocieteFacade = paramsocieteFacade;
    }

    public List<Paramsociete> getListParamsociete() {
        return listParamsociete;
    }

    public void setListParamsociete(List<Paramsociete> listParamsociete) {
        this.listParamsociete = listParamsociete;
    }

    public Paramsociete getParamsociete() {
        return paramsociete;
    }

    public void setParamsociete(Paramsociete paramsociete) {
        this.paramsociete = paramsociete;
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
