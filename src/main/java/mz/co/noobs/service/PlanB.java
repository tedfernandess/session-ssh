package mz.co.noobs.service;

import com.jcraft.jsch.JSchException;

import mz.co.noobs.exceptions.SSHConnectionException;
import mz.co.noobs.model.Acesso;

public class PlanB {

	public PlanB(Acesso acesso) throws SSHConnectionException {
		connect(acesso);
	}

	public static void connect(Acesso acesso) throws SSHConnectionException {
		ConnectService connectService;

		try {

			connectService = new ConnectService(acesso);
			connectService.getSession().setConfig("StrictHostKeyChecking", "no");
			connectService.getSession().setPassword(acesso.getPassword());
			connectService.getSession().connect();
			connectService.execute("ls");
			connectService.closeSession();

		}catch (JSchException f) {
			throw new SSHConnectionException("haha");
		}
		
	}

}
