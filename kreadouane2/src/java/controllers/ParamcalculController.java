/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
import entities.Paramcalcul;
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
import sessions.ParamcalculFacadeLocal;
import sessions.CadFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class ParamcalculController implements Serializable {

    @EJB
    private ParamcalculFacadeLocal paramcalculFacade;
    private List<Paramcalcul> listParamcalcul = new ArrayList<>();
    private Paramcalcul paramcalcul = new Paramcalcul();
    private String operation;
    private String msg;
    private Paramcalcul paramcalcul1 = new Paramcalcul();
    private Paramcalcul paramcalcul2 = new Paramcalcul();
    private Paramcalcul paramcalcul3 = new Paramcalcul();
    private Paramcalcul paramcalcul4 = new Paramcalcul();
    private Paramcalcul paramcalcul5 = new Paramcalcul();
    private Paramcalcul paramcalcul6 = new Paramcalcul();
    private Paramcalcul paramcalcul7 = new Paramcalcul();
    private Paramcalcul paramcalcul8 = new Paramcalcul();
    private Paramcalcul paramcalcul9 = new Paramcalcul();
    private Paramcalcul paramcalcul10 = new Paramcalcul();
    private Paramcalcul paramcalcul11 = new Paramcalcul();
    private Paramcalcul paramcalcul12 = new Paramcalcul();

    public ParamcalculController() {
    }

    @PostConstruct
    public void init() {
        listParamcalcul.clear();
        listParamcalcul.addAll(paramcalculFacade.findAll());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
        
    }

    public void prepareCreate(ActionEvent e) {
        paramcalcul = new Paramcalcul();
        msg = "";
        action(e);
    }

    public void saveParamcalcul() {

        try {
//            check if the account does not exist
            if (paramcalculFacade.findByGuce(paramcalcul.getGuce()) == null) {

//                create the acconier
                paramcalculFacade.create(paramcalcul);
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
    
     public String modifParam() {
        
        return "formmodparamcal.xhtml?faces-redirect=true";

    }

     public String modifyParamcalcul() {
        
     try {
             if (paramcalculFacade.findByGuce(paramcalcul.getGuce()) == null) {
//                create the acconier
                    paramcalculFacade.edit(paramcalcul);
            saveMessage("modified");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(paramcalculFacade.findByGuce(paramcalcul.getGuce()).getId(), paramcalcul.getId())) {
                        paramcalculFacade.edit(paramcalcul);
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
       return "paramcalcul.xhtml?faces-redirect=true";
    }

   

    public void persist() {
        System.out.println(operation);
        switch (operation){

            case "modify":
                modifyParamcalcul();
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

    public ParamcalculFacadeLocal getParamcalculFacade() {
        return paramcalculFacade;
    }

    public void setParamcalculFacade(ParamcalculFacadeLocal paramcalculFacade) {
        this.paramcalculFacade = paramcalculFacade;
    }

    public List<Paramcalcul> getListParamcalcul() {
        return listParamcalcul;
    }

    public void setListParamcalcul(List<Paramcalcul> listParamcalcul) {
        this.listParamcalcul = listParamcalcul;
    }

    public Paramcalcul getParamcalcul() {
        return paramcalcul;
    }

    public void setParamcalcul(Paramcalcul paramcalcul) {
        this.paramcalcul = paramcalcul;
    }

    public Paramcalcul getParamcalcul1() {
        return paramcalcul1;
    }

    public void setParamcalcul1(Paramcalcul paramcalcul1) {
        this.paramcalcul1 = paramcalcul1;
    }

    public Paramcalcul getParamcalcul2() {
        return paramcalcul2;
    }

    public void setParamcalcul2(Paramcalcul paramcalcul2) {
        this.paramcalcul2 = paramcalcul2;
    }

    public Paramcalcul getParamcalcul3() {
        return paramcalcul3;
    }

    public void setParamcalcul3(Paramcalcul paramcalcul3) {
        this.paramcalcul3 = paramcalcul3;
    }

    public Paramcalcul getParamcalcul4() {
        return paramcalcul4;
    }

    public void setParamcalcul4(Paramcalcul paramcalcul4) {
        this.paramcalcul4 = paramcalcul4;
    }

    public Paramcalcul getParamcalcul5() {
        return paramcalcul5;
    }

    public void setParamcalcul5(Paramcalcul paramcalcul5) {
        this.paramcalcul5 = paramcalcul5;
    }

    public Paramcalcul getParamcalcul6() {
        return paramcalcul6;
    }

    public void setParamcalcul6(Paramcalcul paramcalcul6) {
        this.paramcalcul6 = paramcalcul6;
    }

    public Paramcalcul getParamcalcul7() {
        return paramcalcul7;
    }

    public void setParamcalcul7(Paramcalcul paramcalcul7) {
        this.paramcalcul7 = paramcalcul7;
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
