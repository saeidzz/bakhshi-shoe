package com.bakhshi.Main.ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import org.h2.tools.Script;

public class DataBaseBackupAndRestore {

	public static void main(String[] args) throws SQLException{
	}
	 public static  void restore(String f)throws SQLException, IOException {
		 Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/bakhshi_database1;DB_CLOSE_DELAY=-1;LOG=0", "sa","");
		 Statement stat = conn.createStatement();
		 stat.execute("DROP ALL OBJECTS");
		 stat.close();
		 conn.close();
		 
		String s = f.replace("\\", "/");
		 conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/bakhshi_database1;DB_CLOSE_DELAY=-1;INIT=runscript from'"+s+"';LOG=0", "sa","");
		 conn.close();
	}

      public static void backUp(String path) throws SQLException, URISyntaxException{
  		
  		String[] bkp = {"-url","jdbc:h2:tcp://localhost/~/bakhshi_database1", "-user", "sa", "-password","", "-script",path};
  	     Script.main(bkp);
      }

}
