package BuscaIA;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BuscaEmLarguraTest extends TestCase {

	Jogo jogo = new QuebraCabeca();
	
	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Busca busca = new BuscaEmLargura(jogo.getPosicoes(), jogo);
		busca.buscar(jogo.getPosicoes());
		assertTrue(true);
	}
}
