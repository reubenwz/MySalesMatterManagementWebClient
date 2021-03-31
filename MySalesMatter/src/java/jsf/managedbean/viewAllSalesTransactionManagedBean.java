/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.SalesTransactionEntitySessionBeanLocal;
import entity.SalesTransactionEntity;
import entity.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.exception.SalesTransactionNotFoundException;

/**
 *
 * @author sylvia
 */
@Named(value = "viewAllSalesTransactionManagedBean")
@SessionScoped
public class viewAllSalesTransactionManagedBean implements Serializable {

    private List<SalesTransactionEntity> salesTransactionNotCompleted;
    private List<SalesTransactionEntity> salesTransactionCompleted;
    private UserEntity currentUser;

    @EJB
    private SalesTransactionEntitySessionBeanLocal salesTransactionEntitySessionBeanLocal;

    public viewAllSalesTransactionManagedBean() {
        currentUser = new UserEntity();
        salesTransactionNotCompleted = new ArrayList<>();
        salesTransactionCompleted = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {
        currentUser = (UserEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
        List<SalesTransactionEntity> transactions = currentUser.getTransactions();
        List<SalesTransactionEntity> newSalesTransactionCompleted = new ArrayList<>();
        List<SalesTransactionEntity> newSalesTransactionNotCompleted = new ArrayList<>();

        if (!transactions.isEmpty()) {
            for (SalesTransactionEntity s : transactions) {
                if (s.getStatus().toString().toLowerCase().equals("paid")) {
                    newSalesTransactionCompleted.add(s);
                } else {
                    newSalesTransactionNotCompleted.add(s);
                }
            }
        }
        try {
            for (SalesTransactionEntity s : newSalesTransactionCompleted) {
                salesTransactionCompleted.add(salesTransactionEntitySessionBeanLocal.retrieveTransactionById(s.getSalesTransactionId()));
            }
            for (SalesTransactionEntity s : newSalesTransactionNotCompleted) {
                salesTransactionNotCompleted.add(salesTransactionEntitySessionBeanLocal.retrieveTransactionById(s.getSalesTransactionId()));
            }
        } catch (SalesTransactionNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while loading conversations: " + ex.getMessage(), null));
        }

    }

//    public List<SalesTransactionEntity> retrieveSalesTransactionCompleted() {
//        List<SalesTransactionEntity> transactions = currentUser.getTransactions();
//        List<SalesTransactionEntity> newT = new ArrayList<>();
//        if (!transactions.isEmpty()) {
//            for (SalesTransactionEntity s : transactions) {
//                if (s.getStatus().toString().toLowerCase().equals("paid")) {
//                    newT.add(s);
//                }
//            }
//        }
//
//        return newT;
//    }

    public List<SalesTransactionEntity> getSalesTransactionNotCompleted() {
        return salesTransactionNotCompleted;
    }

    public void setSalesTransactionNotCompleted(List<SalesTransactionEntity> salesTransactionNotCompleted) {
        this.salesTransactionNotCompleted = salesTransactionNotCompleted;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public List<SalesTransactionEntity> getSalesTransactionCompleted() {
        return salesTransactionCompleted;
    }

    public void setSalesTransactionCompleted(List<SalesTransactionEntity> salesTransactionCompleted) {
        this.salesTransactionCompleted = salesTransactionCompleted;
    }

}
