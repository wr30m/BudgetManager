package com.wr30mggmail.budgetmanager.model;

/**
 * Created by Kirabot on 06.12.2017.
 */

public class PurchaseCategory {
    private String label;
    private Double amount;

    public PurchaseCategory(String label, Double amount) {
        this.label = label;
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
