package mz.co.noobs.framework.ssh;

import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHCommandExecutor {

	private Session session;
	private String password;
	private Channel channel;

	public SSHCommandExecutor(final String username, final String password, final String host, final int port)
			throws JSchException {
		JSch jSch = new JSch();
		session = jSch.getSession(username, host, port);
		this.password = password;
	}
	
	 public void init() throws JSchException {
	        Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");

	        this.session.setPassword(this.password);
	        this.session.setConfig(config);
	        this.session.connect();

	    }
	
    private void openChannel() throws JSchException {
        this.channel = this.session.openChannel("exec");
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
	
    public void closeSession() {
        this.channel.disconnect();
        this.session.disconnect();
    }

}
