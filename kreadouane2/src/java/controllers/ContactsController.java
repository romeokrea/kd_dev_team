/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Contact;
import entities.Utilisateur;
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
import sessions.AcconierFacadeLocal;
import sessions.AccountFacadeLocal;
import sessions.AssureurFacadeLocal;
import sessions.BanqueFacadeLocal;
import sessions.CadFacadeLocal;
import sessions.ContactFacadeLocal;
import sessions.TransporteurFacadeLocal;
import sessions.UtilisateurFacadeLocal;

/**
 *
 * @author samourai venimeux
 */
public class ContactsController implements Serializable {

    @EJB
    private ContactFacadeLocal contactFacade;
    private List<Contact> listContact = new ArrayList<>();
    private Contact contact = new Contact();
    private String operation;
    private String msg;
    @EJB
    private CadFacadeLocal cadFacade;
    private List<Contact> listContacts = new ArrayList<>();
    @EJB
    private TransporteurFacadeLocal transporteurFacade;
    @EJB
    private AcconierFacadeLocal acconierFacade;
    @EJB
    private BanqueFacadeLocal banqueFacade;
     @EJB
    private AssureurFacadeLocal assureurFacade;

    public ContactsController() {
    }

     public String contactsCad() {
        init();
        return "contactCAD.xhtml?faces-redirect=true";

    }
    public void init() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByCadIdStatus(CADController.getIdCad(), "Active"));
        

    }
    public String contactsTrans() {
        initTrans();
        return "contactTransporteur.xhtml?faces-redirect=true";

    }
    public void initTrans() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByTransIdStatus(TransporteurController.getIdTrans(), "Active"));

    }
    public String contactsAcc() {
        initAcc();
        return "contactAcconier.xhtml?faces-redirect=true";

    }
    public void initAcc() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByAccIdStatus(AcconierController.getIdAcc(), "Active"));

    }
    public String contactsBanq() {
        initBanq();
        return "contactBanque.xhtml?faces-redirect=true";

    }
    public void initBanq() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByBanqueIdStatus(BanqueController.getIdBanq(), "Active"));

    }
    
    public String contactsAss() {
        initAss();
        return "contactAssureur.xhtml?faces-redirect=true";

    }
    public void initAss() {
        listContacts.clear();
        listContacts.addAll(contactFacade.findByAssureurIdStatus(AssureurController.getIdAss(), "Active"));

    }
//    public String contactsAss() {
//        initAss();
//        return "contactAcconier.xhtml?faces-redirect=true";
//
//    }
//    public void initAss() {
//        listContacts.clear();
//        listContacts.addAll(contactFacade.findByAssureurIdStatus(AssureurController.getIdAcc(), "Active"));
//
//    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        System.out.println(operation);
        msg = "";
    }
    
    public String create2() {
        contact = new Contact();
        return "formcontactacconier.xhtml?faces-redirect=true";

    }
    
    public String create3() {
        contact = new Contact();
        return "formcontactassureur.xhtml?faces-redirect=true";

    }
    
     public String create4() {
        contact = new Contact();
        return "formcontactbanque.xhtml?faces-redirect=true";

    }
     
     public String create5() {
        contact = new Contact();
        return "formcontactcad.xhtml?faces-redirect=true";

    }
    
     public String create6() {
        contact = new Contact();
        return "formcontacttransporteur.xhtml?faces-redirect=true";

    }
     
      public String modif1() {
        
        return "modifcontactcad.xhtml?faces-redirect=true";

    }
      
    public String modif2() {
        
        return "modifcontacttrans.xhtml?faces-redirect=true";

    }  

     public String modif3() {
        
        return "modifcontactbanq.xhtml?faces-redirect=true";

    }
      
    public String modif4() {
        
        return "modifcontactass.xhtml?faces-redirect=true";

    } 
    
    public String modif5() {
        
        return "modifcontactacc.xhtml?faces-redirect=true";

    } 
    
    public void prepareCreate(ActionEvent e) {
        contact = new Contact();
        msg = "";
        action(e);
    }

    public String saveContactCAD() {

        try {
            
            if (contact.getEmail()== null || "".equals(contact.getName()) || contact.getName().trim().length() == 0 || "".equals(contact.getSurname()) || contact.getSurname().trim().length() == 0 || "".equals(contact.getPhone()) || contact.getPhone().trim().length() == 0 || "".equals(contact.getFonction()) || contact.getFonction().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
            System.out.println("save contact");
//            check if the account does not exist
            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setCadId(cadFacade.find(CADController.getIdCad()));
                contact.setStatus("Active");
                contactFacade.create(contact);
                return "contactCAD.xhtml?faces-redirect=true";
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
    public String saveContactTrans() {

        try {
            
            if (contact.getEmail()== null || "".equals(contact.getName()) || contact.getName().trim().length() == 0 || "".equals(contact.getSurname()) || contact.getSurname().trim().length() == 0 || "".equals(contact.getPhone()) || contact.getPhone().trim().length() == 0 || "".equals(contact.getFonction()) || contact.getFonction().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
            System.out.println("save contact");
//            check if the account does not exist
            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setTraId(transporteurFacade.find(TransporteurController.getIdTrans()));
                contact.setStatus("Active");
                contactFacade.create(contact);
                return "contactTransporteur.xhtml?faces-redirect=true";
            } else {
                fatal();
                    return null;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            initTrans();
        }
        
    }
    public String saveContactAcc() {

        try {
            
            if (contact.getEmail()== null || "".equals(contact.getName()) || contact.getName().trim().length() == 0 || "".equals(contact.getSurname()) || contact.getSurname().trim().length() == 0 || "".equals(contact.getPhone()) || contact.getPhone().trim().length() == 0 || "".equals(contact.getFonction()) || contact.getFonction().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
            System.out.println("save contact");
//            check if the account does not exist
            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setAccId(acconierFacade.find(AcconierController.getIdAcc()));
                contact.setStatus("Active");
                contactFacade.create(contact);
                return "contactAcconier.xhtml?faces-redirect=true";
            } else {
                fatal();
                    return null;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        } finally {
            initAcc();
        }
        
    }
    
    public String saveContactBanq() {

        try {
            
            if (contact.getEmail()== null || "".equals(contact.getName()) || contact.getName().trim().length() == 0 || "".equals(contact.getSurname()) || contact.getSurname().trim().length() == 0 || "".equals(contact.getPhone()) || contact.getPhone().trim().length() == 0 || "".equals(contact.getFonction()) || contact.getFonction().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
            System.out.println("save contact");
//            check if the account does not exist
            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setBanId(banqueFacade.find(BanqueController.getIdBanq()));
                contact.setStatus("Active");
                contactFacade.create(contact);
                return "contactBanque.xhtml?faces-redirect=true";
            } else {
                fatal();
                    return null;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            initBanq();
        }
         
    }
    
    public String saveContactAss() {

        try {
            
            if (contact.getEmail()== null || "".equals(contact.getName()) || contact.getName().trim().length() == 0 || "".equals(contact.getSurname()) || contact.getSurname().trim().length() == 0 || "".equals(contact.getPhone()) || contact.getPhone().trim().length() == 0 || "".equals(contact.getFonction()) || contact.getFonction().trim().length() == 0) {

                fatal2();
                return null;
            } else {
            
            System.out.println("save contact");
//            check if the account does not exist
            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setAssId(assureurFacade.find(AssureurController.getIdAss()));
                contact.setStatus("Active");
                contactFacade.create(contact);
                return "contactAssureur.xhtml?faces-redirect=true";
            } else {
                fatal();
                    return null;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            initAss();
        }
        
    }

    public void showMessageDialog(String x) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "you have an error", x);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String modifyContact() {

        try {

            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setStatus("Active");
                contactFacade.edit(contact);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");
            } else {
                if (Objects.equals(contactFacade.findByName(contact.getName()).getId(), contact.getId())) {
                    contactFacade.edit(contact);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");

                } else {
                    showMessageDialog("cet Contact exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "contactCAD.xhtml?faces-redirect=true";
        }
    }
    public String modifyContactTrans() {

        try {

            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setStatus("Active");
                contactFacade.edit(contact);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");
            } else {
                if (Objects.equals(contactFacade.findByName(contact.getName()).getId(), contact.getId())) {
                    contactFacade.edit(contact);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");

                } else {
                    showMessageDialog("cet Contact exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initTrans();
            return "contactTransporteur.xhtml?faces-redirect=true";
        }
    }

    public String modifyContactAcc() {

        try {

            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setStatus("Active");
                contactFacade.edit(contact);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");
            } else {
                if (Objects.equals(contactFacade.findByName(contact.getName()).getId(), contact.getId())) {
                    contactFacade.edit(contact);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");

                } else {
                    showMessageDialog("cet Contact exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initAcc();
            return "contactAcconier.xhtml?faces-redirect=true";
        }
    }
    
    public String modifyContactAss() {

        try {

            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setStatus("Active");
                contactFacade.edit(contact);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");
            } else {
                if (Objects.equals(contactFacade.findByName(contact.getName()).getId(), contact.getId())) {
                    contactFacade.edit(contact);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");

                } else {
                    showMessageDialog("cet Contact exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initAss();
            return "contactAssureur.xhtml?faces-redirect=true";
        }
    }
    
    
    
    
     public String modifyContactBanq() {

        try {

            if (contactFacade.findByName(contact.getName()) == null) {

//                create the acconier
                contact.setStatus("Active");
                contactFacade.edit(contact);
                saveMessage("modified");
                RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");
            } else {
                if (Objects.equals(contactFacade.findByName(contact.getName()).getId(), contact.getId())) {
                    contactFacade.edit(contact);
                    saveMessage("modified");
                    RequestContext.getCurrentInstance().execute("PF('edit_contact').hide()");

                } else {
                    showMessageDialog("cet Contact exist deja");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initBanq();
            return "contactBanque.xhtml?faces-redirect=true";
        }
    }
    
    public String deleteContact() {
        try {

            contact.setStatus("Not active");
            contactFacade.edit(contact);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete_contact').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            init();
            return "contactCAD.xhtml?faces-redirect=true";
        }
    }
    public String deleteContactTrans() {
        try {

            contact.setStatus("Not active");
            contactFacade.edit(contact);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete_contact').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initTrans();
            return "contactTransporteur.xhtml?faces-redirect=true";
        }
    }

    public String deleteContactAcc() {
        try {

            contact.setStatus("Not active");
            contactFacade.edit(contact);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete_contact').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initAcc();
            return "contactAcconier.xhtml?faces-redirect=true";
        }
    }
    
    public String deleteContactAss() {
        try {

            contact.setStatus("Not active");
            contactFacade.edit(contact);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete_contact').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initAss();
            return "contactAssureur.xhtml?faces-redirect=true";
        }
    }
    
    public String deleteContactBanq() {
        try {

            contact.setStatus("Not active");
            contactFacade.edit(contact);
            saveMessage("deleted");
            RequestContext.getCurrentInstance().execute("PF('wv_delete_contact').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initBanq();
            return "contactBanque.xhtml?faces-redirect=true";
        }
    }
    public void persist() {
        System.out.println(operation);
        switch (operation) {

            case "add":
                saveContactCAD();
                break;

            case "modify":
                modifyContact();
                break;

            case "delete":
                deleteContact();
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

    public ContactFacadeLocal getContactFacade() {
        return contactFacade;
    }

    public void setContactFacade(ContactFacadeLocal contactFacade) {
        this.contactFacade = contactFacade;
    }

    public List<Contact> getListContact() {
        return listContact;
    }

    public void setListContact(List<Contact> listContact) {
        this.listContact = listContact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public CadFacadeLocal getCadFacade() {
        return cadFacade;
    }

    public void setCadFacade(CadFacadeLocal cadFacade) {
        this.cadFacade = cadFacade;
    }

    public List<Contact> getListContacts() {
        return listContacts;
    }

    public void setListContacts(List<Contact> listContacts) {
        this.listContacts = listContacts;
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
