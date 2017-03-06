package mz.co.noobs.model;

public class Acesso {

	private String username;
	private String password;
	private String host;
	private Integer port;

	public Acesso() {

	}

	public Acesso(String username, String password, String host, Integer port) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port == null ? 0 : port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
