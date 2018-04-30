import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class TesteRodrigo {

	public static void main(String[] args) {
		
		try {
			
			// Collection<String> cad = Cadastro.Cadastro("");
			testesData("04/11/2016");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("");
		 
		
		// TODO Auto-generated method stub

	}
	/**
	 * Trabalha com datas
	 * @param strData
	 * @throws Exception
	 */
	public static void testesData(String strData) throws Exception {
		
		try {
			
			Date date = null;
			
			// Define o formato que a data é recebida
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");			
	        
			// Alimenta a variável date com a informação da str
			date = (java.util.Date)formatter.parse(strData);
	        
			// instancia Calendar para adicionar a quantidade de dias
	        Calendar c = Calendar.getInstance();
	        
	        // recebe Data
	        c.setTime(date);
	        
	        // indica que a data deve receber a quantidade de dias
	        c.add(Calendar.DATE, 7);
	        
	        // imprime a data original
	        System.out.println(date);
	        
	        // altera data original com a quantidade de dias desejada
	        date = c.getTime();
	        
	        // imprime a data com a quandidade de dias desejada
	        System.out.println(date);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
