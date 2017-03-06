package mz.co.noobs.service;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import mz.co.noobs.exceptions.SSHConnectionException;
import mz.co.noobs.framework.ssh.LoadProperties;
import mz.co.noobs.model.Acesso;

public class ConnectService {

	private Session session;
	private String password;
	private Channel channel;

	public ConnectService(Acesso acesso) throws SSHConnectionException {
		JSch jSch = new JSch();
		try {
			session = jSch.getSession(acesso.getUsername(), acesso.getHost(), acesso.getPort());
		} catch (JSchException e) {
			String key = getMessageResult(e);
			throw new SSHConnectionException(key);
		}
		this.password = acesso.getPassword();
	}

	public void init() throws SSHConnectionException {
		this.session.setPassword(this.password);
		this.session.setConfig("StrictHostKeyChecking", "no");
		try {
			this.session.connect();
		} catch (JSchException e) {
			String key = getMessageResult(e);
			throw new SSHConnectionException(key);
		}
	}

	private void openChannel() throws SSHConnectionException {
		try {
			this.channel = this.session.openChannel("exec");
		} catch (JSchException e) {
			String key = getMessageResult(e);
			throw new SSHConnectionException(key);
		}
		this.channel.setInputStream(null);
		((ChannelExec) this.channel).setErrStream(System.err);
	}

	public int execute(final String command) {
		int exitStatusCode = 0;

		try {
			openChannel();
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		((ChannelExec) this.channel).setCommand(command);

		try {
			InputStream in = this.channel.getInputStream();
			this.channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (this.channel.isClosed()) {
					exitStatusCode = this.channel.getExitStatus();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					// TODO Tratar da exception
					ee.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO Tratar da exception
			e.printStackTrace();
		}

		return exitStatusCode;
	}

	private String getMessageResult(JSchException e) {
		String keyMessage = e.getCause() == null ? e.getMessage().replace(" ", ".")
				: e.getCause().getClass().getSimpleName();
		return LoadProperties.getValue(keyMessage);
	}

	public void closeSession() {
		this.channel.disconnect();
		this.session.disconnect();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
