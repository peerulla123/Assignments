package com.emi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.emi.bo.EmiBO;

public interface EmiDAO {
	
	public   int insert(EmiBO bo)throws Exception;
	public ResultSet select(EmiBO bo) throws SQLException;
}
