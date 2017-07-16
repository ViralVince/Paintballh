package de.viralvince.paintball.coins;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {
	 private MySQL mysql;

	  public boolean existCoinsPlayer(String UUID)
	  {
	    try
	    {
	      ResultSet rs = this.mysql.getResult("SELECT * FROM Player_Coins WHERE UUID= '" + UUID + "'");

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
	  
	  public void createCoinsPlayer(String UUID) {
		  String str = "INSERT INTO Player_Coins(UUID, Coins) VALUES ('" + UUID + "', 0)";
		  
		  this.mysql.update(str);
	    
	  }

	  public long getCoins(String UUID) {
	    try {
	      ResultSet rs = this.mysql.getResult("SELECT * FROM Player_Coins WHERE UUID= '" + UUID + "'");
	      if (rs.next()) {
	        return rs.getLong("Coins");
	      }
	      rs.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return 0L;
	  }

	  public void setCoins(String UUID, long Coins)
	  {
	    this.mysql.update("UPDATE Player_Coins SET Coins='" + Coins + "' WHERE UUID='" + UUID + "';");
	  }

	  public void addCoins(String UUID, long Coins) {
	    
	    String str = "UPDATE Player_Coins SET Coins='" + Long.valueOf(getCoins(UUID) + Coins) + "' WHERE UUID='" + UUID + "';";
	//    System.out.println(str);
		  this.mysql.update(str);
	  }

	  public void removeCoins(String UUID, long Coins) {
	    this.mysql.update("UPDATE Player_Coins SET Coins='" + Long.valueOf(getCoins(UUID) - Coins) + "' WHERE UUID='" + UUID + "';");
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
