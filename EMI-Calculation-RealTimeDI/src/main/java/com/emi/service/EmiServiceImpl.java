package com.emi.service;

import java.sql.ResultSet;
import java.util.Random;

import com.emi.bo.EmiBO;
import com.emi.dao.EmiDAO;
import com.emi.dto.EmiDTO;

public class EmiServiceImpl implements EmiService {

	private EmiDAO dao;
	
	public EmiServiceImpl(EmiDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public String emiCalculation(EmiDTO dto) throws Exception {
		
		
		float emi=0.0f,rate=0.0f,time=0.0f,result=0.0f,si=0.0f;
		long id=0l;
		int count=0;
		String out=null,cid=null;
		EmiBO bo=null;
		
		si=Math.round(dto.getPamt()*dto.getRoi()*dto.getYears()/100);
		rate=dto.getRoi()/ (12 * 100); // one month interest 
		time = dto.getYears() * 12; // one month period 
		emi = Math.round((dto.getPamt() * rate * (float)Math.pow(1 + rate, time)) / (float)(Math.pow(1 + rate, time) - 1)); 
		result=Math.round(emi*time);
		
		id=(long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		cid="CID"+id;
		
		bo=new EmiBO();
		bo.setCID(cid);
		bo.setCNAME(dto.getCname().toUpperCase());
		bo.setCADD(dto.getCadd().toUpperCase());
		bo.setPAMT(dto.getPamt());
		bo.setROI(dto.getRoi());
		bo.setYEARS(dto.getYears());
		bo.setEMI(emi);
		bo.setRESULT(result);
		
		count=dao.insert(bo);
		if(count==0)
			return "\nData Insertion Problem "+"\nMonthly EMI	\t: "+emi+"\nTotal Payable Amount\t: "+result;
		else return "\nData Inserted Succefully "+"\nCID	\t\t: "+cid+"\nSimple Interest\t\t: "+si+"\nMonthly EMI	\t: "+emi+"\nTotal Payable Amount\t: "+result;
	}	
	public String displayDetails(EmiDTO dto)throws Exception {
	
		EmiBO bo=null;
		float float1=0,float2=0,float3=0,float4=0,float5=0;
		String cid=dto.getCid(),param1=null,param2=null;
		bo=new EmiBO();
		bo.setCID(cid);
		ResultSet rset=dao.select(bo);
		
		while(rset.next()) {
			
			param1=rset.getString(1).toUpperCase();
			param2=rset.getString(2).toUpperCase();
			float1=rset.getFloat(3);
			float2=rset.getFloat(4);
			float3=rset.getFloat(5);
			float4=rset.getFloat(6);
			float5=rset.getFloat(7);
			
		}
		return "\nName	\t\t: "+param1+
				"\nAddress	\t\t: "+param2+
				"\nPrinciple Amount\t: "+float1+
				"\nRate Of Interest\t: "+float2+
				"\nYears	\t\t: "+float3+
				"\nEMI	\t\t: "+float4+
				"\nGross Total	\t: "+float5;
	}
}
