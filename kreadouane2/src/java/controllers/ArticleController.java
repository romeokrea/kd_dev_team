/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Article;
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

/**
 *
 * @author samourai venimeux
 */
public class ArticleController implements Serializable {

    @EJB
    private ArticleFacadeLocal articleFacade;
    private List<Article> listArticle = new ArrayList<>();
    private Article article = new Article();
    private String operation;
    private String msg;
    private String msg1;
    private String msg2;
    private String msg3;
    private String msg4;
    private String msg5;
    private String msg6;

    public ArticleController() {
    }

    @PostConstruct
    public void init() {
        listArticle.clear();
        listArticle.addAll(articleFacade.findByStatus("Active"));

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";

    }

    public String create() {
        article = new Article();
        return "addAricle.xhtml?faces-redirect=true";

    }

    public String modify() {
        return "modifyArticle.xhtml?faces-redirect=true";

    }

    public String consult() {
        return "consultArticle.xhtml?faces-redirect=true";

    }

    public String returnList() {
        return "article.xhtml?faces-redirect=true";

    }

    public void prepareCreate(ActionEvent e) {
        article = new Article();
        msg = "";
        action(e);
    }

    public String saveArticle() {

        try {
            if (article.getApe() == null || "".equals(article.getCode())||article.getCode().trim().length() == 0 || "".equals(article.getDescription())||article.getDescription().trim().length() == 0 || article.getTaxe() == null || "".equals(article.getUnite())||article.getUnite().trim().length() == 0) {
                fatal2();
                return null;
            } else {

//            check if the account does not exist
                if (articleFacade.findByCode(article.getCode()) == null) {

//                create the acconier
                    article.setStatus("Active");
                    articleFacade.create(article);
                    return "article.xhtml?faces-redirect=true";
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

    public String modifyArticle() {

        try {
            if (article.getApe() == null || "".equals(article.getCode())||article.getCode().trim().length() == 0 || "".equals(article.getDescription())||article.getDescription().trim().length() == 0 || article.getTaxe() == null || "".equals(article.getUnite())||article.getUnite().trim().length() == 0) {
                fatal2();
                return null;
            } else {
                if (articleFacade.findByCode(article.getCode()) == null) {

//                create the acconier
                    article.setStatus("Active");
                    articleFacade.edit(article);
                    return "article.xhtml?faces-redirect=true";
                } else {
                    if (Objects.equals(articleFacade.findByCode(article.getCode()).getId(), article.getId())) {
                        article.setStatus("Active");
                        articleFacade.edit(article);
                        return "article.xhtml?faces-redirect=true";

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

    public String deleteArticle() {
        try {

            article.setStatus("Not active");
            articleFacade.edit(article);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "article.xhtml?faces-redirect=true";
        }
    }

    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveArticle();
                break;

            case "modify":
                modifyArticle();
                break;

            case "delete":
                deleteArticle();
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

    public ArticleFacadeLocal getArticleFacade() {
        return articleFacade;
    }

    public void setArticleFacade(ArticleFacadeLocal articleFacade) {
        this.articleFacade = articleFacade;
    }

    public List<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(List<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    public String getMsg4() {
        return msg4;
    }

    public void setMsg4(String msg4) {
        this.msg4 = msg4;
    }

    public String getMsg5() {
        return msg5;
    }

    public void setMsg5(String msg5) {
        this.msg5 = msg5;
    }

    public String getMsg6() {
        return msg6;
    }

    public void setMsg6(String msg6) {
        this.msg6 = msg6;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "This article already exist"));
    }

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the blank spaces"));
    }

}
