package br.com.caelum.aceitacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {
	
	private WebDriver navegador;
	
	public UsuariosPage(WebDriver navegador) {
		this.navegador = navegador;
	}
	
	public UsuariosPage visita() {
		navegador.navigate().to("http://localhost:8080/usuarios");
		return this;
	}

	public UsuariosPage novo() {
		navegador.findElement(By.linkText("Novo Usuário")).click();
		return this;
	}
	
	public UsuariosPage popularFormulario(String nome, String email) {
		navegador.findElement(By.name("usuario.nome")).sendKeys(nome);
		navegador.findElement(By.name("usuario.email")).sendKeys(email);
		return this;
	}
	
	public UsuariosPage clicaEmSalvar() {
		navegador.findElement(By.id("btnSalvar")).click();
		return this;
	}
	
	public boolean verificaSeUsuarioFoiCadastrado(String nome, String email) {
		boolean temNome = navegador.getPageSource().contains(nome);
		boolean temEmail = navegador.getPageSource().contains(email);
		return temNome && temEmail;
	}
	
	public boolean verificaSeUsuarioNaoFoiCadastradoSemMail() {
		boolean emailObrigatorio = navegador.getPageSource().contains("E-mail obrigatorio!");
		return emailObrigatorio;
	}
	
	public boolean verificaSeUsuarioNaoFoiCadastradoSemNome() {
		boolean nomeObrigatorio = navegador.getPageSource().contains("Nome obrigatorio!");
		return nomeObrigatorio;
	}
	
	public boolean verificaSeUsuarioNaoFoiCadastradoSemNomeEMail() {
		boolean nomeObrigatorio = navegador.getPageSource().contains("Nome obrigatorio!");
		boolean emailObrigatorio = navegador.getPageSource().contains("E-mail obrigatorio!");

		return nomeObrigatorio && emailObrigatorio;
	}

	public UsuariosPage clicaBotaoExcluir() {
		navegador.findElements(By.tagName("button")).get(0).click();
		return this;
	}

	public UsuariosPage confirmaExclusao() {
		navegador.switchTo().alert().accept();
		return this;
	}

	public boolean verificaSeUsuarioFoiExcluido(String nome, String email) {
		boolean temNome = navegador.getPageSource().contains(nome);
		boolean temEmail = navegador.getPageSource().contains(email);
		return !temNome && !temEmail;
	}
}
