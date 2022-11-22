package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USDWithDate")
public class USD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String baseCurrency;

    private String currency;

    private Double saleRateNB;

    private Double purchaseRateNB;

    private Double saleRate;

    private Double purchaseRate;

    private String date;

     public String getDate() {
         return date;
     }

     public void setDate(String date) {
         this.date = date;
     }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public Double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "USD{" +
                "id=" + id +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate + '}';
    }
}
