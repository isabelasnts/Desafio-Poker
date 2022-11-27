import java.util.Scanner;
import java.lang.Exception;
import static java.lang.System.exit;

public class Poker {
    public static int checkJogadores() {
        Scanner scan = new Scanner(System.in);
        int numJogadores = 0;
        while (true) {
            try {
                System.out.println("\nQuantas pessoas irão jogar?");
                String entrada = scan.next();
                if (!entrada.matches("^[0-9]*$") ){
                    throw new Exception();
                }
                numJogadores = Integer.parseInt(entrada);
                if (numJogadores <= 1 || numJogadores > 8) {
                    throw new Exception();
                } else {
                    System.out.println("Ok. " + numJogadores + " jogadores irão participar do jogo.");
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Erro. Digite um número inteiro e positivo, entre 2 e 8.");
                continue;
            }
        }
        return numJogadores;
    }
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        Baralho baralho = new Baralho();
        baralho.temCarta();
        baralho.embaralhar();
        baralho.imprimirBaralho();
        Carta[] mesa = new Carta[5];


        int numJogadores = checkJogadores();

        Jogador[] jogadores = new Jogador[numJogadores];

        for (int i = 0; i < numJogadores; i++) {
            jogadores[i] = new Jogador();
            System.out.println("Insira o nome do jogador #" + (i + 1));
            jogadores[i].insereNome(scan.next());
            jogadores[i].insereCarta(0, baralho.darCarta());
            jogadores[i].insereCarta(1, baralho.darCarta());
        }
        for (int i = 0; i < numJogadores; i++) {
            jogadores[i].imprimeJogador();
        }

        int rodada = 0;
        double apostaBlind = 5;
        double controleAposta = apostaBlind;

        for (int i = 0; i < numJogadores; i++) {
            jogadores[i].insereCarta(0, baralho.darCarta());
            jogadores[i].insereCarta(1, baralho.darCarta());
        }

        ListaJogadores lj = new ListaJogadores();
        for (int i = 0; i < numJogadores; i++) {
            lj.add(jogadores[i]);
        }

        for (; ;) {
            double pote = 0;
            lj.jogadorAtual = lj.jogadorBlind;
            if (lj.jogadorAtual.d.retiraDinheiro(apostaBlind)) {
                pote += apostaBlind;
                lj.jogadorAtual.d.addAposta(apostaBlind);
            }
            boolean apostaIgual = false;
            String resposta;
            String ganhador;
            boolean isApto = true;
            while(true) {
                isApto = lj.jogadorAtual.d.getApto();
                lj.jogadorAtual = lj.jogadorAtual.next;
                if(lj.jogadorAtual.d.getApto()==false){
                    continue;
                }
                if (lj.jogadorAtual == lj.jogadorBlind) {
                    while (lj.jogadorAtual != lj.ultimoJg) {
                        if (controleAposta != lj.jogadorAtual.d.getAposta()) {
                            System.out.println("Jogador " + lj.jogadorAtual.d.getNome() + " deseja igualar a aposta? Digite sim ou nao");
                            resposta = scan.next();
                            if (resposta.equalsIgnoreCase("sim")) {
                                pote += controleAposta - lj.jogadorAtual.d.getAposta();
                                System.out.println("\nValor atual da mesa: "+String.valueOf(pote));
                                lj.jogadorAtual.d.retiraDinheiro(controleAposta - lj.jogadorAtual.d.getAposta());
                                lj.jogadorAtual.d.addAposta(controleAposta - lj.jogadorAtual.d.getAposta());
                                System.out.println("\nCartas na mesa: ");
                                for (int i=0; i < 5; i++){
                                    mesa[i] = baralho.darCarta();
                                    System.out.print(mesa[i].get()+" ");
                                }
                                for(int i = 0; i<numJogadores; i++){
                                    if(jogadores[i].getApto()){
                                        jogadores[i].imprimeJogador();
                                    }
                                }
                                System.out.println("Quem ganhou a rodada? Digite o nome do jogador.");
                                ganhador = scan.next();
                                System.out.println("Parabéns jogador "+ganhador+"!\n Você ganhou: $"+pote);
                                exit(0);
                            }
                        }
                        lj.jogadorAtual = lj.jogadorAtual.next;
                    }
                }
                try {
                    System.out.println("\nJogador "+lj.jogadorAtual.d.getNome()+ ", deseja apostar? Digite sim ou nao");
                    resposta = scan.next();
                    if (resposta.equalsIgnoreCase("sim")) {
                        System.out.println("quanto deseja apostar?");
                        double aposta = scan.nextDouble();
                        if (lj.jogadorAtual.d.retiraDinheiro(aposta)) {
                            pote += aposta;
                            if(aposta>controleAposta){
                                controleAposta = aposta;
                            }
                            lj.jogadorAtual.d.addAposta(aposta);
                            System.out.println("\nValor atual da mesa: "+String.valueOf(pote));
                        } else {
                            System.out.println("Você não pode apostar $" + aposta + ". Saldo disponível: $" + lj.jogadorAtual.d.getSaldo());
                        }
                        continue;
//                    } else (resposta.equalsIgnoreCase("nao")) {
//                    lj.jogadorAtual.d.Inapto();
//                    for(int i = 0; i<numJogadores; i++){
//                        if(jogadores[i].getNome() == lj.jogadorAtual.d.getNome()){
//                            jogadores[i].Inapto();
//                        }
//                    }
//                    for(int i = 0; i<numJogadores; i++){
//                        if(jogadores[i].getApto()){
//                            jogadores[i].imprimeJogador();
//                        }
//                    }
                    }
                } catch (Exception e) {
                    lj.jogadorAtual.d.Inapto();
                    for(int i = 0; i<numJogadores; i++){
                        if(jogadores[i].getNome() == lj.jogadorAtual.d.getNome()){
                            jogadores[i].Inapto();
                        }
                    }
                    for(int i = 0; i<numJogadores; i++){
                        if(jogadores[i].getApto()){
                            jogadores[i].imprimeJogador();
                        }
                    }
                    throw new RuntimeException(e);
                }
            }
        }
//            scan.close();
        }

    }




