package br.com.caelum.aceitacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LeilaoPage {
	
	private WebDriver navegador;
	
	public LeilaoPage(WebDriver navegador) {
		this.navegador = navegador;		
	}
	
	public LeilaoPage vaiParaTelaDeLeilao() {
		navegador.navigate().to("http://localhost:8080/leiloes");
		return this;
	}
	
	public LeilaoPage clicaEmNovoLeilao() {
		navegador.findElement(By.linkText("Novo Leilão")).click();
		return this;
	}
	
	public LeilaoPage preencheFormulario(String nome, Double valor, String dono, boolean usado) {
		navegador.findElement(By.name("leilao.nome")).sendKeys(nome);
		navegador.findElement(By.name("leilao.valorInicial")).sendKeys(valor.toString());
		WebElement comboBoxDono = navegador.findElement(By.name("leilao.usuario.id"));
		Select select = new Select(comboBoxDono);
		select.selectByVisibleText(dono);
		if (usado)
			navegador.findElement(By.name("leilao.usado")).click();
		return this;
	}
	
	public LeilaoPage enviaFormulario() {
		navegador.findElement(By.tagName("button")).click();
		return this;
	}
	
	public boolean validaSeLeilaoFoiCriado(String nome, Double valor, String dono, boolean usado) {
		
		String htmlDaPagina = navegador.getPageSource();
		
		boolean temNome = htmlDaPagina.contains(nome);
		boolean temValor = htmlDaPagina.contains(valor.toString());
		boolean temDono = htmlDaPagina.contains(dono);
		boolean isUsado = htmlDaPagina.contains(usado?"Sim":"Não");
		
		return temNome && temValor && temDono && isUsado;
		
	}

	public boolean verificaSeExibiuErroDeNomeObrigatorio() {
		boolean contemMensagemDeNomeObrigatorio = navegador.getPageSource().contains("Nome obrigatorio!");
		return contemMensagemDeNomeObrigatorio;
	}

	public boolean verificaSeExibiuErroValorMaiorQue0() {
		boolean contemMensagemDeValorMaiorQue0 = navegador.getPageSource().contains("Valor inicial deve ser maior que zero!");
		return contemMensagemDeValorMaiorQue0;
	}

	public boolean verificaSeExebiuDuasMensagensDeErro() {
		boolean contemMensagemDeNomeObrigatorio = navegador.getPageSource().contains("Nome obrigatorio!");
		boolean contemMensagemDeValorMaiorQue0 = navegador.getPageSource().contains("Valor inicial deve ser maior que zero!");
		return contemMensagemDeNomeObrigatorio && contemMensagemDeValorMaiorQue0;
	}
}
