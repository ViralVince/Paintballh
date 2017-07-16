package de.viralvince.paintball.kits;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.viralvince.paintball.coins.MySQL;

public class KitAPI {
	 private MySQL mysql;
	 	   
	  public long getKit(String UUID, long KitID) {
	    try {
	      ResultSet rs = this.mysql.getResult("SELECT * FROM Player_Kits WHERE UUID= '" + UUID + "'");
	      if (rs.next()) {
	        return rs.getLong("kit"+ KitID);
	      }
	      rs.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return 0L;
	  }

	  public void setDefaultKit(String UUID, Integer KitID) {
		  String str = "UPDATE Player_Kits SET def =" + Integer.toString(KitID) + " WHERE UUID='" + UUID + "';";
		  this.mysql.update(str);
	  }
	  public long getDefaultKit(String UUID) {
		    try {
		      ResultSet rs = this.mysql.getResult("SELECT * FROM Player_Kits WHERE UUID= '" + UUID + "'");
		      if (rs.next()) {
		        return rs.getLong("def");
		      }
		      rs.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return 0L;
		  }

	  public void addKit(String UUID, Integer KitID) {
	    
	    String str = "UPDATE Player_Kits SET kit" + Integer.toString(KitID) +"=" + "1" + " WHERE UUID='" + UUID + "';";
	   //System.out.println(str);
		  this.mysql.update(str);
	  }
	  
	  public boolean existKitPlayer(String UUID)
	  {
	    try
	    {
	      ResultSet rs = this.mysql.getResult("SELECT * FROM Player_Kits WHERE UUID= '" + UUID + "'");

	      if (rs.next()) {
	        return rs.getString("UUID") != null;
	      }
	      rs.close();
	      return false;
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return false;
	  }
	  
	  public void createKitPlayer(String UUID) {
		  String str = "INSERT INTO Player_Kits(UUID, def , kit1 , kit2 , kit3 , kit4 , kit5 , kit6 , kit7 , kit8 , kit9 , kit10 , kit11 , kit12 , kit13 , kit14 , kit15 , kit16 , kit17 , kit18 , kit19 , kit20 , kit21 , kit22 , kit23 , kit24 , kit25 , kit26 , kit27 ) VALUES ('" + UUID + "', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 )";
		  
		  this.mysql.update(str);
	    
	  }
	  public MySQL getMysql()
	  {
	    return this.mysql;
	  }
	  public void setMysql(MySQL mysql_in)
	  {
	     this.mysql = mysql_in;
	  }
	 
	  
}
