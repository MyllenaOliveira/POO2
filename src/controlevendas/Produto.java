package controlevendas;

public class Produto {
    private String nomeProd;
    private double valor;
    private int Qtd;
            
   public Produto(){
       
   }
    
    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }
    
    @Override
    public String toString(){
        return(nomeProd+","+valor+","+Qtd);
    }
}
