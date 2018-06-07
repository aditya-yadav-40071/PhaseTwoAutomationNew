package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


import  java.sql.Connection;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;



final class DBConnect extends WebPage {
	
	
	def static returnQuery(String query1){
		
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://52.66.9.34:3306/KPLMS_CLOUD";

		//Database Username
		String username = "podworkss";

		//Database Password
		String password = "podworkss";

		//Query to Execute
		String query = query1;
		println "query "+query
		
		//Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver"); 
		println "JDBC driver loaded Successfully"
        
		//Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl,username,password);
		println "Connected Successfully"
		
		//Create Statement Object
		Statement stmt = con.createStatement();
		
		ResultSet rs= null;
		def list = []
		// Execute the SQL Query. Store results in ResultSet
		if(query.contains("select")){
			rs= stmt.executeQuery(query);
			println "result set : "+rs
			int i = 1;
			//println "Size : "+rs.size()
			while (rs.next()){
					def test = rs.getString(i)
					println "Test : "+test
					list.add(test)
					i++;
			}
			println "Result : "+list
		}else{
			stmt.executeUpdate(query);
		}
		// closing DB Connection
		con.close();
		return list;
	}
}
