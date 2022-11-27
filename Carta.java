public class Carta {
    public String nome;
    public String naipe;

    public Carta() {
    }

    public Carta set(String naipe, String nome) {
        this.naipe = naipe;
        this.nome = nome;
        return this;
    }

    public String get() {
        return (this.naipe + this.nome);
    }
}
