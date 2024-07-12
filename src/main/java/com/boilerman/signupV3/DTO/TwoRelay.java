package com.boilerman.signupV3.DTO;


import com.boilerman.signupV3.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Entity
public class TwoRelay extends Relay{


    private String oneFirstName;
    private String oneLastName;
    private String oneUSATNum;
    private String oneEmail;
    private String oneSex;
    private String oneShirtSize;
    private String oneDob;

    private String twoFirstName;
    private String twoLastName;
    private String twoUSATNum;
    private String twoEmail;
    private String twoSex;
    private String twoShirtSize;
    private String twoDob;

    protected TwoRelay() {}

    public TwoRelay(@JsonProperty("PaymentNumber") String paymentNum, @JsonProperty("TeamName") String teamName,
                    @JsonProperty("State") String state, @JsonProperty("City") String city,
                    @JsonProperty("PhoneNumber") String phoneNumber,
                    @JsonProperty("OneFirstName") String oneFirstName, @JsonProperty("TwoFirstName") String twoFirstName,
                    @JsonProperty("OneLastName") String oneLastName, @JsonProperty("TwoLastName") String twoLastName,
                    @JsonProperty("OneUSATNumber") String oneUSATNum, @JsonProperty("TwoUSATNumber") String twoUSATNum,
                    @JsonProperty("OneEmail") String oneEmail, @JsonProperty("TwoEmail") String twoEmail,
                    @JsonProperty("OneDOB") String oneDateOfBirth, @JsonProperty("TwoDOB") String twoDateOfBirth,
                    @JsonProperty("OneSex") String oneSex, @JsonProperty("TwoSex") String twoSex,
                    @JsonProperty("OneShirtSize") String oneShirtSize, @JsonProperty("TwoShirtSize") String twoShirtSize) {
        super(2, paymentNum, teamName, state, city, phoneNumber);

        this.oneFirstName = oneFirstName;
        this.oneLastName = oneLastName;
        this.oneEmail = oneEmail;
        this.oneUSATNum = oneUSATNum;
        this.oneSex = oneSex;
        this.oneShirtSize = oneShirtSize;
        this.oneDob = oneDateOfBirth;

        this.twoFirstName = twoFirstName;
        this.twoLastName = twoLastName;
        this.twoEmail = twoEmail;
        this.twoUSATNum = twoUSATNum;
        this.twoSex = twoSex;
        this.twoShirtSize = twoShirtSize;
        this.twoDob = twoDateOfBirth;

    }


    public String getOneDob() {
        return oneDob;
    }

    public void setOneDob(String oneDob) {
        this.oneDob = oneDob;
    }

    public String getTwoDob() {
        return twoDob;
    }

    public void setTwoDob(String twoDob) {
        this.twoDob = twoDob;
    }

    public String getOneEmail() {
        return oneEmail;
    }

    public void setOneEmail(String oneEmail) {
        this.oneEmail = oneEmail;
    }

    public String getOneFirstName() {
        return oneFirstName;
    }

    public void setOneFirstName(String oneFirstName) {
        this.oneFirstName = oneFirstName;
    }

    public String getOneLastName() {
        return oneLastName;
    }

    public void setOneLastName(String oneLastName) {
        this.oneLastName = oneLastName;
    }

    public String getOneSex() {
        return oneSex;
    }

    public void setOneSex(String oneSex) {
        this.oneSex = oneSex;
    }

    public String getOneShirtSize() {
        return oneShirtSize;
    }

    public void setOneShirtSize(String oneShirtSize) {
        this.oneShirtSize = oneShirtSize;
    }

    public String getOneUSATNum() {
        return oneUSATNum;
    }

    public void setOneUSATNum(String oneUSATNum) {
        this.oneUSATNum = oneUSATNum;
    }


    public String getTwoEmail() {
        return twoEmail;
    }

    public void setTwoEmail(String twoEmail) {
        this.twoEmail = twoEmail;
    }

    public String getTwoFirstName() {
        return twoFirstName;
    }

    public void setTwoFirstName(String twoFirstName) {
        this.twoFirstName = twoFirstName;
    }

    public String getTwoLastName() {
        return twoLastName;
    }

    public void setTwoLastName(String twoLastName) {
        this.twoLastName = twoLastName;
    }

    public String getTwoSex() {
        return twoSex;
    }

    public void setTwoSex(String twoSex) {
        this.twoSex = twoSex;
    }

    public String getTwoShirtSize() {
        return twoShirtSize;
    }

    public void setTwoShirtSize(String twoShirtSize) {
        this.twoShirtSize = twoShirtSize;
    }

    public String getTwoUSATNum() {
        return twoUSATNum;
    }

    public void setTwoUSATNum(String twoUSATNum) {
        this.twoUSATNum = twoUSATNum;
    }

    /**
     * members | payment number | team name | state | city | phone number |
     * oneFirstName | oneLastName | oneUSATNum | oneEmail | oneSex | oneShirtSize | oneAge |
     * twoFirstName | twoLastName | twoUSATNum | twoEmail | twoSex | twoShirtSize | twoAge |
     * @return
     */
    @Override
    public List<String> getValues() {

        List<String> output = super.getValues();

        output.add(getOneFirstName());
        output.add(getOneLastName());
        output.add(getOneUSATNum());
        output.add(getOneEmail());
        output.add(getOneSex());
        output.add(getOneShirtSize());
        output.add(getOneDob());


        output.add(getTwoFirstName());
        output.add(getTwoLastName());
        output.add(getTwoUSATNum());
        output.add(getTwoEmail());
        output.add(getTwoSex());
        output.add(getTwoShirtSize());
        output.add(getTwoDob());

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoRelay twoRelay = (TwoRelay) o;
        return Objects.equals(oneFirstName, twoRelay.oneFirstName) && Objects.equals(oneLastName, twoRelay.oneLastName) && Objects.equals(oneEmail, twoRelay.oneEmail) && Objects.equals(twoFirstName, twoRelay.twoFirstName) && Objects.equals(twoLastName, twoRelay.twoLastName) && Objects.equals(twoEmail, twoRelay.twoEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneFirstName, oneLastName, oneEmail, twoFirstName, twoLastName, twoEmail);
    }
}
