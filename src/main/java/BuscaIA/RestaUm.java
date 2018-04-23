
package BuscaIA;

import java.util.ArrayList;
import java.util.List;

/**
 * RestaUm
 * @author Thyago Henrique Pacher
 */
public class RestaUm extends Jogo {

  private int qtdPosicoes;

  public RestaUm() {
    System.out.println(" == Implementação jogo resta um == ");
    this.setQtdPosicoes(33);
    this.carregaPosicoes();
    // Busca busca;

    // busca = new BuscaEmLargura(this.getPosicoes(), this);
    // this.jogando(busca);

    // busca = new BuscaEmProfundidade(this.getPosicoes(), this);
    // this.jogando(busca);

    // busca = new BuscaGulosa(this.getPosicoes(), this);
    // this.jogando(busca);

    // busca = new BuscaGulosaA(this.getPosicoes(), this);
    // this.jogando(busca);
  }

  public void carregaPosicoes() {
    int qtdPosicoes = this.getQtdPosicoes();
    List<Integer> posicoes = new ArrayList<Integer>(qtdPosicoes);
    for (int i = 0; i < 33; i++) {
      posicoes.add(0);
    }
    this.setPosicoes(posicoes);
    this.apresentaPosicoes(posicoes);
  }

  public void apresentaPosicoes() {
    this.apresentaPosicoes(this.getPosicoes());
  }

  public void apresentaPosicoes(List<Integer> pecas){
    int qtdPasso = 0;
    String tabuleiro = "";
    String espaco = "        ";
    for (int i = 0; i < pecas.size(); i++) {
      String peca = " ";
      if(pecas.get(i) != null){
        peca = String.valueOf(pecas.get(i));
      }
      if(qtdPasso == 0 || qtdPasso == 3){
        tabuleiro += espaco;
      }
      if(qtdPasso == 3){
        tabuleiro += "\n" + espaco;
      }
      if(qtdPasso == 6 || qtdPasso == 13 || qtdPasso == 20 || qtdPasso == 27 || qtdPasso == 30){
        tabuleiro += "\n";
      }
      if(qtdPasso == 27 || qtdPasso == 30){
        tabuleiro += espaco;
      }
      if(peca == "1"){
        tabuleiro += "X | ";
      }else{
        tabuleiro += peca + " | ";
      }
      
      qtdPasso++;
    }
    System.out.println(tabuleiro);
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
}