package com.example.myfirstjob.data;

public class JobOffer extends Offer {
    private int iTypeOfContract;

    private enum typeOfContract {
        UNDEFINED,
        TEMPORARY
    }

    public JobOffer(String companyName, String offerName, int salary, boolean activeOffer, String description, int iTypeOfWork, int iTypeOfContract) {
        super(companyName, offerName, salary, activeOffer, description, iTypeOfWork);
        this.iTypeOfContract = iTypeOfContract;
    }

    public int getTypeOfContract() {
        return iTypeOfContract;
    }

    public void setsTypeOfContract(int iTypeOfContract) {
        this.iTypeOfContract = iTypeOfContract;
    }
}
