package com.example.myfirstjob.data;


public class Practice extends Offer {
    private int hours;

    /**
     * Clase Práctica
     *
     * @param companyName
     * @param offerName
     * @param salary
     * @param activeOffer
     * @param description
     * @param iTypeOfWork
     * @param hours
     */
    public Practice(String companyName, String offerName, int salary, boolean activeOffer, String description, int iTypeOfWork, int hours) {
        super(companyName, offerName, salary, activeOffer, description, iTypeOfWork);
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
