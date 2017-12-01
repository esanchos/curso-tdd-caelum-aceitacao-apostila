package br.com.caelum.aceitacao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeilaoTest {
	
	private WebDriver navegador;
	private UsuariosPage usuariosPage;
	private LeilaoPage leilaoPage;

	@Before
	public void setUp() throws Exception {
		navegador = new ChromeDriver();
		navegador.get("http://localhost:8080/apenas-teste/limpa");
		usuariosPage = new UsuariosPage(navegador);
		leilaoPage = new LeilaoPage(navegador);
	}

	@After
	public void tearDown() throws Exception {
		navegador.close();
	}

	@Test
	public void deveCriarLeilaoComDadosValidos() {
		
		usuariosPage
			.visita()
			.novo()
			.popularFormulario("Mario", "mario@revoltado")
			.clicaEmSalvar();
		
		leilaoPage
			.vaiParaTelaDeLeilao()
			.clicaEmNovoLeilao()
			.preencheFormulario("tv", 5000.0, "Mario", true)
			.enviaFormulario();
		
		boolean verificaLeilaoCriado = leilaoPage.validaSeLeilaoFoiCriado("tv", 5000.0, "Mario", true);
		
		assertTrue(verificaLeilaoCriado);
	}
	
	@Test
	public void deveExibirMensagemDeNomeObrigatorioSeNaoPassarNome() {
		
		usuariosPage
			.visita()
			.novo()
			.popularFormulario("Mario", "mario@revoltado")
			.clicaEmSalvar();
		
		leilaoPage
			.vaiParaTelaDeLeilao()
			.clicaEmNovoLeilao()
			.preencheFormulario("", 5000.0, "Mario", true)
			.enviaFormulario();
		
		boolean contemMessagemDeErro = leilaoPage.verificaSeExibiuErroDeNomeObrigatorio();
		
		assertTrue(contemMessagemDeErro);
	}
	
	@Test
	public void deveExibirMensagemDeValorMaiorQue0SeNaoPassarValor() {
		
		usuariosPage
			.visita()
			.novo()
			.popularFormulario("Mario", "mario@revoltado")
			.clicaEmSalvar();
		
		leilaoPage
			.vaiParaTelaDeLeilao()
			.clicaEmNovoLeilao()
			.preencheFormulario("TV", 0.0, "Mario", true)
			.enviaFormulario();
		
		boolean contemMessagemDeErro = leilaoPage.verificaSeExibiuErroValorMaiorQue0();
		
		assertTrue(contemMessagemDeErro);
	}
	
	@Test
	public void deveExibirMensagemDuasMensagensDeErroSeNaoPassarNomeNemValor() {
		
		usuariosPage
			.visita()
			.novo()
			.popularFormulario("Mario", "mario@revoltado")
			.clicaEmSalvar();
		
		leilaoPage
			.vaiParaTelaDeLeilao()
			.clicaEmNovoLeilao()
			.preencheFormulario("", 0.0, "Mario", true)
			.enviaFormulario();
		
		boolean contemMessagemDeErro = leilaoPage.verificaSeExebiuDuasMensagensDeErro();
		
		assertTrue(contemMessagemDeErro);
	}

}
