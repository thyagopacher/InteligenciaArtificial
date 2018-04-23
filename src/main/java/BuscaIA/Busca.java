package BuscaIA;

import java.util.ArrayList;
import java.util.List;

/**
 * Busca
 */
public class Busca{

    private int custoBusca = 0;
    private int pontoInicial;
    private int pontoFinal;
    private List<No> vetor = new ArrayList<No>();
	
    public Busca(){

    }

    public void buscar(){

    }

    public boolean buscar(List<Integer> posicoes){
		return false;
	}
	
	public void setarPontos(int ptinicial, int ptfinal){
		this.pontoInicial = ptinicial;
		this.pontoFinal = ptfinal;
	}

	/**
	 * @return the vetor
	 */
	public List<No> getVetor() {
		return vetor;
	}

	/**
	 * @param vetor the vetor to set
	 */
	public void setVetor(List<No> vetor) {
		this.vetor = vetor;
	}

	/**
	 * @return the pontoInicial
	 */
	public int getPontoInicial() {
		return pontoInicial;
	}

	/**
	 * @param pontoInicial the pontoInicial to set
	 */
	public void setPontoInicial(int pontoInicial) {
		this.pontoInicial = pontoInicial;
	}


	/**
	 * @return the pontoFinal
	 */
	public int getPontoFinal() {
		return pontoFinal;
	}

	/**
	 * @param pontoFinal the pontoFinal to set
	 */
	public void setPontoFinal(int pontoFinal) {
		this.pontoFinal = pontoFinal;
	}

	/**
	 * @return the custoBusca
	 */
	public int getCustoBusca() {
		return custoBusca;
	}

	/**
	 * @param custoBusca the custoBusca to set
	 */
	public void setCustoBusca(int custoBusca) {
		this.custoBusca = custoBusca;
    }
	
	public void somarCustoBusca(){
        this.custoBusca++;
	}

    public void somarCustoBusca(int valor){
        this.custoBusca = valor + this.custoBusca;
	}


}