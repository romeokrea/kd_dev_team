/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import entities.Article;
import entities.Cotation;
import entities.Redevanceservice;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.ArticleFacadeLocal;
import sessions.CotationFacadeLocal;
import sessions.RedevanceserviceFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class CotationController implements Serializable {

    @EJB
    private CotationFacadeLocal cotationFacade;
    private List<Cotation> listCotation = new ArrayList<>();
    private Cotation cotation = new Cotation();
    private String operation;
    private String msg;
    private Article article = new Article();
    private float montempFacture;
    private String incoterm;
    private float valeurFOB;
    private float poids;
    private float montantFret;
    private double assurance;
    private double transport;
    private float droitDaccise;
    private float tauxPRC;
    private int vide;
    private String nameArticle;
    @EJB
    private ArticleFacadeLocal articleFacade;
    @EJB
    private RedevanceserviceFacadeLocal redevanceserviceFacade;
    private List<Redevanceservice> listRedevanceservice = new ArrayList<>();
    private Redevanceservice redevanceservice = new Redevanceservice();
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private List<Utilisateur> listUtilisateur = new ArrayList<>();
    private Utilisateur utilisateur = new Utilisateur();
    private Account account = new Account();

    public CotationController() {
    }

    @PostConstruct
    public void init() {
        listCotation.clear();
        listCotation.addAll(cotationFacade.findAll());

    }
    
    public String chooseArticle() {
        return "chooseArticle.xhtml?faces-redirect=true";

    }
    
    public String choosen() {
        return "cotation.xhtml?faces-redirect=true";

    }
    
    public String nouvelle() {
        article = new Article();
        montempFacture = vide;
        incoterm = null;
        valeurFOB = vide;
        assurance = vide;
        poids = vide;
        montantFret = vide;
        transport = vide;
        cotation.setConteneur(null);
        droitDaccise = vide;
        tauxPRC = vide;
        
        return "cotation.xhtml?faces-redirect=true";

    }

//    public void clear() {
//        article = new Article();
//        montempFacture = vide;
//        incoterm = new String();
//        valeurFOB = vide;
//        assurance = vide;
//        cotation.setConteneur("");
//        poids = vide;
//        montantFret = vide;
//        transport = vide;
//        droitDaccise = vide;
//        tauxPRC = vide;
//
//    }
    public String calculer() {
        if (article == null|| montempFacture == 0.0 || incoterm == null || "".equals(incoterm) || valeurFOB == 0.0 || assurance == 0.0 || poids == 0.0 || montantFret == 0.0 || transport == 0.0 ) {
            fatal2();
            return null;
        }  else {
                cotation.setReferance("COT0" + (cotationFacade.findMax() + 1));
                double x = valeurFOB;
                cotation.setValeurimposable(x);
                double u = cotation.getValeurimposable() * article.getTaxe() / 100;
                cotation.setDroitdedouane(u);
                cotation.setDroitdaccise((cotation.getDroitdedouane() + cotation.getValeurimposable()) * (droitDaccise / 100));
                cotation.setTvacac((cotation.getValeurimposable() + cotation.getDroitdedouane() + cotation.getDroitdaccise()) * (19.25 / 100));
                double i = tauxPRC;
                cotation.setPrecomptesurachat(i);
                cotation.setCci(0.40 * cotation.getValeurimposable() / 100);//////////////////////////////
                cotation.setTci(0.60 * cotation.getValeurimposable() / 100);
                cotation.setT0(0.05 * cotation.getValeurimposable() / 100);
                cotation.setCia(0.2 * cotation.getValeurimposable() / 100);
                cotation.setRi(0.45 * cotation.getValeurimposable() / 100);

                cotation.setTaxesgs(0.95 * cotation.getValeurimposable() / 100);
                if (cotation.getTaxesgs() < 110000) {
                    cotation.setTaxesgs(110000.0);
                }
                cotation.setTaxepecea(0.45 * cotation.getValeurimposable() / 100);
                if (cotation.getTaxepecea() < 175218) {
                    cotation.setTaxepecea(175218.0);
                } else if (cotation.getTaxepecea() > 591699) {
                    cotation.setTaxepecea(591699.0);
                }
                redevanceservice.setTransport(transport);
                redevanceservice.setAssurance(assurance);
                if ("20".equals(cotation.getConteneur())) {
                    redevanceservice.setAcconage(0);
                    redevanceservice.setCaution(500000);
                    redevanceservice.setCommisionsurdebourse(0);
                    redevanceservice.setCommisionsurtransit(0);
                    redevanceservice.setControlsanitaire(12500);
                    redevanceservice.setGuce(12500);
                    if (cotation.getValeurimposable() < 1000000) {
                        redevanceservice.setHad(50000);
                    } else if ((cotation.getValeurimposable() >= 1000000) && (cotation.getValeurimposable() <= 2000000)) {
                        redevanceservice.setHad(80000);
                    } else if ((cotation.getValeurimposable() >= 2000001) && (cotation.getValeurimposable() <= 6000000)) {
                        redevanceservice.setHad(163000);
                    } else if ((cotation.getValeurimposable() >= 6000001) && (cotation.getValeurimposable() <= 10000000)) {
                        redevanceservice.setHad(240000);
                    } else {
                        int p = (int) (245000 + (0.5 * cotation.getValeurimposable() / 100));
                        redevanceservice.setHad(p);
                    }

                    redevanceservice.setImpressionsurbl(15000);
                    redevanceservice.setOuverturedossier(25000);
                    redevanceservice.setPad(0);
                    redevanceservice.setPhytosanitaire(17500);
                    redevanceservice.setScanning(60000);
                    redevanceservice.setTimbresurbl(20000);
                    redevanceservice.setTravailextralegal(10000);
                } else if ("40".equals(cotation.getConteneur())) {
                    redevanceservice.setAcconage(0);
                    redevanceservice.setCaution(1000000);
                    redevanceservice.setCommisionsurdebourse(0);
                    redevanceservice.setCommisionsurtransit(0);
                    redevanceservice.setControlsanitaire(12500);
                    redevanceservice.setGuce(12500);
                    if (cotation.getValeurimposable() < 1000000) {
                        redevanceservice.setHad(50000);
                    } else if ((cotation.getValeurimposable() >= 1000000) && (cotation.getValeurimposable() <= 2000000)) {
                        redevanceservice.setHad(80000);
                    } else if ((cotation.getValeurimposable() >= 2000001) && (cotation.getValeurimposable() <= 6000000)) {
                        redevanceservice.setHad(163000);
                    } else if ((cotation.getValeurimposable() >= 6000001) && (cotation.getValeurimposable() <= 10000000)) {
                        redevanceservice.setHad(240000);
                    } else {
                        int p = (int) (245000 + (0.5 * cotation.getValeurimposable() / 100));
                        redevanceservice.setHad(p);
                    }
                    redevanceservice.setImpressionsurbl(15000);
                    redevanceservice.setOuverturedossier(25000);
                    redevanceservice.setPad(0);
                    redevanceservice.setPhytosanitaire(17500);
                    redevanceservice.setScanning(85000);
                    redevanceservice.setTimbresurbl(20000);
                    redevanceservice.setTravailextralegal(10000);
                } else {
                    redevanceservice.setAcconage(vide);
                    redevanceservice.setAssurance((double) vide);
                    redevanceservice.setCaution(vide);
                    redevanceservice.setCommisionsurdebourse(vide);
                    redevanceservice.setCommisionsurtransit(vide);
                    redevanceservice.setControlsanitaire(vide);
                    redevanceservice.setGuce(vide);
                    redevanceservice.setHad(vide);
                    redevanceservice.setImpressionsurbl(vide);
                    redevanceservice.setOuverturedossier(vide);
                    redevanceservice.setPad(vide);
                    redevanceservice.setPhytosanitaire(vide);
                    redevanceservice.setScanning(vide);
                    redevanceservice.setTimbresurbl(vide);
                    redevanceservice.setTransport((double) vide);
                    redevanceservice.setTravailextralegal(vide);
                }
                double y = cotation.getValeurimposable() + cotation.getDroitdedouane() + cotation.getDroitdaccise() + cotation.getTvacac()
                        + cotation.getPrecomptesurachat() + cotation.getCci() + cotation.getTci() + cotation.getT0() + cotation.getCia() + cotation.getRi()
                        + cotation.getTaxesgs() + cotation.getTaxesgs() + redevanceservice.getAcconage()
                        + redevanceservice.getAssurance() + redevanceservice.getCaution() + redevanceservice.getCommisionsurdebourse() + redevanceservice.getCommisionsurtransit()
                        + redevanceservice.getControlsanitaire() + redevanceservice.getGuce() + redevanceservice.getHad() + redevanceservice.getImpressionsurbl()
                        + redevanceservice.getOuverturedossier() + redevanceservice.getPad() + redevanceservice.getPhytosanitaire() + redevanceservice.getScanning()
                        + redevanceservice.getTimbresurbl() + redevanceservice.getTransport() + redevanceservice.getTravailextralegal();
                cotation.setMontemp(y);

                return "calculeCotation.xhtml?faces-redirect=true";
            

        }

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";

    }

    public void valider() {

    }

    public void prepareCreate(ActionEvent e) {
        cotation = new Cotation();
        article = new Article();
        incoterm = new String();
        redevanceservice = new Redevanceservice();
        montempFacture = vide;
        valeurFOB = vide;
        poids = vide;
        montantFret = vide;
        assurance = vide;
        droitDaccise = vide;
        tauxPRC = vide;
        nameArticle = new String();
        msg = "";

        action(e);
    }

    public void remplir() {
        RequestContext.getCurrentInstance().execute("PF('wv_user').hide()");
        RequestContext.getCurrentInstance().execute("PF('wv_user').show()");

    }

    public void marchandise() {
        article = articleFacade.findByDescription(nameArticle);
        if(article==null){
            article = new Article();
            article.setDescription("");
            article.setTaxe(vide);
            article.setPecae(0);
            
        }

    }

    public void valeurAssurance() {
        assurance = (valeurFOB * 0.002) + 2500 + 0.1925 + 600;

    }

    public void valeurFOB() {
        if ("FOB".equals(incoterm)) {
            valeurFOB = montempFacture;
            assurance = (valeurFOB * 0.002) + 2500 + 0.1925 + 600;
        }

    }

    public String saveCotation() {

        try {

            cotation.setArtId(article);
            redevanceserviceFacade.create(redevanceservice);
            cotation.setRedId(redevanceserviceFacade.findAll().get(redevanceserviceFacade.findAll().size() - 1));
            account = (Account) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
            cotation.setUtiId(account.getUtiId());
            cotation.setPosition(article.getCode());
            cotation.setType("Standard");
            cotation.setStatus("Active");
            cotation.setDatecreation(new Date(System.currentTimeMillis()));
            cotationFacade.create(cotation);
            RequestContext.getCurrentInstance().execute("PF('wv_utilisateurs').hide()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }

        return "listCotation.xhtml?faces-redirect=true";
    }

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String consult() {
        return "consultCotation.xhtml?faces-redirect=true";
    }

    public String returnListCotation() {
        return "listCotation.xhtml?faces-redirect=true";
    }

    public void modifyCotation() {

        try {
            if (cotationFacade.findByReferance(cotation.getReferance()) == null) {

//                create the acconier
                cotation.setStatus("Active");
                cotationFacade.edit(cotation);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");
            } else {
                if (Objects.equals(cotationFacade.findByReferance(cotation.getReferance()).getId(), cotation.getId())) {
                    cotation.setStatus("Active");
                    cotationFacade.edit(cotation);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

                } else {
                    showMessageDialog("cette cotation exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void deleteCotation() {
        try {

            cotation.setStatus("Not active");
            cotationFacade.edit(cotation);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_userEdit').hide()");

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
                saveCotation();
                break;

            case "modify":
                modifyCotation();
                break;

            case "delete":
                deleteCotation();
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

    public CotationFacadeLocal getCotationFacade() {
        return cotationFacade;
    }

    public void setCotationFacade(CotationFacadeLocal cotationFacade) {
        this.cotationFacade = cotationFacade;
    }

    public List<Cotation> getListCotation() {
        return listCotation;
    }

    public void setListCotation(List<Cotation> listCotation) {
        this.listCotation = listCotation;
    }

    public Cotation getCotation() {
        return cotation;
    }

    public void setCotation(Cotation cotation) {
        this.cotation = cotation;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getMontempFacture() {
        return montempFacture;
    }

    public void setMontempFacture(float montempFacture) {
        this.montempFacture = montempFacture;
    }

    public float getValeurFOB() {
        return valeurFOB;
    }

    public void setValeurFOB(float valeurFOB) {
        this.valeurFOB = valeurFOB;
    }

    public float getMontantFret() {
        return montantFret;
    }

    public void setMontantFret(float montantFret) {
        this.montantFret = montantFret;
    }

    public double getAssurance() {
        return assurance;
    }

    public void setAssurance(double assurance) {
        this.assurance = assurance;
    }

    public float getDroitDaccise() {
        return droitDaccise;
    }

    public void setDroitDaccise(float droitDaccise) {
        this.droitDaccise = droitDaccise;
    }

    public float getTauxPRC() {
        return tauxPRC;
    }

    public void setTauxPRC(float tauxPRC) {
        this.tauxPRC = tauxPRC;
    }

    public void setDroitDaccise(int droitDaccise) {
        this.droitDaccise = droitDaccise;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public int getVide() {
        return vide;
    }

    public void setVide(int vide) {
        this.vide = vide;
    }

    public ArticleFacadeLocal getArticleFacade() {
        return articleFacade;
    }

    public void setArticleFacade(ArticleFacadeLocal articleFacade) {
        this.articleFacade = articleFacade;
    }

    public RedevanceserviceFacadeLocal getRedevanceserviceFacade() {
        return redevanceserviceFacade;
    }

    public void setRedevanceserviceFacade(RedevanceserviceFacadeLocal redevanceserviceFacade) {
        this.redevanceserviceFacade = redevanceserviceFacade;
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

    public List<Redevanceservice> getListRedevanceservice() {
        return listRedevanceservice;
    }

    public void setListRedevanceservice(List<Redevanceservice> listRedevanceservice) {
        this.listRedevanceservice = listRedevanceservice;
    }

    public Redevanceservice getRedevanceservice() {
        return redevanceservice;
    }

    public void setRedevanceservice(Redevanceservice redevanceservice) {
        this.redevanceservice = redevanceservice;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public void fatal2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "file all the required spaces with values"));
    }

}
