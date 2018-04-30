package br.ts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Begin {
	
	private static String INICIO_PERIODO = "23/12/2017";
	private static String ENTRADA = "Hora de entrada não preenchida";	
	private static String SAIDA = "Hora de saída não preenchida";
	private static String SAIDA_ALMOCO = "Hora de saída almoço não preenchida";
	private static String RETORNO_ALMOCO = "Hora de retorno almoço não preenchida";
	private static String MENOS_HORAS = "Dia encerrado com menos horas de trabalho";
	private static String NENHUMA_TAREFA = "Nenhuma tarefa apontada";
	
	private static String FILE_MANIFEST = "C:/!/TS/manifest.txt";
	private static String FILE_TIME = "C:/!/TS/time.txt";
	
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
    private static final String DB_URL = "jdbc:oracle:thin:@223.223.2.143:1521:des_amil";

    //  Database credentials
    private static final String USER = "ts_ger";
    private static final String PASS = "ts_ger";

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// create file
			System.out.println("Creating file... ");
			File f = createFile("C:/!/TS/ts.csv");
			System.out.println("File created.");
			
			// read manifest
			String lines = readManifest(FILE_MANIFEST);
			
			// write on file
			writeOnFile(f, lines);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param nameFile
	 * @return
	 * @throws IOException
	 */
	private static File createFile(String nameFile) throws IOException {
		
		File file = new File(nameFile);
		
		try {
			
			file.createNewFile();
			if(file.isFile()){
				System.out.println(">> File created susseful.");
			}
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		
		return file;
		
	}
	
	/**
	 * 
	 * @param date
	 * @param action
	 * @return
	 * @throws IOException
	 */
	private static String readFile(String date, String action) throws IOException {
		
		date = date.substring(8,10)+'/'+date.substring(5,7)+'/'+date.substring(0,4);
		
		BufferedReader br = new BufferedReader(new FileReader(FILE_TIME));
		String line = br.readLine();
		
		String hour = "";
		String time = "";
		
		try {
			
			while (line != null) {
				if(line.contains(date) && action.equals("entrada")){
					hour = line.substring(11,16)+":00";
					return hour;
				}else if(line.contains(date) && action.equals("almoco")){
					hour = line.substring(17,22)+":00";					
					return hour;
				}else if(line.contains(date) && action.equals("tempo")){
					time = line.substring(29,33)+":00";
					return time;
				}else if(line.contains(date) && action.equals("saida")){
					hour = line.substring(34,39)+":00";
					return hour;
				}
				line = br.readLine();
		    }
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}finally{
			br.close();
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * @param manifest
	 * @return
	 * @throws IOException
	 */
	private static String readManifest(String manifest) throws IOException {
		
		System.out.println("Begin reading manifest.txt...");
		
		BufferedReader br = new BufferedReader(new FileReader(manifest));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		int i = 1;
		
		try {
			
			while (line != null) {
				
				System.out.println("Reading line "+i+"... ");
				
				line = createLines(line);
				if(line != null){
					sb.append(line);
			        sb.append(System.lineSeparator());
				}
		        line = br.readLine();
		        
		        i++;
		    }

			System.out.println(sb.length());
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			throw new IOException("Erro ao ler arquivo de horas na linha: "+ line);
		}finally{
			br.close();
		}
		
		return sb.toString();
		
	}

	/**
	 * 
	 * @param file
	 * @param lines
	 * @throws IOException
	 */
	private static void writeOnFile(File file, String lines) throws IOException {
		
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		
		try {
			
			writer.println("Email,Client,Project,Description,Start date,Start time,Duration,Tags");
			writer.println(lines);
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		} finally{
			writer.close();
		}
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 * @throws IOException
	 */
	private static String createLines(String line) throws IOException {
		
		try {
			
			String dateToggle = "20"+line.substring(6,8)+"-"+line.substring(3,5)+"-"+line.substring(0,2);
			String hour = "";
			String time = "00:00:00";
			
			if(line.contains(ENTRADA)){
				hour = ','+readFile(dateToggle, "entrada");
				time = ",00:00:00";
				line = "leonardo.nascimento@topdown.com.br,AMIL,FABRICA AMIL,ENTRADA,"+dateToggle+hour+time+',';			
			}else if(line.contains(SAIDA)){
				hour = ','+readFile(dateToggle, "saida");
				time = ",00:00:00";
				line = "leonardo.nascimento@topdown.com.br,AMIL,FABRICA AMIL,SAIDA,"+dateToggle+hour+time+',';
			}else if(line.contains(SAIDA_ALMOCO)){
				hour = ','+readFile(dateToggle, "almoco");
				time = ','+readFile(dateToggle, "tempo");
				line = "leonardo.nascimento@topdown.com.br,AMIL,FABRICA AMIL,ALMOCO,"+dateToggle+hour+time+',';
			}else if(line.contains(RETORNO_ALMOCO)){
				line = null;
			}else if(line.contains(MENOS_HORAS)){
				line = null;
				// line = "leonardo.nascimento@topdown.com.br,AMIL,FABRICA AMIL,Avaliando CL / Acompanhando desenvolvimento / correção,"+dateToggle+",15:00:00,01:00:00,CL00003734";
			}else if(line.contains(NENHUMA_TAREFA)){
				line = getLineChamados(dateToggle, conn());
			}
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		
		return line;
	}
	
	/**
	 * 
	 * @param dateToggle
	 * @param conn
	 * @return
	 */
	private static String getLineChamados(String dateToggle, Connection conn) throws SQLException {

		// 2017-12-26
		// 0123456789
		String dateConsulta = dateToggle.substring(8,10)+'/'+dateToggle.substring(5,7)+'/'+dateToggle.substring(0,4);
		String line = "leonardo.nascimento@topdown.com.br,AMIL,FABRICA AMIL,Acompanhando correção / distribuição,"+dateToggle+",11:00:00,01:00:00,SD";
		
		try {
			
			String numChamado = "";
			PreparedStatement stmt = null;
			String qry = "select gce2.num_chamado, trunc(gce2.dt_abertura)"
					+ "  from ts_ger.ger_chamado_ext gce2"
			      	+ " where gce2.num_chamado in"
			      	+ "       (select gceh.num_chamado"
			      	+ "          from ts_ger.ger_chamado_ext gce, ts_ger.ger_chamado_ext_hist gceh"
			      	+ "         where (gce.nom_categoria_modulo like '%DENTAL%' or"
			      	+ "               gce.nom_categoria_modulo like '%ODONTO%')"
			      	+ "           and gce.dt_abertura >= '"+INICIO_PERIODO+"'"
			      	+ "           and gceh.num_chamado = gce.num_chamado"
			      	+ "           and gceh.ind_grupo_acao = 'N3'"
			      	+ "         group by gceh.num_chamado)"
			      	+ "   and trunc(gce2.dt_abertura) = '"+dateConsulta+"'"
			      	+ " order by gce2.dt_abertura";
			      		
			stmt = conn.prepareStatement(qry);
			
			ResultSet rs = stmt.executeQuery(qry);
			
			while (rs.next()) {
				numChamado = rs.getString("NUM_CHAMADO");
				if(!numChamado.equals("")){
					line = line+numChamado;
				}else{
					line = null;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
			System.out.println("Connection closed...");
		}
		
		return line;
		
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection conn() throws SQLException {
		
		Connection conn = null;
	    
	    try {
	    	   
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	    } catch (Exception e) {
			e.printStackTrace();
		} 
	    return conn;	
	}
}