package br.com.caelum.aceitacao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuarioTest {

	private WebDriver navegador;
	private UsuariosPage usuariosPage;
	
	@Before
	public void setup() {
		navegador = new ChromeDriver();
		navegador.get("http://localhost:8080/apenas-teste/limpa");
		usuariosPage = new UsuariosPage(navegador);
	}
	
	@After
	public void terarDon() {
		navegador.close();
	}
	
	@Test
	public void deveCriarUsuarioSeDadosEstaoOk() {
		
		boolean usuarioCadastrado = usuariosPage
				.visita()
				.novo()
				.popularFormulario("mario", "mario@revoltado.com")
				.clicaEmSalvar()
				.verificaSeUsuarioFoiCadastrado("mario", "mario@revoltado.com");
		
		assertTrue(usuarioCadastrado);
	}
	
	@Test
	public void naoDeveCriarUsuarioSeNaoPassarMail() {
		
		boolean usuarioCadastrado = usuariosPage
				.visita()
				.novo()
				.popularFormulario("mario", "")
				.clicaEmSalvar()
				.verificaSeUsuarioNaoFoiCadastradoSemMail();
		
		assertTrue(usuarioCadastrado);
	}
	
	@Test
	public void naoDeveCriarUsuarioSeNaoPassarNome() {
		
		boolean usuarioCadastrado = usuariosPage
				.visita()
				.novo()
				.popularFormulario("", "mario@revoltado.com")
				.clicaEmSalvar()
				.verificaSeUsuarioNaoFoiCadastradoSemNome();
		
		assertTrue(usuarioCadastrado);
	}
	
	@Test
	public void naoDeveCriarUsuarioSeNaoPassarNomeNemEmail() {
		
		boolean usuarioCadastrado = usuariosPage
				.visita()
				.novo()
				.popularFormulario("", "")
				.clicaEmSalvar()
				.verificaSeUsuarioNaoFoiCadastradoSemNomeEMail();
		
		assertTrue(usuarioCadastrado);
	}
	
	@Test
	public void deveExcluirUsuario() {
		boolean usuarioCadastrado = usuariosPage
				.visita()
				.novo()
				.popularFormulario("mario", "mario@revoltado.com")
				.clicaEmSalvar()
				.clicaBotaoExcluir()
				.confirmaExclusao()
				.verificaSeUsuarioFoiExcluido("mario", "mario@revoltado.com");
		
		assertTrue(usuarioCadastrado);
	}
}
