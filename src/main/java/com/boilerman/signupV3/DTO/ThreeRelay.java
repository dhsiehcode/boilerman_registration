package com.boilerman.signupV3.DTO;


import com.boilerman.signupV3.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Entity
public class ThreeRelay extends Relay {


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

    private String threeFirstName;
    private String threeLastName;
    private String threeUSATNum;
    private String threeEmail;
    private String threeSex;
    private String threeShirtSize;
    private String threeDob;

    protected ThreeRelay() {}

    public ThreeRelay(@JsonProperty("PaymentNumber") String paymentNum, @JsonProperty("TeamName") String teamName,
                    @JsonProperty("State") String state, @JsonProperty("City") String city,
                    @JsonProperty("PhoneNumber") String phoneNumber,
                    @JsonProperty("OneFirstName") String oneFirstName, @JsonProperty("TwoFirstName") String twoFirstName,
                    @JsonProperty("ThreeFirstName") String threeFirstName,
                    @JsonProperty("OneLastName") String oneLastName, @JsonProperty("TwoLastName") String twoLastName,
                    @JsonProperty("ThreeLastName") String threeLastName,
                    @JsonProperty("OneUSATNumber") String oneUSATNum, @JsonProperty("TwoUSATNumber") String twoUSATNum,
                    @JsonProperty("ThreeUSATNumber") String threeUSATNum,
                    @JsonProperty("OneEmail") String oneEmail, @JsonProperty("TwoEmail") String twoEmail,
                    @JsonProperty("ThreeEmail") String threeEmail,
                    @JsonProperty("OneDOB") String oneDateOfBirth, @JsonProperty("TwoDOB") String twoDateOfBirth,
                    @JsonProperty("ThreeDOB") String threeDateOfBirth,
                    @JsonProperty("OneSex") String oneSex, @JsonProperty("TwoSex") String twoSex,
                    @JsonProperty("ThreeSex") String threeSex,
                    @JsonProperty("OneShirtSize") String oneShirtSize, @JsonProperty("TwoShirtSize") String twoShirtSize,
                    @JsonProperty("ThreeShirtSize") String threeShirtSize) {
        super(3, paymentNum, teamName, state, city, phoneNumber);

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

        this.threeFirstName = threeFirstName;
        this.threeLastName = threeLastName;
        this.threeEmail = threeEmail;
        this.threeUSATNum = threeUSATNum;
        this.threeSex = threeSex;
        this.threeShirtSize = threeShirtSize;
        this.threeDob = threeDateOfBirth;

    }

    public String getOneDob() {
        return oneDob;
    }

    public void setOneDob(String oneDob) {
        this.oneDob = oneDob;
    }

    public String getThreeDob() {
        return threeDob;
    }

    public void setThreeDob(String threeDob) {
        this.threeDob = threeDob;
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


    public String getThreeEmail() {
        return threeEmail;
    }

    public void setThreeEmail(String threeEmail) {
        this.threeEmail = threeEmail;
    }

    public String getThreeFirstName() {
        return threeFirstName;
    }

    public void setThreeFirstName(String threeFirstName) {
        this.threeFirstName = threeFirstName;
    }

    public String getThreeLastName() {
        return threeLastName;
    }

    public void setThreeLastName(String threeLastName) {
        this.threeLastName = threeLastName;
    }

    public String getThreeSex() {
        return threeSex;
    }

    public void setThreeSex(String threeSex) {
        this.threeSex = threeSex;
    }

    public String getThreeShirtSize() {
        return threeShirtSize;
    }

    public void setThreeShirtSize(String threeShirtSize) {
        this.threeShirtSize = threeShirtSize;
    }

    public String getThreeUSATNum() {
        return threeUSATNum;
    }

    public void setThreeUSATNum(String threeUSATNum) {
        this.threeUSATNum = threeUSATNum;
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

        output.add(getThreeFirstName());
        output.add(getThreeLastName());
        output.add(getThreeUSATNum());
        output.add(getThreeEmail());
        output.add(getThreeSex());
        output.add(getThreeShirtSize());
        output.add(getThreeDob());

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeRelay that = (ThreeRelay) o;
        return Objects.equals(oneFirstName, that.oneFirstName) && Objects.equals(oneLastName, that.oneLastName) && Objects.equals(oneEmail, that.oneEmail) && Objects.equals(twoFirstName, that.twoFirstName) && Objects.equals(twoLastName, that.twoLastName) && Objects.equals(twoEmail, that.twoEmail) && Objects.equals(threeFirstName, that.threeFirstName) && Objects.equals(threeLastName, that.threeLastName) && Objects.equals(threeEmail, that.threeEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneFirstName, oneLastName, oneEmail, twoFirstName, twoLastName, twoEmail, threeFirstName, threeLastName, threeEmail);
    }

    @Override
    /**
     * for testing
     */
    public String toString() {
        return "ThreeRelay{" +
                "oneAge=" + oneDob +
                ", oneFirstName='" + oneFirstName + '\'' +
                ", oneLastName='" + oneLastName + '\'' +
                ", oneUSATNum='" + oneUSATNum + '\'' +
                ", oneEmail='" + oneEmail + '\'' +
                ", oneSex='" + oneSex + '\'' +
                ", oneShirtSize='" + oneShirtSize + '\'' +
                ", twoFirstName='" + twoFirstName + '\'' +
                ", twoLastName='" + twoLastName + '\'' +
                ", twoUSATNum='" + twoUSATNum + '\'' +
                ", twoEmail='" + twoEmail + '\'' +
                ", twoSex='" + twoSex + '\'' +
                ", twoShirtSize='" + twoShirtSize + '\'' +
                ", twoAge=" + twoDob +
                ", threeFirstName='" + threeFirstName + '\'' +
                ", threeLastName='" + threeLastName + '\'' +
                ", threeUSATNum='" + threeUSATNum + '\'' +
                ", threeEmail='" + threeEmail + '\'' +
                ", threeSex='" + threeSex + '\'' +
                ", threeShirtSize='" + threeShirtSize + '\'' +
                ", threeAge=" + threeDob +
                '}';
    }
}
