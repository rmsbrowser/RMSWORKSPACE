import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;


public class RmsBrowser extends Frame {
	private static final long serialVersionUID = 8972998909128178277L;
	MenuBar barrademenu;
	Menu arquivo;
	Menu exibir;
	MenuItem abrir;
	MenuItem sair;
	Panel painel0;
	Panel painel1;
	Panel painel2;
	Panel painel3;
	Panel painel4;
	Panel painel5;
	Label botaoendereco;
	JButton botaoir;
	JButton botaovoltar, botaoavancar, botaoparar, botaoatualizar;
	JButton botaopaginainicial, botaopesquisar, botaofavoritos;
	JButton botaohistorico;
	JEditorPane pagina_formatada = new JEditorPane();
	ScrollPane barra_de_scroll = new ScrollPane();
	TextField texto1;
	TextField texto2;
	TextField textorodape;
	int min_altura = 430;
	int min_largura = 630;
	int constantetexto = 9;
	String pagina_inicial = "http://www.ramsescaldas.com";
	String mensagemrodape = "Rms Browser v1.0";
	CheckboxMenuItem botoespadrao, barradeenderecos, barradestatus;
	CheckboxMenuItem registro_de_operacoes;
	CheckboxMenuItem mostrar_arquivo_fonte;
	Font fonte_padrao = new Font("MS Sans Serif", Font.PLAIN, 11);
	TextArea areacentral = new TextArea();

	Frame janela_de_log = new Frame("Registro de Logs");
	Panel painel_de_log = new Panel();
	TextArea area_de_log = new TextArea();

	PSMostrarURL psmostrarurl = new PSMostrarURL();
	HTMLBackGround pHTMLbackground = new HTMLBackGround();

	public void registrarLog(final String mensagem) {
		if (registro_de_operacoes.getState()) {
			area_de_log.append(mensagem + "\n");
		}
	}

	public void imprimirMensagem(final String mensagem) {
		textorodape.setText(mensagem);
		registrarLog(mensagem);
	}

	@SuppressWarnings("deprecation")
	public void formatarPaginaURL(final URL url) {
		imprimirMensagem("Formatando Pagina...");
		try {
			pagina_formatada.setPage(url);
		} catch (final IOException ioe) {
			imprimirMensagem(ioe.getMessage());
		}
		barra_de_scroll.setVisible(true);
		show();
		imprimirMensagem("Pagina Formatada com Sucesso");
	}

	public InputStream abrirURL(final String endereco_url) {
		InputStream dados_da_url = null;

		try {
			imprimirMensagem("Iniciando URL:" + endereco_url + "...");
			final URL url = new URL(endereco_url);
			imprimirMensagem("Conectando URL:" + endereco_url + "...");
			final URLConnection url_connection = url.openConnection();
			imprimirMensagem("Recebendo Dados de:" + endereco_url + "...");
			dados_da_url = url_connection.getInputStream();
			if (mostrar_arquivo_fonte.getState()) {
				psmostrarurl = new PSMostrarURL();
				psmostrarurl.setPage(dados_da_url);
				if (psmostrarurl.isAlive()) {
					imprimirMensagem("Tarefa em Andamento");
				} else if (!psmostrarurl.isAlive()) {
					psmostrarurl.start();
					imprimirMensagem("Tarefa Iniciada");
				}
			}
			pHTMLbackground = new HTMLBackGround();
			pHTMLbackground.setPage(url);
			if (pHTMLbackground.isAlive()) {
				imprimirMensagem("Tarefa em Andamento");
			} else if (!pHTMLbackground.isAlive()) {
				pHTMLbackground.start();
				imprimirMensagem("Tarefa Iniciada");
			}
		} catch (final MalformedURLException mue) {
			imprimirMensagem(mue.getMessage());
		} catch (final IOException ioe) {
			imprimirMensagem(ioe.getMessage());
		}
		return dados_da_url;

	}

	public void mostrarURL(final String dados) {
		areacentral.append(dados);
	}

	public void pararProcessos() {
		psmostrarurl = null;
		pHTMLbackground = null;
		imprimirMensagem("Tarefas Canceladas!");

	}

	public void iniciaRegistroDeLog() {
		janela_de_log.setBounds(100, 100, 300, 300);
		janela_de_log.setLayout(new BorderLayout());
		janela_de_log.setBackground(Color.lightGray);
		janela_de_log.add(painel_de_log, BorderLayout.CENTER);
		painel_de_log.setVisible(true);
		painel_de_log.add(area_de_log);
		area_de_log.setVisible(true);
		janela_de_log.setVisible(false);
	}

	public void iniciaInterfaceGrafica() {

		setBackground(Color.lightGray);
		setBounds(10, 10, min_largura, min_altura);

		final Win w = new Win();
		addWindowListener(w);

		barrademenu = new MenuBar();
		setMenuBar(barrademenu);

		arquivo = new Menu("Arquivo");
		barrademenu.add(arquivo);

		exibir = new Menu("Exibir");
		barrademenu.add(exibir);

		botoespadrao = new CheckboxMenuItem(" Botoes Padrao ", true);
		barradeenderecos = new CheckboxMenuItem(" Barra de Enderecos ", true);
		barradestatus = new CheckboxMenuItem(" Barra de Status ", true);
		registro_de_operacoes = new CheckboxMenuItem(" Log de Operacoes ",
				false);
		mostrar_arquivo_fonte = new CheckboxMenuItem(" Mostrar Codigo Fonte ",
				false);

		exibir.add(botoespadrao);
		exibir.add(barradeenderecos);
		exibir.add(barradestatus);
		exibir.add(registro_de_operacoes);
		exibir.add(mostrar_arquivo_fonte);

		final ExibirSimOuNao sn = new ExibirSimOuNao();
		botoespadrao.addItemListener(sn);
		barradeenderecos.addItemListener(sn);
		barradestatus.addItemListener(sn);
		registro_de_operacoes.addItemListener(sn);
		mostrar_arquivo_fonte.addItemListener(sn);

		abrir = new MenuItem("Abrir");
		arquivo.add(abrir);
		sair = new MenuItem("Sair");
		arquivo.add(sair);

		painel0 = new Panel();
		painel0.setLayout(new BorderLayout());
		add(painel0, BorderLayout.CENTER);

		painel1 = new Panel();
		painel1.setLayout(new BorderLayout());
		painel0.add(painel1, BorderLayout.NORTH);

		painel2 = new Panel();
		painel2.setLayout(new BorderLayout());
		painel1.add(painel2, BorderLayout.NORTH);

		painel3 = new Panel();
		painel3.setLayout(new BorderLayout());
		painel2.add(painel3, BorderLayout.SOUTH);

		painel4 = new Panel();
		painel4.setLayout(new FlowLayout());

		painel2.add(painel4, BorderLayout.NORTH);

		botaovoltar = new JButton("  Voltar   ", new ImageIcon(
				"RmsBrowser-voltar.gif"));
		botaoavancar = new JButton("  Avancar  ", new ImageIcon(
				"RmsBrowser-avancar.gif"));
		botaoparar = new JButton("  Parar    ", new ImageIcon(
				"RmsBrowser-parar.gif"));
		botaoatualizar = new JButton(" Atualizar ", new ImageIcon(
				"RmsBrowser-atualizar.gif"));
		botaopaginainicial = new JButton("Pagina Inicial", new ImageIcon(
				"RmsBrowser-paginainicial.gif"));
		botaopesquisar = new JButton(" Pesquisar ", new ImageIcon(
				"RmsBrowser-pesquisar.gif"));
		botaofavoritos = new JButton(" Favoritos ");
		botaohistorico = new JButton(" Historico ");

		botaopaginainicial.setFont(fonte_padrao);
		botaovoltar.setFont(fonte_padrao);
		botaoavancar.setFont(fonte_padrao);
		botaoparar.setFont(fonte_padrao);
		botaoatualizar.setFont(fonte_padrao);
		botaopesquisar.setFont(fonte_padrao);
		botaofavoritos.setFont(fonte_padrao);
		botaohistorico.setFont(fonte_padrao);

		painel4.add(botaopaginainicial);
		painel4.add(botaovoltar);
		painel4.add(botaoavancar);
		painel4.add(botaoparar);
		painel4.add(botaoatualizar);
		painel4.add(botaopesquisar);
		painel4.add(botaofavoritos);
		painel4.add(botaohistorico);

		botaoendereco = new Label("Endereco:  ");
		botaoendereco.setFont(fonte_padrao);
		painel3.add(botaoendereco, BorderLayout.WEST);

		texto1 = new TextField(pagina_inicial, constantetexto);
		texto1.setFont(fonte_padrao);
		texto1.setBounds(10, 10, 200, 15);
		texto1.setVisible(true);
		painel3.add(texto1);

		painel3.add(new Label(" "), BorderLayout.EAST);
		botaoir = new JButton(" Navegar! ");
		botaoir.setFont(fonte_padrao);
		painel3.add(botaoir, BorderLayout.EAST);

		textorodape = new TextField(mensagemrodape, constantetexto);
		textorodape.setBackground(Color.lightGray);
		textorodape.setFont(fonte_padrao);
		add(textorodape, BorderLayout.SOUTH);

		painel5 = new Panel();
		painel5.setLayout(new BorderLayout());
		painel5.add(areacentral, BorderLayout.NORTH);
		areacentral.setVisible(false);
		pagina_formatada.setEditable(false);
		barra_de_scroll.add(pagina_formatada);
		painel5.add(barra_de_scroll, BorderLayout.CENTER);

		painel0.add(painel5, BorderLayout.CENTER);

		final MouseClick mouseclick = new MouseClick();
		botaoir.addMouseListener(mouseclick);
		botaoparar.addMouseListener(mouseclick);

		setVisible(true);

	}

	@SuppressWarnings("deprecation")
	public RmsBrowser() {
		super("RmsBrowser version 1.0 CopyRigth by RMS 2001");
		iniciaInterfaceGrafica();
		iniciaRegistroDeLog();
		show();
		imprimirMensagem("Metodos Completos");

	}

	class PSMostrarURL extends Thread {
		int i = 0;
		int contador = 1;
		InputStream dados_da_url;

		PSMostrarURL() {
		}

		PSMostrarURL(final InputStream dados_da_url) {
			this.dados_da_url = dados_da_url;
		}

		public void setPage(final InputStream dados_da_url) {
			this.dados_da_url = dados_da_url;
		}

		public void run() {
			try {
				while ((i = dados_da_url.read()) != -1) {
					mostrarURL((char) i + "");
					contador++;
				}
			} catch (final IOException ioe) {
				imprimirMensagem(ioe.getMessage());
			}
			imprimirMensagem("o.k." + "Recebidos " + contador + " bytes");
		}

	}

	class HTMLBackGround extends Thread {
		URL url;

		public HTMLBackGround() {
		}

		public HTMLBackGround(final URL url) {
			this.url = url;
		}

		public void setPage(final URL url) {
			this.url = url;
		}

		public void run() {
			imprimirMensagem("Formatando Pagina em BackGround ...");
			try {
				pagina_formatada.setPage(url);
				imprimirMensagem("ok");
			} catch (final IOException ioe) {
				imprimirMensagem(ioe.getMessage());
			}
		}
	}

	class Win extends WindowAdapter {
		public void windowClosing(final WindowEvent event) {
			dispose();
			System.exit(0);
		}
	}

	class ExibirSimOuNao implements ItemListener {
		@SuppressWarnings("deprecation")
		public void itemStateChanged(final ItemEvent event) {
			if (event.getSource() == botoespadrao) {
				painel4.setVisible(!painel4.isVisible());
				show();
			}

			else if (event.getSource() == barradeenderecos) {
				botaoendereco.setVisible(!botaoendereco.isVisible());
				botaoir.setVisible(!botaoir.isVisible());
				texto1.setVisible(!texto1.isVisible());
				show();
			}

			else if (event.getSource() == barradestatus) {
				textorodape.setVisible(!textorodape.isVisible());
				show();
			} else if (event.getSource() == registro_de_operacoes) {
				janela_de_log.setVisible(!janela_de_log.isVisible());
			} else if (event.getSource() == mostrar_arquivo_fonte) {
				areacentral.setVisible(!areacentral.isVisible());
				show();
			}

		}
	}

	class MouseClick extends MouseAdapter {
		public void mouseClicked(final MouseEvent event) {
			if (event.getSource() == botaoir) {
				imprimirMensagem("Mouse Clicado no Botao " + "Navegar para:"
						+ texto1.getText());
				abrirURL(texto1.getText());
			} else if (event.getSource() == botaoparar) {
				imprimirMensagem("Mouse Clicado no Botao " + "Parar:"
						+ texto1.getText());
				pararProcessos();
			}

		}
	}

	public static void main(final String args[]) {
		@SuppressWarnings("unused")
		final
		RmsBrowser browser = new RmsBrowser();
	}
}
