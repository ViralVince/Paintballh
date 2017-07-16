package de.viralvince.paintball.coins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.viralvince.paintball.main.Main;



public class MySQL {
	  public String username = "root";
	  public String password = "ratitixu";
	  public String database = "db";
	  public String host = "5.135.214.32";
	  public String port = "3306";
	  public Connection con;

	  public void connect()
	  {
	    if (!isConnected())
	      try {
	        this.con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":3306/" + this.database + "?autoReconnect=true", this.username, this.password);
	      } catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Es konnte keine MySQL Verbindung hergestellt werden!");
	      }
	  }

	  public void createCoinsTable()
	  {
	    if (isConnected())
	      try {
	        this.con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Player_Coins (UUID VARCHAR(100), Coins long)");
	      } catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Es ist ein Fehler beim Erstellen der Datenbank aufgetreten!");
	      }
	  }
	  
	  public void createKitTable()
	  {
	    if (isConnected())
	      try {
	    	  
	    	  String str = "CREATE TABLE IF NOT EXISTS Player_Kits (UUID VARCHAR(100), def INTEGER, kit1 Bit, kit2 Bit, kit3 Bit, kit4 Bit, kit5 Bit, kit6 Bit, kit7 Bit, kit8 Bit, kit9 Bit, kit10 Bit, kit11 Bit, kit12 Bit, kit13 Bit, kit14 Bit, kit15 Bit, kit16 Bit, kit17 Bit, kit18 Bit, kit19 Bit, kit20 Bit, kit21 Bit, kit22 Bit, kit23 Bit, kit24 Bit, kit25 Bit, kit26 Bit, kit27 Bit)";
	      //  System.out.println(str);
	    	  this.con.createStatement().executeUpdate(str);
	      } catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Es ist ein Fehler beim Erstellen der Datenbank aufgetreten!");
	      }
	  }

	  public void close()
	  {
	    if (isConnected())
	      try {
	        this.con.close();
	        this.con = null;
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Die MySQL Verbundung konnte nicht nicht geschlossen werden");
	      }
	  }

	  public boolean isConnected()
	  {
	    return this.con != null;
	  }

	  public void update(String qry) {
	    if (isConnected())
	      try {
	        this.con.createStatement().executeUpdate(qry);
	      } catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Es konnte kein MySQL Update ausgeführt werden");
	      }
	  }

	  public ResultSet getResult(String qry)
	  {
	    if (isConnected()) {
	      try {
	        return this.con.createStatement().executeQuery(qry);
	      } catch (SQLException e) {
	        e.printStackTrace();
	        Main.getInstance().log("Fehler getResult");
	      }
	    }
	    return null;
	  }
}
