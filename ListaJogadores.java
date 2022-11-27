import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaJogadores {
    public static class cicloJogadores {
        LinkedList cicloJg = new LinkedList();
        Jogador d;
        cicloJogadores next;
        public cicloJogadores(Jogador d) {
            this.d = d;
        }
    }
    
    public cicloJogadores jogadorBlind = null;
    public cicloJogadores jogadorAtual = null;
    public cicloJogadores ultimoJg = null;
    public void add(Jogador d) {
        cicloJogadores novoJ = new cicloJogadores(d);
        if (jogadorBlind == null) {
            jogadorBlind = novoJ;
            ultimoJg = novoJ;
            novoJ.next = jogadorBlind;
        } else {
            ultimoJg.next = novoJ;
            ultimoJg = novoJ;
            ultimoJg.next = jogadorBlind;
        }
    }
}
