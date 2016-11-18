package common;

public class Administrator {
	private String _name;
	private String _password;
	
	public Administrator(String name, String password) {
		_name = name;
		_password = password;
	}
	
	public String getPassword() {
		return _password;
	}

	public String getUsername() {
		return _name;
	}

}
