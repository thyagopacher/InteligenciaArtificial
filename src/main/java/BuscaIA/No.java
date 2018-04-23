package BuscaIA;

import java.util.List;

/**
 * No
 * @author Thyago Henrique Pacher
 */
public class No {

	private String nome;
	private int nivel;
	private int peso;
	private int heuristica;
	private int pesoFinal;
	private List<Integer> pecas; 

	public No(){
	}

	public No(List<Integer> pecas, int nivel){
		this.pecas = pecas;
		this.nivel = nivel;
	}

	public No(int nome){
		this.nome = String.valueOf(nome);
	}

	public No(String nome, int nivel, int peso) {
		this.nome = nome;
		this.nivel = nivel;
		this.peso = peso;
	}

	public No(String nome, int nivel, int peso, int heuristica) {
		this.nome = nome;
		this.nivel = nivel;
		this.peso = peso;
		this.heuristica = heuristica;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * @return the heuristica
	 */
	public int getHeuristica() {
		return heuristica;
	}

	/**
	 * @param heuristica the heuristica to set
	 */
	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	/**
	 * @return the pesoFinal
	 */
	public int getPesoFinal() {
		return pesoFinal;
	}

	/**
	 * @param pesoFinal the pesoFinal to set
	 */
	public void setPesoFinal(int pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

	/**
	 * @return the pecas
	 */
	public List<Integer> getPecas() {
		return pecas;
	}

	/**
	 * @param pecas the pecas to set
	 */
	public void setPecas(List<Integer> pecas) {
		this.pecas = pecas;
	}

}