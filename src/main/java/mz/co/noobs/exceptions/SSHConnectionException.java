package mz.co.noobs.exceptions;

import com.jcraft.jsch.JSchException;

import mz.co.noobs.framework.ssh.LoadProperties;

public class SSHConnectionException extends JSchException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5678402460253613283L;
	
	public SSHConnectionException(Throwable cause){
		super(msg(cause));
	}

	public SSHConnectionException(String message, Throwable cause){
		super(message, cause);
	}
	
	public SSHConnectionException(String message){
		super(message);
	}
	
	private static String msg(Throwable cause){
		
		String key = cause == null ? cause.getMessage().replace(" ", ".")
				: cause.getClass().getSimpleName();
		
		return LoadProperties.getValue(key);
		
	}
	
}
