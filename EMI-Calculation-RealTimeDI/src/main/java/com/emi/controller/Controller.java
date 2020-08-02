package com.emi.controller;

import com.emi.dto.EmiDTO;
import com.emi.service.EmiService;
import com.emi.service.EmiServiceImpl;
import com.emi.vo.EmiVO;

public class Controller {
	
		private EmiService service;
		
		//Constructor Injection
		public Controller(EmiServiceImpl service) {
			this.service = service;
		}
		
		public  String  processController(EmiVO  vo)throws Exception{
			EmiDTO dto=null;
			String result=null;
			
			//convert  VO class object to DTO class object
			dto=new EmiDTO();
			dto.setCname(vo.getCname());
			dto.setCadd(vo.getCadd());
			dto.setPamt(Float.parseFloat(vo.getPamt()));
			dto.setRoi(Float.parseFloat(vo.getRoi()));
			dto.setYears(Float.parseFloat(vo.getYears()));
			
			//using service methods
			result=service.emiCalculation(dto);
			return result;
			
		}
		public String searchController(EmiVO vo) throws Exception {
			EmiDTO dto=null;
			String result=null;
			
			dto=new EmiDTO();
			dto.setCid(vo.getCid());
			result=((EmiServiceImpl) service).displayDetails(dto);
			return result;
		}
}
