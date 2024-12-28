package com.shahathir.membership_crud3;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @JsonProperty("firstN")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("lastN")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty("email")
    @Column(name = "email_address")
    private String emailAddress;

    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    @JsonProperty("phone")
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    @Column(name = "location_address")
    private String locationAddress;

    @JsonProperty("dob")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("age")
    @Column(name = "age")
    private int age;

    @JsonProperty("type")
    @Column(name = "membership_type")
    private String membershipType;

    @JsonProperty("duration")
    @Column(name = "membership_duration")
    private int membershipDuration;

    @JsonProperty("joinDate")
    @Column(name = "join_date")
    private LocalDate joinDate;

    @JsonProperty("expirationDate")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    @JsonProperty("profilePictureUrl")
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public String getEmail() {
        return emailAddress;
    }

    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public int getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(int membershipDuration) {
        this.membershipDuration = membershipDuration;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + ", locationAddress="
                + locationAddress + "]";
    }

}
