package BuscaIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BuscaEmProfundidade
 */
public class BuscaEmProfundidade extends Busca {

	private Jogo jogo;
	String ultPosicao = "";
	private List<No> estadoAnterior = new ArrayList<No>();

	public BuscaEmProfundidade(List<Integer> peca, Jogo jogo) {
		System.out.println("\n === Buscando por profundidade ===");
		this.jogo = jogo;
	}

	/**
	 * comparação de estados anteriores perante a matriz
	 * 
	 * @param pecas
	 *            List com posições e peças
	 */
	public boolean comparaEstadoAnterior(List<Integer> pecas) {
		boolean res = false;
		int qtd = pecas.size();
		int qtdIgual = 0;
		for (No no : estadoAnterior) {
			qtdIgual = 0;// zera para comparação em nó posterior
			for (int i = 0; i < qtd; i++) {
				if (no.getPecas().get(i) == pecas.get(i)) {
					qtdIgual++;
				}
			}
			if (qtdIgual == qtd) {
				return true;
			}
		}
		return res;
	}

	public List<Integer> posicoesDeOndeMover() {
		this.jogo.pecasPossiveisMover();
		return this.jogo.getPosicoesMover();
	}

	public boolean buscar(List<Integer> pecas) {
		int raiz = 1;
		List<No> nivelSuperior = new ArrayList<No>();
		No noUm = new No(pecas, 1);
		nivelSuperior.add(noUm);
		this.estadoAnterior.add(noUm);
		this.jogo.setPosicoes(pecas);
		this.jogo.setPosBranco(this.jogo.buscaIndice(0, pecas));
		List<Integer> posicoesMover = this.jogo.pecasPossiveisMover(pecas);
		int qtdPecaMover = posicoesMover.size();
		boolean acho = false;

		while (!acho) {
			for (int i = 0; i < qtdPecaMover; i++) {
				List<Integer> pecasMovida = new ArrayList<>();
				pecasMovida.addAll(pecas);

				int posicaoMovida = posicoesMover.get(i);
				int pecaMovida = pecasMovida.get(posicaoMovida);

				if (pecaMovida != 0) {
					this.jogo.setPosBranco(this.jogo.buscaIndice(0, pecas));
					pecasMovida.set(this.jogo.getPosBranco(), pecaMovida);
					pecasMovida.set(posicoesMover.get(i), 0);
					
					No noAdicionado = new No(pecasMovida, raiz);

					if (!estadoAnterior.isEmpty() && this.comparaEstadoAnterior(pecasMovida)) {
						System.out.println(" --> Movimentação pulado peça: " + pecaMovida + " - posição: "
								+ this.jogo.getPosBranco() + " pois já tinha sido executada anteriormente... \n");

						if (i == qtdPecaMover - 1) {
							return false;
						} else {
							continue;
						}
					}

					acho = this.jogo.estadoFinal(pecasMovida);
					this.jogo.apresentaPosicoes(pecasMovida);
					estadoAnterior.add(noAdicionado);
					if (acho) {
						return acho;
					} else {
						acho = this.buscar(pecasMovida);
						if (acho) {
							return acho;
						}
					}
				}
			}
		}
		return acho;
	}
}