package n2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Servidor {
	public static void main(String[] args) throws IOException {
		Scanner entrada_usuario = new Scanner(System.in);

		// comunica com o cliente pela porta
		ServerSocket serv = new ServerSocket(12345);
		System.out.println("Aguardando conexão do cliente na porta 12345...");

		// aceitar a conexão com o cliente
		Socket conexao = serv.accept();

		// entrada de dados
		InputStream entrada = conexao.getInputStream();

		// saída de dados
		OutputStream saida = conexao.getOutputStream();

		// Este comando é para que seja criado um "espaço" em bytes para

		// numero sorteado
		Random rdm = new Random();
		int numero = rdm.nextInt(100);
		System.out.println("Numero sorteado:" + numero);

		// converte em string os dados enviados pelo cliente

		while (true) {
			byte[] dadosUsuario = new byte[500];
			String msnClient = new String(dadosUsuario);
			// lê os dados que foram recebidos
			entrada.read(dadosUsuario);
			System.out.println("Palpite do cliente = " + msnClient);

			// String mensagemServer = entrada_usuario.nextLine(); // Envia a resposta para
			// o Cliente

			String dadosConvertidos = new String(dadosUsuario);

			Integer palpite = Integer.parseInt(dadosConvertidos.trim());

			String avaliacao;
			if (palpite < numero) {
				avaliacao = "Mais";
			} else if (palpite > numero) {
				avaliacao = "Menos";
			} else {
				avaliacao = "Acertou(mizer�vi)!";
			}
			
			/*
			 * Você precisa avaliar os dados recebidos pra saber se é maior, menor ou
			 * acertou. Você precisa devolver a mensagem pro cliente.
			 * 
			 */

			// Enviar os bytes da string mensagem para o servidor
			byte[] dadosServer = avaliacao.getBytes();// Transformar a String em um array de bytes
			saida.write(dadosServer); // Envia os bytes
			saida.flush(); // Força o envio de pouco bytes

		}

	}
}
