package controlevendas;


import controlevendas.Produto;

public interface Interface extends java.rmi.Remote{	
   public void Cadastrar(Produto p) throws java.rmi.RemoteException;
   public void Cadastrar(Cliente c) throws java.rmi.RemoteException;
   public void Cadastrar(Funcionario f)throws java.rmi.RemoteException;
   Produto produto = new Produto();
   Funcionario funcionario = new Funcionario();
   Cliente cliente = new Cliente();
   public String getNomeProd();
   public double getValor();
   public int getQtd();
   public String getNome();
   public String getCpf();
   public int getIdade();
}	
