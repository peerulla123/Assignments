package com.emi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.emi.bo.EmiBO;

public class EmiDAOImpl implements EmiDAO {

	private  static final  String   EMI_INSERT_QUERY="INSERT INTO EMI_CALCULATION VALUES(?,?,?,?,?,?,?,?)";
	private static final String EMI_SELECT_QUERY="SELECT CNAME,CADD,PAMT,ROI,YEARS,EMI,RESULT FROM EMI_CALCULATION WHERE CID=?";

	private DataSource ds;

	public EmiDAOImpl(DataSource ds) {
		this.ds=ds;
	}

	@Override
	public int insert(EmiBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//Get Pooled JDBC Connection
		con=ds.getConnection();
		//Create Prepared Statement
		ps=con.prepareStatement(EMI_INSERT_QUERY);
		//Set Values to Query Params
		ps.setString(1,bo.getCID());
		ps.setString(2, bo.getCNAME());
		ps.setString(3, bo.getCADD());
		ps.setFloat(4, bo.getPAMT());
		ps.setFloat(5, bo.getROI());
		ps.setFloat(6, bo.getYEARS());
		ps.setFloat(7, bo.getEMI());
		ps.setFloat(8, bo.getRESULT());
		//Execute the Query
		count=ps.executeUpdate();
		//Close jdbc objs
		ps.close();
		con.close();

		return count;
	}
	public ResultSet select(EmiBO bo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		con=ds.getConnection();
		ps=con.prepareStatement(EMI_SELECT_QUERY);
		ps.setString(1,bo.getCID());
		rs=ps.executeQuery();		

		return rs;

	}

}
