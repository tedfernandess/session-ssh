package mz.co.noobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String ConectarServidor(Acesso acesso, Model model) {

		ConnectService connectService;
		String resultado;

		try {
			connectService = new ConnectService(acesso);
			connectService.init();
			int execute = connectService.execute("ls");
			resultado = LoadProperties.getValue(String.valueOf(execute));
			model.addAttribute("resultado", resultado);
			connectService.closeSession();
			return "conectado";
		} catch (SSHConnectionException e) {
			resultado = e.getMessage();
			model.addAttribute("resultado", resultado);
			return "naoConectado";
		}
	}
}
