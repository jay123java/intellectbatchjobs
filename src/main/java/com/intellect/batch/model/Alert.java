package com.intellect.batch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="ALERTS")
public class Alert {

	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private String alertId;

	
	public Integer getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Integer alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	@Column(name="ALERT_NUMBER")
	private String alertNumber;

	@Column(name="ALERT_TYPE_ID")
	private Integer alertTypeId;

	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	

	@Column(name="COMPANY_CODE")
	private String companyCode;

	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getAlertNumber() {
		return alertNumber;
	}
	public void setAlertNumber(String alertNumber) {
		this.alertNumber = alertNumber;
	}
	@Column(name="CONSIGNEE_CODE")
	private String consigneeCode;
	@Column(name="TRANSPORT_MODE")
	private String transportMode;
	@Column(name="ORIGIN_COUNTRY")
	private String originCountry;
	@Column(name="EXPORT_COUNTRY")
	private String exportCountry;
	@Column(name="SHIPPING_TERM")
	private String shippingTerm;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	private Date modifiedTime;
	
	@Transient
	private String email;
	
	@Transient
	private String fullName;
	
	
	//@Id
	//@Column(name="ID")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	/*private String alertId;
	
	//@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	//@Column(name="CONSIGNEE_CODE")
	private String consigneeCode;
	//@Column(name="TRANSPORT_MODE")
	private String transportMode;
	//@Column(name="ORIGIN_COUNTRY")
	private String originCountry;
	//@Column(name="EXPORT_COUNTRY")
	private String exportCountry;
	//@Column(name="SHIPPING_TERM")
	private String shippingTerm;
	*/
	
	public boolean isActive() {
		return isActive;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getConsigneeCode() {
		return consigneeCode;
	}
	public void setConsigneeCode(String consigneeCode) {
		this.consigneeCode = consigneeCode;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getExportCountry() {
		return exportCountry;
	}
	public void setExportCountry(String exportCountry) {
		this.exportCountry = exportCountry;
	}
	public String getShippingTerm() {
		return shippingTerm;
	}
	public void setShippingTerm(String shippingTerm) {
		this.shippingTerm = shippingTerm;
	}
	
	
	


}
