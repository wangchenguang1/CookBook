package com.example.wcg.cookbook.Model.Classfy;

import java.util.ArrayList;

/**
 * Created by wcg on 16/3/17.
 */
public class CKListData {

    private ArrayList<CKListContent> tngou;
    private String total;
    private boolean status;


    public ArrayList<CKListContent> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<CKListContent> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "CKListData{" +
                "tngou=" + tngou +
                ", total='" + total + '\'' +
                ", status=" + status +
                '}';
    }
}
