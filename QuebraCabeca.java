package BuscaIA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Quebra Cabeca 8 peças
 * Heuristicas para usar
 * h1(n): número de peças fora do lugar
 * h2(n): distãncia Manhattan(número de casas longe da posição final em cada direção)
 */
public class QuebraCabeca extends Jogo{

	private int posBranco = 0;
	

	public QuebraCabeca(){
		System.out.println(" == Implementação jogo quebra cabeça 8 peças == ");
		this.setQtdPosicoes(9);
		this.carregaPosicoes();

		Busca busca = new BuscaEmLargura(this.getPosicoes(), this);
		this.jogando(busca);
	}

	public void carregaPosicoes(){
		int qtdPosicoes = this.getQtdPosicoes();
		List<No> posicoes = new ArrayList<No>(qtdPosicoes);
		posicoes.add(0, new No(0, 0, 0));
		posicoes.add(1, new No(1, 0, 1));
		posicoes.add(2, new No(2, 0, 2));
		posicoes.add(3, new No(7, 1, 3));
		posicoes.add(4, new No(8, 1, 4));
		posicoes.add(5, new No(3, 1, 5));
		posicoes.add(6, new No(6, 2, 6));
		posicoes.add(7, new No(5, 2, 7));
		posicoes.add(8, new No(4, 2, 8));
		this.setPosicoes(posicoes);		
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

	public int buscaIndice(No noBusca, List<No> posicaoBusca){
		int qtd = posicaoBusca.size();
		for(int i = 0; i < qtd; i++){
			No noAtual = posicaoBusca.get(i);
			if(noAtual.getNome().equals(noBusca.getNome())){
				return i;
			}
		}
		return 0;
	}

	public int buscaIndice(No noBusca){
		int qtd = this.getPosicoes().size();
		for(int i = 0; i < qtd; i++){
			No noAtual = this.getPosicoes().get(i);
			if(noAtual.getNome().equals(noBusca.getNome())){
				return i;
			}
		}
		return 0;
	}

	public boolean estadoFinal(List<No> posicaoEstado){
		if(posicaoEstado.get(0).equals("1") && posicaoEstado.get(1).equals("2") && posicaoEstado.get(2).equals("3")
			&& posicaoEstado.get(3).equals("8") && posicaoEstado.get(4).equals("0") && posicaoEstado.get(5).equals("4")
			&& posicaoEstado.get(6).equals("7") && posicaoEstado.get(7).equals("6") && posicaoEstado.get(8).equals("5")){
				return true;
		}
		return false;
	}

	/**
	 * retorna as posições disponíveis para movimentação de peças ao espaço em branco
	 */
	public void pecasPossiveisMover(){
		posicoesMover = new ArrayList<Integer>();
		int qtdPosicoes = this.getPosicoes().size();
		int posLadoDireito = posBranco + 1;
		int posLadoEsquerdo = posBranco - 1;
		int posCima = posBranco - 3;
		int posBaixo = posBranco + 3;

		if(posLadoDireito >= 0 && posLadoDireito < qtdPosicoes && posBranco != 2 && posBranco != 5 && posBranco != 8){
			posicoesMover.add(posLadoDireito);
		}

		if(posLadoEsquerdo >= 0 && posLadoEsquerdo < qtdPosicoes){
			posicoesMover.add(posLadoEsquerdo);
		}

		if(posCima >= 0 && posCima < qtdPosicoes){
			posicoesMover.add(posCima);
		}

		if(posBaixo >= 0 && posBaixo < qtdPosicoes){
			posicoesMover.add(posBaixo);
		}						
	}

	/**
	 * executa o jogo - a Busca determina qual das peças irá mover 
	 */
	public void jogando(Busca busca){
		long start = System.currentTimeMillis();

		List<No> pecaJogoSOrdem = new ArrayList<No>();
		pecaJogoSOrdem.addAll(this.getPosicoes());		

		List<No> pecaJogo = new ArrayList<No>();
		pecaJogo.addAll(this.getPosicoes());
		//ordena pelo nome da peça
		Collections.sort(pecaJogo, comparadorBusca);

		int qtd = pecaJogo.size();
		if(qtd > 0){
			No noBranco = this.getPosicoes().get(0);
			posBranco = 0;
			/** passa as casas de 1 a 8 movendo para espaço em branco */
			for (int i = 0; i < qtd; i++) {
				int posicaoFinal = posBranco;//seta posição final para se mover para a posição do espaço em branco
				No noAtual = pecaJogo.get(i);
				int posicaoInicial = this.buscaIndice(noAtual);
				posBranco = posicaoInicial;//faz a posição do espaço em branco receber a posição da peça anteriormente movida

				//vendo quais peças mover
				this.pecasPossiveisMover();
				System.out.println(" == Peças encontradas para mover == ");
				for (int posicao: this.getPosicoesMover()) {
					System.out.println("Posição para mover: " + posicao);
					
				}

				busca.buscar(this.getPosicoes());
				pecaJogoSOrdem.set(posicaoInicial, noBranco);//seta nó em branco para nova posição no array
				pecaJogoSOrdem.set(posicaoFinal, noAtual);//seta peça atual do laço para nova posição aonde era espaço em branco
				this.apresentaPosicoes(pecaJogoSOrdem);		
			}
			
		}
		this.setPosicoes(pecaJogoSOrdem); //carregando posições arrumadas
		long elapsed = System.currentTimeMillis() - start;
		System.out.printf("\n ==> Tempo de execução: %.3f ms%n", elapsed / 1000d);
	}

}
