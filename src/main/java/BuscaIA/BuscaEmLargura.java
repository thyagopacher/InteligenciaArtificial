package BuscaIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BuscaEmLargura
 * @author Thyago Henrique Pacher
 */
public class BuscaEmLargura extends Busca {

	private Jogo jogo;
	private List<No> estadoAnterior = new ArrayList<No>();

	public BuscaEmLargura() {

	}

	public BuscaEmLargura(List<Integer> peca, Jogo jogo) {
		System.out.println("\n === Buscando por largura ===");
		this.jogo = jogo;

	}

	public List<No> buscaPorNivel(int nivel, List<No> pilha) {
		return pilha.stream().filter(l -> l.getNivel() == nivel).collect(Collectors.toList());
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

	/**
	 * passa por parametro o list com posição inicial
	 */
	public boolean buscar(List<Integer> pecas) {
		int raiz = 1;
		List<No> nivelSuperior = new ArrayList<No>();
		No noUm = new No(pecas, 1);
		nivelSuperior.add(noUm);
		this.estadoAnterior.add(noUm);

		boolean acho = false;
		while (!acho) {
			for (int j = 0; j < nivelSuperior.size(); j++) {
				this.jogo.setPosBranco(this.jogo.buscaIndice(0, nivelSuperior.get(j).getPecas()));
				List<Integer> posicoesMover = this.posicoesDeOndeMover();
				int qtdPecaMover = posicoesMover.size();
				for (int i = 0; i < qtdPecaMover; i++) {
					List<Integer> pecasMovida = new ArrayList<>();
					pecasMovida.addAll(nivelSuperior.get(j).getPecas());
					
					int posicaoMovida = posicoesMover.get(i);
					int pecaMovida = pecasMovida.get(posicaoMovida);
					
					pecasMovida.set(this.jogo.getPosBranco(), pecaMovida);
					pecasMovida.set(posicoesMover.get(i), 0);
					
		            if (!estadoAnterior.isEmpty() && this.comparaEstadoAnterior(pecasMovida)) {
		                System.out.println(" --> Movimentação pulado peça: " + pecaMovida + " - posição: " + this.jogo.getPosBranco() + " pois já tinha sido executada anteriormente... \n");
		                continue;
		            }	
		            acho = this.jogo.estadoFinal(pecasMovida);
		            this.jogo.apresentaPosicoes(pecasMovida);
		            if(acho) {
		            	return acho;
		            }
		            
					No noAdicionado = new No(pecasMovida, raiz);
					estadoAnterior.add(noAdicionado);
					nivelSuperior.add(noAdicionado);
				}
				
			}
			raiz++;
		}
		return acho;
	}
}