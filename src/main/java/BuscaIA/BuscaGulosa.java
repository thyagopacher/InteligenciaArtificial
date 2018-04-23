package BuscaIA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Busca Gulosa - escolhe o ponto mais proximo sem levar em conta peso de heuristica só o peso do caminho
 * @author Thyago Henrique Pacher
 */

public class BuscaGulosa extends Busca {

    private Jogo jogo;
    private String heuristica;
    
    public BuscaGulosa() {
        System.out.println("\n === Busca Gulosa ===");
    }

    public BuscaGulosa(List<Integer> peca, Jogo jogo, String heuristica) {
        System.out.println("\n === Busca Gulosa ===");
        // this.setVetor(vetor);
        this.jogo = jogo;
        this.heuristica = heuristica;
    }

    /*Comparator for sorting the list by roll no*/
    public static Comparator<No> comparadorBusca = new Comparator<No>() {

        public int compare(No s1, No s2) {

            int rollno1 = s1.getHeuristica();
            int rollno2 = s2.getHeuristica();

            /*For ascending order*/
            return rollno1 - rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }
    };


    public boolean buscar(List<Integer> pecas) {

        List<No> pilha = new ArrayList<No>();
        No noAtual = new No(pecas, 0);
        int posBranco = this.jogo.getPosBranco();
        boolean acho = false;

        List<Integer> pecaMover = new ArrayList<Integer>();
        pecaMover.addAll(pecas);

        while (acho == false) {
            pilha = new ArrayList<No>();//limpando a pilha aqui pois só interessa as matrizes da linha
            int h1 = 0;
            int h2 = 0;
            
            this.jogo.pecasPossiveisMover();
            List<Integer> posicoesMover = this.jogo.getPosicoesMover();

            for (int posicao : posicoesMover) {
                this.jogo.setPosBranco(this.jogo.buscaIndice(0, pecas));
                pecaMover = new ArrayList<Integer>();
                pecaMover.addAll(pecas);


                int pecaMovida = pecaMover.get(posicao);
                pecaMover.set(this.jogo.getPosBranco(), pecaMovida);
                pecaMover.set(posicao, 0);
                this.jogo.setPosBranco(posicao);

                acho = this.jogo.estadoFinal(pecaMover);

                int heuristicaFinal = 0;
                if(heuristica.equals("1")) {
                	heuristicaFinal = this.jogo.pecasForadoLugar(pecaMover);
                }else {
                	heuristicaFinal = this.jogo.distanciaManhattan(pecaMovida, pecaMover);
                }
                
                
                System.out.println(" -- Heuristica final: " + heuristicaFinal + " para movimentar a peça: " + pecaMovida);
                                 
                //this.jogo.apresentaPosicoes(pecaMover);
                No no = new No();
                no.setHeuristica(heuristicaFinal);
                no.setPecas(pecaMover);
                pilha.add(no);

                if(acho){//se acho da parada no laço for e acho sendo true já para o while
                    break;
                }                
            }
            Collections.sort(pilha, comparadorBusca);
            noAtual = pilha.get(0);//no com menor heuristica
            pecas = new ArrayList<Integer>();
            pecas.addAll(noAtual.getPecas());
            this.jogo.apresentaPosicoes(noAtual.getPecas());
            this.jogo.setPosBranco(this.jogo.buscaIndice(0, pecas));
        }
        return acho;
    }
}