import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TesteCalculoReembolso {

	public static void main(String[] args) {
		
		try {
			
			// int vlrParametro = 1000;	// PARAM
			// int vlrInf = 22; 			// VI
			// int vlrTotal = 80;			// VT
			// int vlrCalc = vlrInf; 		// VC
			// calculaReembolso(vlrCalc, vlrTotal, vlrParametro, vlrInf);
			
			Connection.connOracle("jdbc:oracle:thin:@223.223.2.143:1521:des_amil", "TS_ODO", "xpto2000");
			
			
			
		} catch (Exception e) {
			
		}
	}
	
	
	private static String getParam(String numContrato, String codPlano, Connection conn) throws SQLException {

		try {
			
			String dtIniValidade = "";
			String dtFimValidade = "";
			
			PreparedStatement stmt = null;
			String qry = "select rc.* from ts.contrato_empresa ce, ts.reembolso_contrato rc"
					+ "where ce.num_contrato = ?"
					+ "and rc.cod_ts_contrato = ce.cod_ts_contrato"
					+ "and rc.cod_plano = ?";

			stmt = conn.prepareStatement(qry);
			
			stmt.setString(1, numContrato);
			stmt.setString(2, codPlano);
			      
			ResultSet rs = stmt.executeQuery(qry);
			
			while (rs.next()) {
				dtIniValidade = rs.getString("DT_INI_VALIDADE");
				dtFimValidade = rs.getString("DT_FIM_VALIDADE");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return line;
	}
	
	/**
	 * 
	 * @param vlrCalc
	 * @param vlrTotal
	 * @param vlrParametro
	 * @param vlrInf
	 * @throws Exception
	 */
	private static void calculaReembolso(int vlrCalc, int vlrTotal, int vlrParametro, int vlrInf) throws Exception {
		
		try {
			
			int valDisponivel = vlrParametro - vlrTotal;
			
			if(valDisponivel <= 0){
				System.out.println("APLICA GLOSA 2584");
				
			}else if(vlrInf > valDisponivel){
				System.out.println("Reembolso pago COM VALORES ALTERADOS, sem glosa");
				System.out.println("Vlr. Inf. = "+ vlrInf);
				System.out.println("Vlr. Calc. = "+ valDisponivel);
				
				int valGlosado = vlrInf - valDisponivel;
				System.out.println("Vlr. Glosado = "+ valGlosado);
				
				int valPago = vlrInf - valGlosado;
				System.out.println("Vlr. Pago = "+ valPago);
				
			}else{
				System.out.println("Reembolso pago CONFORME SOLICITADO, sem glosa");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
