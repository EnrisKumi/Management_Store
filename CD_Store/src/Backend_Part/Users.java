package Backend_Part;

import java.io.Serializable;

public class Users implements Serializable {
	String username;
	String password;
	int ID;

	Level level;
	String accsess_level;

	public Users() {
		ID=nrofUsers()+1;
		level = new Level();
		level.setName("");
		accsess_level = "";
	}

	public static class Level implements Serializable {
		private int Id;
		private String name;

		public int getId() {
			return Id;
		}

		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name=name;
			
			switch(name) {
			case "Cashier":
				Id=2;
				break;
			case "Menager":
				Id=1;
				break;
			default:
				Id=0;
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getAccsess_level() {
		accsess_level= level.getName();
		return accsess_level;
	}

	public void setAccsess_level(String accsess_level) {
		level.setName(accsess_level);
	}
	
	int nrofUsers() {
		int total_users = 0;
		
		for(Cashiers c: DataBase.getCashiers())
			total_users++;
		for(Menagers m : DataBase.getMenagers())
			total_users = 0;
		return total_users;
		
	}
	
	

}
