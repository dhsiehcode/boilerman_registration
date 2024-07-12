package com.boilerman.signupV3.DTO;

import com.boilerman.signupV3.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "COLLEGIATE")
public class Collegiate {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "USAT_NUM")
    private String usatNum;
    @Column(name = "PAYMENT_NUM", unique = true)
    private String paymentNum;
    @Column(name = "COLLEGE")
    private String college;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "COLLEGE_EMAIL")
    private String collegeEmail;
    @Column(name = "PHONE_NUM")
    private Long phoneNumber;
    @Column(name = "SHIRT_SIZE")
    private String shirtSize;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "SEX")
    private String sex;

    private @GeneratedValue(strategy = GenerationType.AUTO) @Id Long id;

    protected Collegiate() {}

    public Collegiate(@JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName,
                      @JsonProperty("USATNumber") String usatNum, @JsonProperty("PaymentNumber") String paymentNum,
                      @JsonProperty("College") String college,
                      @JsonProperty("Email") String email, @JsonProperty("CollegeEmail") String collegeEmail,
                      @JsonProperty("PhoneNumber") String phoneNumber, @JsonProperty("ShirtSize") String shirtSize,
                      @JsonProperty("DateOfBirth") String dateOfBirth, @JsonProperty("Sex") String sex) {

        // gets phone number without dashes
        Long phoneNumberWithoutDash = Long.parseLong(phoneNumber.replaceAll("-", ""));

        this.phoneNumber = phoneNumberWithoutDash;
        this.dob = dateOfBirth;

        this.firstName = firstName;
        this.lastName = lastName;
        this.usatNum = usatNum;
        this.paymentNum = paymentNum;
        this.college = college;
        this.email = email;
        this.collegeEmail = collegeEmail;
        this.shirtSize = shirtSize;
        this.sex = sex;

    }

    public Collegiate(@JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName,
                      @JsonProperty("PaymentNumber") String paymentNum,
                      @JsonProperty("College") String college,
                      @JsonProperty("Email") String email, @JsonProperty("CollegeEmail") String collegeEmail,
                      @JsonProperty("PhoneNumber") String phoneNumber, @JsonProperty("ShirtSize") String shirtSize,
                      @JsonProperty("DateOfBirth") String dateOfBirth, @JsonProperty("Sex") String sex) {


        // gets phone number without dashes
        Long phoneNumberWithoutDash = Long.parseLong(phoneNumber.replaceAll("-", ""));

        this.phoneNumber = phoneNumberWithoutDash;
        this.dob = dateOfBirth;

        this.firstName = firstName;
        this.lastName = lastName;
        this.usatNum = "";
        this.paymentNum = paymentNum;
        this.college = college;
        this.email = email;
        this.collegeEmail = collegeEmail;
        this.shirtSize = shirtSize;
        this.sex = sex;

    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCollege() {
        return college;
    }

    public String getCollegeEmail() {
        return collegeEmail;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public String getUsatNum() {
        return usatNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCollegeEmail(String collegeEmail) {
        this.collegeEmail = collegeEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public void setUsatNum(String usatNum) {
        this.usatNum = usatNum;
    }

    /**
     * exports this submission's information in a list to be written to google sheets
     * order:
     * Lastname | first name | usat number | paymet number | college | email | college email | phone number | shirt size | age | sex
     * @return
     */
    public List<String> getValues() {
        List<String> output = new ArrayList<>();

        output.add(getLastName());
        output.add(getFirstName());
        output.add(getUsatNum());
        output.add(getPaymentNum());
        output.add(getCollege());
        output.add(getEmail());
        output.add(getCollegeEmail());
        output.add(getPhoneNumber().toString());
        output.add(getShirtSize());
        output.add(getDob());
        output.add(getSex());

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collegiate that = (Collegiate) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(paymentNum, that.paymentNum) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, paymentNum, email);
    }
}
