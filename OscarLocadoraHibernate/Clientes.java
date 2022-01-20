// import java.io.Serializable;
import java.sql.Date;


public class Clientes  {
	   private int idCliente;
       private String  Nome;
       private String Identidade;
       private int CPF;
       private int CNH;
       private String Categoria_Habilitacao;
       private Date  Validade_Habilitacao;
       private int Numero_da_Carteira;
       private Date Data_Emissao;
       private String Endereco_Res;
       private String Esquadrao;
       private String Email;
       
       public Clientes(){
       }
           
       // metodos set 
       
       public void setidCliente(int idCliente){
    	   this.idCliente = idCliente;
       }
       public void setNome (String Nome){
    	   this.Nome = Nome;
       }
       public void setIdentidade (String Identidade){
    	   this.Identidade = Identidade;
       }
       public void setCPF(int CPF){
    	   this.CPF=CPF;
       }
       public void setCNH(int CNH){
    	   this.CNH=CNH;
       }
       public void setCategoria_Habilitacao (String Categoria_Habilitacao){
    	   this.Categoria_Habilitacao = Categoria_Habilitacao;
       }
       public void setValidade_Habilitacao (Date Validade_Habilitacao){
    	   this.Validade_Habilitacao = Validade_Habilitacao;
       }
       public void setNumero_da_Carteira(int Numero_da_Carteira){
    	   this.Numero_da_Carteira=Numero_da_Carteira;
       }
       public void setData_Emissao (Date Data_Emissao){
    	   this.Data_Emissao = Data_Emissao;
       } 
       public void setEndereco_Res (String Endereco_Res){
    	   this.Endereco_Res = Endereco_Res;
       }       
       public void setEsquadrao (String Esquadrao){
    	   this.Esquadrao = Esquadrao;
       }        
       public void setemail (String Email){
    	   this.Email = Email;
       } 
            
       //metodos Get
       public int getidCliente (){
    	   return idCliente;
       }
       public String getNome(){
    	   return Nome;
       }
       public String getIdentidade(){
    	   return Identidade;
       }
       public int getCPF(){
    	   return CPF;
       }
       public int getCNH(){
    	   return CNH;
       }
       public String getCategoria_Habilitacao(){
    	   return Categoria_Habilitacao;
       }
       public Date getValidade_Habilitacao(){
    	   return Validade_Habilitacao;
       }
       public int getNumero_da_Carteira(){
    	   return Numero_da_Carteira;
       }      
       public Date getData_Emissao(){
    	   return Data_Emissao;
       }
       public String getEndereco_Res(){
    	   return Endereco_Res;
       }
       public String getEsquadrao(){
    	   return Esquadrao;
       }
       public String getEmail(){
    	   return Email;
       }
              
       
}
