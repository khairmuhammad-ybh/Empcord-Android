package com.khairmuhammad.transactions.models;

public class CompanyModel {

    private String _id;                   //company id
    private String company;                //company name

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return company;
    }
}
