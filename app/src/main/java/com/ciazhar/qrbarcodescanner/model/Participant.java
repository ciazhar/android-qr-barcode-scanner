package com.ciazhar.qrbarcodescanner.model;

/**
 * Created by ciazhar on 19/12/17.
 */

public class Participant {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private JobStatus jobStatus;
    private DVDBit dvdBitBit;
    private Boolean paymentStatus;
    private Boolean attendanceStatus;

    public Participant(String id, String name, String phoneNumber, String email, JobStatus jobStatus, DVDBit dvdBitBit, Boolean paymentStatus, Boolean attendanceStatus) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.jobStatus = jobStatus;
        this.dvdBitBit = dvdBitBit;
        this.paymentStatus = paymentStatus;
        this.attendanceStatus = attendanceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public DVDBit getDvdBitBit() {
        return dvdBitBit;
    }

    public void setDvdBitBit(DVDBit dvdBitBit) {
        this.dvdBitBit = dvdBitBit;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", jobStatus=" + jobStatus +
                ", dvdBitBit=" + dvdBitBit +
                ", paymentStatus=" + paymentStatus +
                ", attendanceStatus=" + attendanceStatus +
                '}';
    }
}
