package n2;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Cliente {

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Socket conexao = new Socket("127.0.0.1", 12345); // Servidor local é aberto pela porta 12345
		InputStream entrada = conexao.getInputStream(); // Objeto de entrada é criado
		OutputStream saida = conexao.getOutputStream(); // Objeto de saída e criado

		 boolean rodando = true;
		 
		while (rodando) {
			System.out.println();
			System.out.println("Digite um n�mero >> ");
			String msnClient = teclado.nextLine(); // Resposta encaminhada ao Cliente

			// Envio de bytes da string mensagem para o servidor
			byte[] dadosClient = msnClient.getBytes(); // String transformado em um array de bytes
			saida.write(dadosClient); // Envio dos bytes
			saida.flush();// Força o envio de pouco bytes

			byte[] dadosServidor = new byte[1024];
			entrada.read(dadosServidor);
			String mensagemServidor = new String(dadosServidor);
			System.out.println("SERVIDOR ---> " + mensagemServidor);

			if(mensagemServidor.trim().equals("Acertou(mizer�vi)!")) {
                System.out.println("Programa Finalizado");
                rodando = false;
			/*
			 * Agora, você precisa receber o pacote do servidor e criar a sua lógica Use a
			 * mesma lógica que você usou no servidor para receber o pacote
			 */

		}

	}
}
}