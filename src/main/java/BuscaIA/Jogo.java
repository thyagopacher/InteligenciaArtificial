
package BuscaIA;

import java.util.ArrayList;
import java.util.List;

/**
 * Jogo
 * @author Thyago Henrique Pacher
 */
public class Jogo {

    private int qtdPosicoes;
	private List<Integer> posicoes;
	protected List<Integer> posicoesMover = new ArrayList<Integer>();
	private int posBranco;
	private int ultMovida;

	public Jogo(){
		System.out.println(" == Implementação genérica do jogo == ");
	}

	public int distanciaManhattan(int peca, List<Integer> pecas){
		return 0;
	}

	public List<Integer> pecasPossiveisMover(List<Integer> pecas) {
		return null;
	}
	
    public int pecasForadoLugar(List<Integer> pecas){
        return 0;
	}
	
	/**
	 * verifica se o jogo chegou a seu estado final pelo quadrado inteiro
	 */
	public boolean estadoFinal(List<Integer> posicaoEstado){
		return false;
	}

	/**
	 * peças quais mover?
	 */
	public void pecasPossiveisMover(){

	}
	
	public List<Integer> pecasOndeMover(){
		this.pecasPossiveisMover();
		return this.getPosicoesMover();
	}

	/** 
	 * fazendo rodar o jogo com as buscas
	*/
	public void jogando(Busca busca){

	}

    public void carregaPosicoes(){

    }

	public int buscaIndice(int peca, List<Integer> posicaoBusca){
		return 0;
	}

	public void apresentaPosicoes(){
		this.apresentaPosicoes(this.posicoes);
	}

	public void apresentaPosicoesDois(List<String> pecas){

	}

	/** 
	 * faz apresentação das peças para o jogo
	 * @param pecas é um array de No
	 */
    public void apresentaPosicoes(List<Integer> pecas){
    	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int linha = 0;
				System.out.println();
				for (int peca : pecas) {
					if(linha == 3){
						System.out.println();
						linha = 0; 
					}			
					if(peca == 0){
						System.out.print("  | ");
					}else{
						System.out.print(peca + " | ");
					}
					
					linha++;			
				}      
				System.out.println("\n == \n");
				
			}
		});
    	thread.start();
    }

	/**
	 * @return the qtdPosicoes
	 */
	public int getQtdPosicoes() {
		return qtdPosicoes;
	}

	/**
	 * @param qtdPosicoes the qtdPosicoes to set
	 */
	public void setQtdPosicoes(int qtdPosicoes) {
		this.qtdPosicoes = qtdPosicoes;
	}

    public List<Integer> getPosicoes(){
		return posicoes;
	}

	public void setPosicoes(List<Integer> posicoes) {
		this.posicoes = posicoes;
	}

	/**
	 * @return the posicoesMover
	 */
	public List<Integer> getPosicoesMover() {
		return posicoesMover;
	}

	/**
	 * @param posicoesMover the posicoesMover to set
	 */
	public void setPosicoesMover(List<Integer> posicoesMover) {
		this.posicoesMover = posicoesMover;
	}

	/**
	 * @return the posBranco
	 */
	public int getPosBranco() {
		return posBranco;
	}

	/**
	 * @param posBranco the posBranco to set
	 */
	public void setPosBranco(int posBranco) {
		this.posBranco = posBranco;
	}

	/**
	 * @return the ultMovida
	 */
	public int getUltMovida() {
		return ultMovida;
	}

	/**
	 * @param ultMovida the ultMovida to set
	 */
	public void setUltMovida(int ultMovida) {
		this.ultMovida = ultMovida;
	}	
}