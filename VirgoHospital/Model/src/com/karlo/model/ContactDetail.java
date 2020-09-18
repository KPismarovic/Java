
package com.karlo.model;


public class ContactDetail {
    private int id;
    private String telephoneWork;
    private String telephoneHome;
    private String mobile;
    private String pager;
    private String fax;
    private String email;
    private PersonAddress presentAddress;
    private PersonAddress permanentAddress;
    private PersonalDetails personalDetails;

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public ContactDetail(int id, String telephoneWork, String telephoneHome, String mobile, String pager, String fax, String email, PersonAddress presentAddress, PersonAddress permanentAddress, PersonalDetails personalDetails) {
        this.id = id;
        this.telephoneWork = telephoneWork;
        this.telephoneHome = telephoneHome;
        this.mobile = mobile;
        this.pager = pager;
        this.fax = fax;
        this.email = email;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.personalDetails = personalDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonAddress getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(PersonAddress presentAddress) {
        this.presentAddress = presentAddress;
    }

    public PersonAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PersonAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }
    

    public ContactDetail(int id, String telephoneWork, String telephoneHome, String mobile, String pager, String email, PersonAddress presentAddress, PersonAddress permanentAddress, PersonalDetails personalDetails) {
        this.id = id;
        this.telephoneWork = telephoneWork;
        this.telephoneHome = telephoneHome;
        this.mobile = mobile;
        this.pager = pager;
        this.email = email;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.personalDetails = personalDetails;
    }
    
    public ContactDetail(String telephoneHome, String mobile) {
        setTelephoneHome(telephoneHome);
        setMobile(mobile);
    }

    public ContactDetail(String telephoneWork, String telephoneHome, String mobile, String pager, String email, String fax) {
        setTelephoneWork(telephoneWork);
        setTelephoneHome(telephoneHome);
        setMobile(mobile);
        setPager(pager);
        setEmail(email);
        setFax(fax);
    }

    public String getTelephoneWork() {
        return telephoneWork;
    }

    public void setTelephoneWork(String telephoneWork) {
        this.telephoneWork = telephoneWork;
    }

    public String getTelephoneHome() {
        return telephoneHome;
    }

    public void setTelephoneHome(String telephoneHome) {
        this.telephoneHome = telephoneHome;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
