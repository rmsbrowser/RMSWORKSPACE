import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class RmsURLLoader {
	String pagina_inicial = "http://www.ramsescaldas.com";
	String mensagemrodape = "Rms Browser v1.0";
	int tamanho_da_transmissao = 0;
	int tamanho_maximo_da_transmissao = 999999;

	public void registrarLog(String mensagem) {
		// System.out.println(mensagem + "\n");
	}

	public void imprimirMensagem(String mensagem) {
		System.out.println("SystemMessage: " + mensagem);
		registrarLog(mensagem);
	}

	public InputStream abrirURL(String endereco_url) {
		InputStream dados_da_url = null;
		try {
			imprimirMensagem("Iniciando URL:" + endereco_url + "...");
			URL url = new URL(endereco_url);
			imprimirMensagem("Conectando URL:" + endereco_url + "...");
			URLConnection url_connection = url.openConnection();
			imprimirMensagem("Recebendo Dados de:" + endereco_url + "...");
			dados_da_url = url_connection.getInputStream();
			int i;
			int contador = 1;
			while ((i = dados_da_url.read()) != -1) {
				mostrarURL((char) i + "");
				contador++;
			}
			imprimirMensagem("o.k." + "Recebidos " + contador + " bytes");
		} catch (MalformedURLException mue) {
			imprimirMensagem(mue.getMessage());
		} catch (IOException ioe) {
			imprimirMensagem(ioe.getMessage());
		}
		return dados_da_url;
	}

	public byte[] capturarURL(String endereco_url) {
		InputStream dados_da_url = null;
		byte conteudo_capturado_total[] = new byte[tamanho_maximo_da_transmissao];
		try {
			imprimirMensagem("Iniciando URL:" + endereco_url + "...");
			URL url = new URL(endereco_url);
			imprimirMensagem("Conectando URL:" + endereco_url + "...");
			URLConnection url_connection = url.openConnection();
			imprimirMensagem("Recebendo Dados de:" + endereco_url + "...");
			dados_da_url = url_connection.getInputStream();
			int i;
			int contador = 1;
			while ((i = dados_da_url.read()) != -1) {
				// mostrarURL((char)i + "");
				conteudo_capturado_total[contador] = (byte) i;
				contador++;
				tamanho_da_transmissao = contador;
			}
			imprimirMensagem("o.k." + "Recebidos " + contador + " bytes");
		} catch (MalformedURLException mue) {
			imprimirMensagem(mue.getMessage());
		} catch (IOException ioe) {
			imprimirMensagem(ioe.getMessage());
		}
		byte conteudo_capturado[] = new byte[tamanho_da_transmissao - 1];
		for (int n = 1; n < tamanho_da_transmissao; n++) {
			conteudo_capturado[n - 1] = conteudo_capturado_total[n];
		}
		return conteudo_capturado;
	}

	public void mostrarURL(String dados) {
		System.out.print(dados);
	}

	public RmsURLLoader(String endereco_url) {
		imprimirMensagem("RmsUrlLoader v1.0 CopyRight by RMS 2001");
		abrirURL(endereco_url);

	}

	public RmsURLLoader(String endereco_url, String arquivo_de_saida) {
		imprimirMensagem("RmsUrlCapture v1.0 CopyRight by RMS 2001");
		imprimirMensagem("Limitado a 999.999 Bytes de Escrita");
		byte conteudo[] = capturarURL(endereco_url);
		Arquivo.escreveArquivoBinario(arquivo_de_saida, conteudo);
	}

	public static void main(String args[]) {
		int tamanho_do_argumento = args.length;
		if (tamanho_do_argumento == 1) {
			RmsURLLoader browser = new RmsURLLoader(args[0]);
		} else if (tamanho_do_argumento == 2) {
			RmsURLLoader browser = new RmsURLLoader(args[0], args[1]);
		}
	}
}
