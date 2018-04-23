package BuscaIA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Quebra Cabeca 8 peças
 * Heuristicas para usar
 * h1(n): número de peças fora do lugar
 * h2(n): distãncia Manhattan(número de casas longe da posição final em cada direção)
 * @author Thyago Henrique Pacher
 */
public class QuebraCabeca extends Jogo {

	List<Integer> estadoFinal = new ArrayList<Integer>();

	public QuebraCabeca() {
		this.setPosBranco(0);
		System.out.println(" == Implementação jogo quebra cabeça 8 peças == ");
		this.setQtdPosicoes(9);
		this.carregaPosicoes();

	}

	public void carregaPosicoes() {
		int qtdPosicoes = this.getQtdPosicoes();
		List<Integer> posicoes = new ArrayList<Integer>(qtdPosicoes);
		posicoes.add(0);
		posicoes.add(1);
		posicoes.add(2);
		posicoes.add(7);
		posicoes.add(8);
		posicoes.add(3);
		posicoes.add(6);
		posicoes.add(5);
		posicoes.add(4);
		this.setPosicoes(posicoes);

		/**estado ao qual se desejo chegar */
		estadoFinal.add(1);
		estadoFinal.add(2);
		estadoFinal.add(3);
		estadoFinal.add(8);
		estadoFinal.add(0);
		estadoFinal.add(4);
		estadoFinal.add(7);
		estadoFinal.add(6);
		estadoFinal.add(5);
	}

	public int distanciaManhattan(int peca, List<Integer> pecas) {
		int qtdPosicoesFora = 0;
		int qtdPecas = pecas.size();
		for(int i = 0; i < qtdPecas; i++){
			for(int j = 0; j < qtdPecas; j++){
				if(pecas.get(i) != 0 && pecas.get(i) == estadoFinal.get(j)){
					if(i == j){
						qtdPosicoesFora += 0;
					}
					if(i >= 0 && i <= 2 && j >= 3 && j <= 5){
						qtdPosicoesFora += 1;//move da primeira linha a segunda
					}
					if(i >= 0 && i <= 2 && j >= 6 && j <= 8){
						qtdPosicoesFora += 2;//move da primeira linha a ultima
					}					
					if(i >= 3 && i <= 5 && j >= 6 && j <= 8){
						qtdPosicoesFora += 1;//move da segunda linha a ultima
					}
					if(i >= 3 && i <= 5 && j >= 0 && j <= 2){
						qtdPosicoesFora += 1;//move da segunda linha a primeira
					}					
					if((i == 0 || i == 3 || i == 6 || i == 2 || i == 5 || i == 8) && (j == 1 || j == 4 || j == 7)){
						qtdPosicoesFora += 1;//move da primeira coluna para a segunda ou da ultima a segunda
					}	
					if((i == 0 || i == 3 || i == 6) && (j == 2 || j == 5 || j == 8)){
						qtdPosicoesFora += 2;//move da primeira coluna para a ultima soma 2 pois são 2 colunas para ir.
					}		
					if((i == 1 || i == 4 || i == 7) && (j != 1 && j != 4 && j != 7)){
						qtdPosicoesFora += 1;//move do meio para um dos lados
					}
					if((i >= 6 && i <= 8) && (j == 3 || j == 4 || j == 5)){
						qtdPosicoesFora += 1;//move do ult para cima
					}
					if((i == 2 || i == 5 || i == 8) && (j == 0 || j == 3 || j == 6)){
						qtdPosicoesFora += 2; // move da ult coluna para primeira
					}
					break;
				}
			}
		}
		return qtdPosicoesFora;
	}

	public int pecasForadoLugar(List<Integer> pecas) {
		int tamVetor = pecas.size();
		int qtdFora = 0;
		for (int i = 0; i < tamVetor; i++) {
			if (pecas.get(i) != 0 && pecas.get(i) != estadoFinal.get(i)) {
				qtdFora++;
			}
		}
		return qtdFora;
	}

	/** pode ser usado junto a Collections.sort para ordenar list de No */
	public static Comparator<No> comparadorBusca = new Comparator<No>() {

		public int compare(No s1, No s2) {

			int rollno1 = Integer.valueOf(s1.getNome());
			int rollno2 = Integer.valueOf(s2.getNome());

			/*For ascending order*/
			return rollno1 - rollno2;

			/*For descending order*/
			//rollno2-rollno1;
		}
	};

	public int buscaIndice(int pecaBusca) {
		return this.buscaIndice(pecaBusca, this.getPosicoes());
	}

	public int buscaIndice(int pecaBusca, List<Integer> posicaoBusca) {
		int qtd = posicaoBusca.size();
		for (int i = 0; i < qtd; i++) {
			int pecaAtual = posicaoBusca.get(i);
			if (pecaAtual == pecaBusca) {
				return i;
			}
		}
		return 0;
	}

	public boolean estadoFinal(List<Integer> posicaoEstado) {
		int tamVetor = posicaoEstado.size();
		for (int i = 0; i < tamVetor; i++) {
			if (posicaoEstado.get(i) != estadoFinal.get(i)) {
				return false;
			}
		}
		return true;
	}

	public List<Integer> pecasPossiveisMover(List<Integer> pecas) {
		posicoesMover = new ArrayList<Integer>();
		int qtdPosicoes = pecas.size();
		int posLadoDireito = this.getPosBranco() + 1;
		int posLadoEsquerdo = this.getPosBranco() - 1;
		int posCima = this.getPosBranco() - 3;
		int posBaixo = this.getPosBranco() + 3;

		//verifica se a posição atual do espaço em branco tem lado direito
		if (posLadoDireito >= 0 && posLadoDireito < qtdPosicoes && this.getPosBranco() != 2 && this.getPosBranco() != 5
				&& this.getPosBranco() != 8) {
			posicoesMover.add(posLadoDireito);
		}

		//verifica se a posição atual do espaço em branco tem lado esquerdo
		if (posLadoEsquerdo >= 0 && posLadoEsquerdo < qtdPosicoes && this.getPosBranco() != 0
				&& this.getPosBranco() != 3 && this.getPosBranco() != 6) {
			posicoesMover.add(posLadoEsquerdo);
		}

		//verifica se a posição atual do espaço em branco tem pos cima
		if (posCima >= 0 && posCima < qtdPosicoes && this.getPosBranco() > 2) {
			posicoesMover.add(posCima);
		}

		//verifica se a posição atual do espaço em branco tem pos baixo
		if (posBaixo >= 0 && posBaixo < qtdPosicoes && this.getPosBranco() < 6) {
			posicoesMover.add(posBaixo);
		}
		return posicoesMover;
	}	
	
	/**
	 * retorna as posições disponíveis para movimentação de peças ao espaço em branco
	 */
	public void pecasPossiveisMover() {
		posicoesMover = new ArrayList<Integer>();
		int qtdPosicoes = this.getPosicoes().size();
		int posLadoDireito = this.getPosBranco() + 1;
		int posLadoEsquerdo = this.getPosBranco() - 1;
		int posCima = this.getPosBranco() - 3;
		int posBaixo = this.getPosBranco() + 3;

		//verifica se a posição atual do espaço em branco tem lado direito
		if (posLadoDireito >= 0 && posLadoDireito < qtdPosicoes && this.getPosBranco() != 2 && this.getPosBranco() != 5
				&& this.getPosBranco() != 8) {
			posicoesMover.add(posLadoDireito);
		}

		//verifica se a posição atual do espaço em branco tem lado esquerdo
		if (posLadoEsquerdo >= 0 && posLadoEsquerdo < qtdPosicoes && this.getPosBranco() != 0
				&& this.getPosBranco() != 3 && this.getPosBranco() != 6) {
			posicoesMover.add(posLadoEsquerdo);
		}

		//verifica se a posição atual do espaço em branco tem pos cima
		if (posCima >= 0 && posCima < qtdPosicoes && this.getPosBranco() > 2) {
			posicoesMover.add(posCima);
		}

		//verifica se a posição atual do espaço em branco tem pos baixo
		if (posBaixo >= 0 && posBaixo < qtdPosicoes && this.getPosBranco() < 6) {
			posicoesMover.add(posBaixo);
		}
	}

	/** 
	 * faz apresentação das peças para o jogo
	 * @param pecas é um array de inteiros
	 */
	public void apresentaPosicoes(List<Integer> pecas) {
		int linha = 0;
		System.out.println();
		for (int pecaAtual : pecas) {
			if (linha == 3) {
				System.out.println();
				linha = 0;
			}
			if (pecaAtual == 0) {
				System.out.print("  | ");
			} else {
				System.out.print(pecaAtual + " | ");
			}

			linha++;
		}
		System.out.println("\n");
	}

	/**
	 * executa o jogo - a Busca determina qual das peças irá mover 
	 */
	public void jogando(Busca busca) {
		long start = System.currentTimeMillis();
		busca.buscar(this.getPosicoes());
		long elapsed = System.currentTimeMillis() - start;
		System.out.printf("\n ==> Tempo de execução: %.3f ms%n", elapsed / 1000d);
	}

}