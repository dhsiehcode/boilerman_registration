package com.boilerman.signupV3.DTO;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Relay {


    private int members;

    private String paymentNum;

    private String teamName;

    private String state;

    private String city;

    private Long phoneNumber;



    private @GeneratedValue @Id Long id;

    public Relay() {

    }

    public Relay(int members, String paymentNum, String teamName, String state, String city, String phoneNumber) {

        this.members = members;
        this.paymentNum = paymentNum;
        this.teamName = teamName;
        this.state = state;
        this.city = city;
        this.phoneNumber = Long.parseLong(phoneNumber.replaceAll("-", ""));;

    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getPaymetNum() {
        return paymentNum;
    }

    public void setPaymetNum(String paymetNum) {
        this.paymentNum = paymetNum;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getValues() {

        List<String> output = new ArrayList<>();

        output.add(Integer.toString(getMembers()));
        output.add(getPaymetNum());
        output.add(getTeamName());
        output.add(getState());
        output.add(getCity());
        output.add(Long.toString(getPhoneNumber()));

        return output;
    }
}
