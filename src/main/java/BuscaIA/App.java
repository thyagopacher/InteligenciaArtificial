package BuscaIA;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Execução de buscar entre jogos
 *@author Thyago Henrique Pacher
 */
public class App {

    public void menu() {
        try {
            Jogo jogo = null;
            Busca busca = null;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Escolha a opção de busca:\n");
            System.out.println("1) Quebra cabeça 8 peças");
            System.out.println("2) Resta Um");
            System.out.println(" == Opção: ==");
            String input = br.readLine();
            System.out.println(" -- Você escolheu: " + input);
            if(input.isEmpty()) {
            	System.exit(-1);
            }
            if(input.equals("1")) {
            	jogo = new QuebraCabeca();
            }else if(input.equals("2")){
            	jogo = new RestaUm();
            }
    		
            System.out.println("\n == Escolha a busca ==");
            System.out.println("1) Busca em Largura");
            System.out.println("2) Busca em Profundidade");
            System.out.println("3) Busca Gulosa");
            System.out.println("4) Busca Gulosa A*");
            String input2 = br.readLine();
            if(input2.equals("1")) {
            	busca = new BuscaEmLargura(jogo.getPosicoes(), jogo);
            }else if(input2.equals("2")){
            	busca = new BuscaEmProfundidade(jogo.getPosicoes(), jogo);
            }else if(input2.equals("3") || input2.equals("4")){
            	System.out.println("\n == Escolha a heuristica a ser usada:");
            	System.out.println("1) h1 - número de peças fora do lugar");
            	System.out.println("2) h2 - distância Manhattan (número de casas longe da posição final em cada direção)");
            	String input3 = br.readLine();
            	
            	if(input2.equals("3")) {
            		busca = new BuscaGulosa(jogo.getPosicoes(), jogo, input3);
            	}else {
            		busca = new BuscaGulosaA(jogo.getPosicoes(), jogo, input3);
            	}
            	
            }  

            //ativa o jogo
            jogo.jogando(busca);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.menu();
        // QuebraCabeca qc = new QuebraCabeca();
        //RestaUm ru = new RestaUm();
    }
}
