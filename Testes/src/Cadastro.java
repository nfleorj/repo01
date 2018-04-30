import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class Cadastro {

	/**
	 * 
	 * @param abc
	 * @return
	 */
	public static Collection Cadastro(String abc)throws Exception {
		
		Collection collCpf =  new ArrayList();
		Collection collApostas =  new ArrayList();
		Collection collCpfApostas =  new ArrayList();

		try {
			
			collCpf =  leArquivos("nomeArquivo");
			collApostas =  leArquivos("nomeArquivo");
			collCpfApostas =  leArquivos("nomeArquivo");
			
			// cria coleção de CPF
			for (int i = 0; i < collCpf.size(); i++) {
				
				collCpf.add(i);
				
			}
			
			// compara CPF com apostas
			for (int j = 0; j < collApostas.size(); j++) {
				
				String cpfApostas = "";

				for (int i = 0; i < collCpf.size(); i++) {
					if(cpfApostas.equals(collCpf.toString())){
						
						String aucCpfApostas = cpfApostas + collCpf.toString();   
						collCpfApostas.add(aucCpfApostas);
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return collCpfApostas;

	}
	
	/**
	 * 
	 * @param nomeArquivo
	 * @return
	 * @throws IOException
	 */
	private static Collection leArquivos(String nomeArquivo) throws IOException {
		
		Collection<String> coll = new ArrayList<String>();
		
		try {
			
			// fazer a leitura do arquivo e trazer as informações necessárias para a comparação
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return coll;
		
	}

}
