package com.ciazhar.qrbarcodescanner.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class AttendanceData{
  @SerializedName("dvdBitBit")
  @Expose
  private String dvdBitBit;
  @SerializedName("jobStatus")
  @Expose
  private String jobStatus;
  @SerializedName("phoneNumber")
  @Expose
  private Long phoneNumber;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("paymentStatus")
  @Expose
  private Boolean paymentStatus;
  @SerializedName("attendanceStatus")
  @Expose
  private Boolean attendanceStatus;
  public void setDvdBitBit(String dvdBitBit){
   this.dvdBitBit=dvdBitBit;
  }
  public String getDvdBitBit(){
   return dvdBitBit;
  }
  public void setJobStatus(String jobStatus){
   this.jobStatus=jobStatus;
  }
  public String getJobStatus(){
   return jobStatus;
  }
  public void setPhoneNumber(Long phoneNumber){
   this.phoneNumber=phoneNumber;
  }
  public Long getPhoneNumber(){
   return phoneNumber;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setId(String id){
   this.id=id;
  }
  public String getId(){
   return id;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setPaymentStatus(Boolean paymentStatus){
   this.paymentStatus=paymentStatus;
  }
  public Boolean getPaymentStatus(){
   return paymentStatus;
  }
  public void setAttendanceStatus(Boolean attendanceStatus){
   this.attendanceStatus=attendanceStatus;
  }
  public Boolean getAttendanceStatus(){
   return attendanceStatus;
  }
}