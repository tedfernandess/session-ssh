package mz.co.noobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mz.co.noobs.exceptions.SSHConnectionException;
import mz.co.noobs.framework.ssh.LoadProperties;
import mz.co.noobs.model.Acesso;
import mz.co.noobs.service.ConnectService;

@Controller
public class SessionController {

	@RequestMapping("/conectar")
	public String Conectar() {
		return "ConexaoSSH";
	}

	@RequestMapping("/conectarServidor")
	public String ConectarServidor(Acesso acesso) {

		ConnectService connectService;

		try {
			connectService = new ConnectService(acesso);
			connectService.init();
			int execute = connectService.execute("ls");
			String resultado = LoadProperties.getValue(String.valueOf(execute));
			System.out.println(resultado);
			connectService.closeSession();
		} catch (SSHConnectionException e) {
			System.out.println(e.getMessage() + " "+e.getCause());
		}
		return "conectado";
	}
	
}
