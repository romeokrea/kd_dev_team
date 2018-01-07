/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import entities.Utilisateur;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.AccountFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class UserController implements Serializable {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private List<Utilisateur> listUtilisateur = new ArrayList<>();
    private Utilisateur utilisateur = new Utilisateur();
    private String operation;
    private String msg;
    private String confirmation;

    @EJB
    private AccountFacadeLocal accountFacade;
    private Account account = new Account();
    private List<Account> listAccount = new ArrayList<>();

    public UserController() {
    }

    @PostConstruct
    public void init() {
        listAccount.clear();
        listAccount.addAll(accountFacade.findByStatus("Active"));

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        if (!"add".equals(operation)) {

            utilisateur.setId(account.getUtiId().getId());
            utilisateur.setName(account.getUtiId().getName());
            utilisateur.setSurname(account.getUtiId().getSurname());
            utilisateur.setEmail(account.getUtiId().getEmail());
            utilisateur.setPhone(account.getUtiId().getPhone());
            utilisateur.setSexe(account.getUtiId().getSexe());
            utilisateur.setMatricule(account.getUtiId().getMatricule());
        }
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        utilisateur = new Utilisateur();
        account = new Account();
        msg = "";
        action(e);
    }

    public void saveUser() {

        try {
//            check if the account does not exist
            String l = account.getLogin();
            String p = ((Integer) account.getPassword().hashCode()).toString();

            if (accountFacade.findByLoginMdp(l, p) == null) {

//                create the user
                utilisateurFacade.create(utilisateur);

//                create the account
                account.setStatus("Active");
                account.setPassword(((Integer) account.getPassword().hashCode()).toString());
                account.setUtiId(utilisateurFacade.findByUserMatricule(utilisateur.getMatricule()));// must find user
                accountFacade.create(account);

                saveMessage("saved");
                RequestContext.getCurrentInstance().execute("PF('wv_user').hide()");
            } else {
                showMessageDialog("This login and password is already in use");
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

    public void modifyUser() {

        try {
            String l = account.getLogin();
            String p = ((Integer) account.getPassword().hashCode()).toString();
            if (accountFacade.findByLoginMdp(l, p) == null) {

                utilisateurFacade.edit(utilisateur);
                account.setPassword(((Integer) account.getPassword().hashCode()).toString());
                accountFacade.edit(account);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(accountFacade.findByLoginMdp(l, p).getId(), account.getId())) {
                    utilisateurFacade.edit(utilisateur);
                    account.setPassword(((Integer) account.getPassword().hashCode()).toString());
                    accountFacade.edit(account);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                    showMessageDialog("This login and password is already in use");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void deleteUser() {
        try {

            account.setStatus("Not active");
            accountFacade.edit(account);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveUser();
                break;

            case "modify":
                modifyUser();
                break;

            case "delete":
                deleteUser();
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

    public AccountFacadeLocal getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(AccountFacadeLocal accountFacade) {
        this.accountFacade = accountFacade;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UtilisateurFacadeLocal getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateurFacadeLocal utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    public List<Utilisateur> getListUtilisateur() {
        return listUtilisateur;
    }

    public void setListUtilisateur(List<Utilisateur> listUtilisateur) {
        this.listUtilisateur = listUtilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Account> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
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

}
