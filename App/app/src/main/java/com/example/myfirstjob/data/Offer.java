package com.example.myfirstjob.data;

import com.example.myfirstjob.MainActivity;
import com.example.myfirstjob.ui.UserView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public abstract class Offer implements Serializable {
    private String companyName;
    private String offerName;
    private final int offerId;

    private enum typeOfWork {
        PRESENTIAL,
        SEMIPRESENTIAL,
        DISTANCE
    }

    private int iTypeOfWork;
    private int salary;
    private boolean activeOffer;
    private String description;
    private final String owner;
    private LinkedList<String> inscrp;
    private LinkedList<String> inscrp_aproved;

    /**
     * Clase Abstracta Oferta
     *
     * @param companyName
     * @param offerName
     * @param salary
     * @param activeOffer
     * @param description
     * @param iTypeOfWork
     */
    public Offer(String companyName, String offerName, int salary, boolean activeOffer, String description, int iTypeOfWork) {
        this.offerId = getNewId();
        this.companyName = companyName;
        this.offerName = offerName;
        this.salary = salary;
        this.activeOffer = activeOffer;
        this.description = description;
        this.iTypeOfWork = iTypeOfWork;
        this.owner = UserView.user.getEmail();
        this.inscrp = new LinkedList<>();
        this.inscrp_aproved = new LinkedList<>();
    }

    public Integer getNewId() {
        return ++MainActivity.lastOfferID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isActiveOffer() {
        return activeOffer;
    }

    public void setActiveOffer(boolean activeOffer) {
        this.activeOffer = activeOffer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getiTypeOfWork() {
        return iTypeOfWork;
    }

    public void setiTypeOfWork(int iTypeOfWork) {
        this.iTypeOfWork = iTypeOfWork;
    }

    public String getOwner() {
        return owner;
    }

    public void addSub(String email) {
        inscrp.add(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return offerId == offer.offerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }

    public LinkedList<String> getInscrp() {
        return inscrp;
    }

    public LinkedList<String> getInscrp_aproved() {
        return inscrp_aproved;
    }

    public void setInscrp_aproved(LinkedList<String> inscrp_aproved) {
        this.inscrp_aproved = inscrp_aproved;
    }

    public void addInscrp_aproved(String s) {
        this.inscrp_aproved.add(s);
    }

    public boolean isSuscribed(String s) {
        return this.inscrp.contains(s);
    }

    public boolean isSuscribedAproved(String s) {
        return this.inscrp_aproved.contains(s);
    }

    public void removeUser(String s) {
        this.inscrp.remove(s);
        if (this.inscrp_aproved.contains(s)) {
            this.inscrp_aproved.remove(s);
        }
    }
}
