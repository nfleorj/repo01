import java.net.Socket;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class Testes001 {
	
	

	public static void main(String[] args) {
		
		try {
			
			Locale ptBr = new Locale("pt", "BR");
			NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
			
			int qtdMeses = 36;
			double valInicial = 19000;
			double valTxJuros = 7.00/100;
			
			double resultado = valInicial * Math.pow(1+valTxJuros, qtdMeses);
			
			System.out.println(formato.format(resultado/qtdMeses));
			System.out.println(formato.format(resultado));
			
			
			// certificationTestes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void teste(){
		
		try {
			
			Socket cliente = new Socket("paulo",12345);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void testeData(){
		
		try {
			Date x = new Date();
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			
			sdf1.format(x);
			
			System.out.println(x);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private static void certificationTestes(){
		
		try {
			
			String s1 = "Java";
			String s2 = "Java2";
			int a = 1;
			int b = 2;
			
			StringBuilder sb1 = new StringBuilder();
			
			sb1.append("Ja").append("va");
			
			System.out.println(s1 != s2);
			System.out.println(a != b);
			System.out.println(s1.equals(s2));
			System.out.println(sb1.toString() == s1);
			System.out.println(sb1.toString().equals(s1));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
