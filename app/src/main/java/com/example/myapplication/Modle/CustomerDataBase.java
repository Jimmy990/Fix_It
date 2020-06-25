package com.example.myapplication.Modle;

public class CustomerDataBase {

    String first_Name,
            last_Name,
            phone_Number,
            email,
            password,
            age,
            cus_firebase_id;

    public CustomerDataBase() {
    }

    public CustomerDataBase(String first_Name, String last_Name, String phone_Number, String age,
                            String email, String password, String cus_firebase_id) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone_Number = phone_Number;
        this.email = email;
        this.password = password;
        this.age = age;
        this.cus_firebase_id = cus_firebase_id;

    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCus_firebase_id(String cus_firebase_id) {
        this.cus_firebase_id = cus_firebase_id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCus_firebase_id() {
        return cus_firebase_id;
    }
}

