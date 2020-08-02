package com.emi.dto;

import java.io.Serializable;

public class EmiDTO implements Serializable{
	
	private String cid;
	private String cname;
	private String cadd;
	private float pamt;
	private float roi;
	private float years;
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCadd() {
		return cadd;
	}
	public void setCadd(String cadd) {
		this.cadd = cadd;
	}
	public float getPamt() {
		return pamt;
	}
	public void setPamt(float pamt) {
		this.pamt = pamt;
	}
	public float getRoi() {
		return roi;
	}
	public void setRoi(float roi) {
		this.roi = roi;
	}
	public float getYears() {
		return years;
	}
	public void setYears(float years) {
		this.years = years;
	}
	
	

}
