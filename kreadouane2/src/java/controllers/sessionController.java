/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;
import sessions.AccountFacadeLocal;

/**
 *
 * @author admin
 */
public class sessionController implements Serializable {

    @EJB
    private AccountFacadeLocal accountFacade;
    private Account currentUser = new Account();
//    private String org = " ";
//    private static Organization currentOrganization = new Organization();
//    private static Client currentClient = new Client();
//    private static Driver currentDriver = new Driver();
//    @EJB
//    private DriverFacadeLocal driverFacade;
//    private Driver driver = new Driver();
//    @EJB
//    private OrganizationFacadeLocal organizationFacade;
//    @EJB
//    private ClientFacadeLocal clientFacade;
//    private Client client = new Client();
//
//    @EJB
//    private UserrsFacadeLocal userFacade;
//    private Userrs user = new Userrs();
//    @EJB
//    private LogfileFacadeLocal logfileFacade;

    private String path;
    private String language;

    private String name;
    private String error1 = "";
    private String error2 = "";

    /**
     * Creates a new instance of sessionController
     */
    public sessionController() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                ((FacesContext.getCurrentInstance()).getExternalContext()).redirect("index.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String english() {
        language = "en";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String french() {
        language = "fr";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String authenticate() {
        try {
            if("".equals(currentUser.getLogin()) || "".equals(currentUser.getPassword())){
            error2 = "";
            error1 = "login and password is required";
            return "null";
            }else{
                currentUser = accountFacade.findByLoginMdp(currentUser.getLogin(), ((Integer) currentUser.getPassword().hashCode()).toString());
            if ((currentUser != null) && ("Active".equals(currentUser.getStatus()))) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                return "welcome.xhtml?faces-redirect=true";
            } else {
                currentUser = new Account();
                error1 = "";
                error2 = "your login or password is incorrect";
                return "null";
            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            currentUser = new Account();
            return "null";
        }
    }

    public void refreshLogin() {
        System.out.println("login");
        currentUser = new Account();
    }

    public String logOut() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "index.xhtml?faces-redirect=true";
        }
    }

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    private static boolean isNullOrBlank(String s) {
        return (s == null || s.trim().equals(""));
    }

    public void logFile(String name) {
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountFacadeLocal getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(AccountFacadeLocal accountFacade) {
        this.accountFacade = accountFacade;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
    
    

}
