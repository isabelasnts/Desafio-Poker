public class Jogador {
    private String Nome;
    private double Aposta = 0;
    private boolean Apto = true;

    private Carta[] mao = new Carta[2];
    private double dinheiro = 100.00;
    public Jogador() {
    }
    public void Inapto(){
        this.Apto = false;
    }
    public boolean getApto(){
        return this.Apto;
    }
    public double getAposta(){
        return this.Aposta;
    }
    public void addAposta(double Aposta){
        this.Aposta += Aposta;
    }
    public void zeraAposta(){
        this.Aposta = 0;
    }
    public Jogador insereNome(String Nome) {
        this.Nome = Nome;
        return this;
    }
    public void imprimeJogador() {
        System.out.println("Jogador " + this.Nome+" possui as cartas: " + this.mao[0].get()+ " " +this.mao[1].get()+" e $"+ this.dinheiro + " no pote");
    }
    public void insereCarta(int pos, Carta carta) {
        this.mao[pos] = carta;
    }
    public boolean retiraDinheiro(double retirada) {
       if (retirada>this.dinheiro){
           return false;
       }
        this.dinheiro -= retirada;
        return true;
    }
    public void addDinheiro(double deposito) {
        this.dinheiro += deposito;
    }
    public String getNome() {
       return this.Nome;
    }
    public double getSaldo() {
        return this.dinheiro;
    }

}




