import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;


public class Principal {
	public static void main(String[] args) {
	 Session sessao = HibernateUtility.getSession(); 
	 Transaction transaction = sessao.beginTransaction(); 
	 Clientes cliente = new Clientes(); 
	 Clientes.setNome("Oscar Neto"); 
	 Clientes.setIdentidade("1.111.111");
	 sessao.save(cliente); 
	 transaction.commit(); 
	 sessao.close(); 
	}
}
