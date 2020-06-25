package com.example.myapplication.Modle;

public class MechanicalDataBase {
    String first_Name,
            last_Name,
            phone_Number,
            email,
            password,
            age,
            mech_firebase_id;

    public MechanicalDataBase() {
    }

    public MechanicalDataBase(String first_Name, String last_Name, String phone_Number,String age,
                              String email, String password,String mech_firebase_id) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone_Number = phone_Number;
        this.email = email;
        this.password = password;
        this.mech_firebase_id=mech_firebase_id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMech_firebase_id(String mech_firebase_id) {
        this.mech_firebase_id = mech_firebase_id;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getMech_firebase_id() {
        return mech_firebase_id;
    }
}
