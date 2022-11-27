import java.util.Queue;
import java.util.LinkedList;
public class Baralho {
    private Carta[] cartas = new Carta[52];
    private Queue baralho = new LinkedList<Carta>();
    public Baralho() {

        String[] naipes = {"♣","♦", "♠",  "♥"};
        String[] nomes ={"A","K","Q","J","10","9","8","7","6","5","4","3","2"};

    int controle = 0;
        for (int i = 0 ; i< naipes.length; i++){
            for (int j = 0; j< nomes.length; j++){
                this.cartas [controle] = new Carta().set(naipes[i],nomes[j]);
//                System.out.println(cartas[controle].get());
                controle ++;
            }
        }
    }
    public void embaralhar() {
        int troca = 100;
        for (int i = 0; i < troca; i++){
            int pos1=0;
            int pos2=0;
                do {
                    pos1 = (int) (Math.random() * 52);
                    pos2 = (int) (Math.random() * 52);
                } while ( pos1 == pos2 );
                Carta auxiliar = this.cartas[pos1];
                this.cartas[pos1] = this.cartas[pos2];
                this.cartas[pos2] = auxiliar;
        }
        for (int i = 0; i < 52; i++) {
            this.baralho.add(this.cartas[i]);
        }
    }
    public void imprimirBaralho(){
        for (int i = 0; i < this.cartas.length; i++){
        System.out.print(" "+ this.cartas[i].get()+" ");
        }
    }
    public Carta darCarta() {
        return (Carta) this.baralho.remove();
    }
    public boolean temCarta() throws Exception {
        try {
            if ( this.baralho.size() > 1){
                System.out.println(this.baralho.size());
                return true;
            } else if (this.baralho.size() == 1) {
                System.out.println("Uma carta restante");
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Sem cartas no monte.");
        }
        return false;
    }


}