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
@Table(name = "AGE_GROUP")
public class AgeGroup {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "USAT_NUM")
    private String usatNum;
    @Column(name = "PAYMENT_NUM", unique = true)
    private String paymentNum;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "PHONE_NUM")
    private Long phoneNumber;
    @Column(name = "SHIRT_SIZE")
    private String shirtSize;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "SEX")
    private String sex;

    private  @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    protected AgeGroup() {}

    public AgeGroup(@JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName,
                    @JsonProperty("USATNumber") String usatNum, @JsonProperty("PaymentNumber") String paymentNum,
                    @JsonProperty("Email") String email, @JsonProperty("City") String city,
                    @JsonProperty("State") String state, @JsonProperty("PhoneNumber") String phoneNumber,
                    @JsonProperty("ShirtSize") String shirtSize, @JsonProperty("DateOfBirth") String dateOfBirth,
                    @JsonProperty("Sex") String sex) {



        // gets phone number without dashes
        Long phoneNumberWithoutDash = Long.parseLong(phoneNumber.replaceAll("-", ""));

        this.phoneNumber = phoneNumberWithoutDash;
        this.dob = dateOfBirth;

        this.firstName = firstName;
        this.lastName = lastName;
        this.usatNum = usatNum;
        this.paymentNum = paymentNum;
        this.email = email;
        this.shirtSize = shirtSize;
        this.sex = sex;
        this.city = city;
        this.state = state;

    }

    public AgeGroup(String firstName, String lastName, String paymentNum,
                      String city, String state, String email, String phoneNumber,
                      String shirtSize, String dateOfBirth, String sex) {


        // gets phone number without dashes
        Long phoneNumberWithoutDash = Long.parseLong(phoneNumber.replaceAll("-", ""));

        this.phoneNumber = phoneNumberWithoutDash;
        this.dob = dateOfBirth;

        this.firstName = firstName;
        this.lastName = lastName;
        this.usatNum = "";
        this.paymentNum = paymentNum;
        this.email = email;
        this.shirtSize = shirtSize;
        this.sex = sex;

    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsatNum() {
        return usatNum;
    }

    public void setUsatNum(String usatNum) {
        this.usatNum = usatNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * exports this submission's information in a list to be written to google sheets
     * order:
     * Lastname | first name | usat number | paymet number | email | city | state | phone number | shirt_size | age | sex
     * @return
     */
    public List<String> getValues() {

        List<String> output = new ArrayList<>();

        output.add(getLastName());
        output.add(getFirstName());
        output.add(getUsatNum());
        output.add(getPaymentNum());
        output.add(getEmail());
        output.add(getCity());
        output.add(getState());
        output.add(Long.toString(getPhoneNumber()));
        output.add(getShirtSize());
        output.add(dob);
        output.add(getSex());

        return output;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeGroup ageGroup = (AgeGroup) o;
        return Objects.equals(firstName, ageGroup.firstName) && Objects.equals(lastName, ageGroup.lastName) && Objects.equals(paymentNum, ageGroup.paymentNum) && Objects.equals(email, ageGroup.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, paymentNum, email);
    }
}
