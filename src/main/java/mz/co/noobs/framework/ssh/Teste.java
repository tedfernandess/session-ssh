package mz.co.noobs.framework.ssh;

import com.jcraft.jsch.JSchException;

import mz.co.noobs.model.Acesso;
import mz.co.noobs.service.ConnectService;

public class Teste {

	public static void main(String[] args) throws JSchException {

		Acesso acesso = new Acesso("sysadmin", "dFx16*.0", "192.168.0.60", 22);
		
		ConnectService connectService = new ConnectService(acesso);
		connectService.getSession().setConfig("StrictHostKeyChecking", "no");
		connectService.getSession().setPassword("dFx16*.0");
		connectService.getSession().connect();
		connectService.execute("ls");
		connectService.closeSession();
	}

}
