
import java.io.IOException; //bib das exceções
import java.io.PrintStream; //bib para a saida do cliente
import java.net.Socket; // bib para coneção com o servidor 
import java.net.UnknownHostException;
import java.util.Scanner; //bib para leitura

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hugo Sousa e Kamila Amélia
 */
public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            Socket cliente = new Socket("127.0.0.1", 40000);
            /*conecta com o servidor, dizendo endereço e porta, 
            o end é 127.0.0.1(localhost) pois os dois estão na mesma máquina*/
            
            System.out.println("Cliente criado");
            System.out.println("Cliente conectado ao servidor");
            System.out.println("Para finalizar digite CTRL+C");
                    
            Scanner leitor = new Scanner(System.in);

            PrintStream saida = new PrintStream(cliente.getOutputStream());
            // a saida do cliente

            String assunto; //var que guarda o assunto
            String mensagem = ""; //var que guarda a mensagem
            String resultado; //var que guarda o assunto e a mensagem formatado para enviar 

            System.out.println("ASSUNTO: ");
            while (leitor.hasNextLine()) {
                //fica nesse looping enquanto o usuário digita e envia

                assunto = leitor.nextLine(); //ler o assunto

                if (assunto.isEmpty()) {
                    assunto = "ASSUNTO: SEM ASSUNTO";
                    //se o assunto for vazio, troca por sem assunto
                } else {
                    assunto = "ASSUNTO: " + assunto;
                    //só formata
                }

                do {
                    //para forçar o usuário a escrever uma mensagem, para poder enviar
                    if (mensagem.equals("MENSAGEM: SEM MENSAGEM")) {
                        System.out.println("DIGITE UMA MENSAGEM");
                    }
                    System.out.println("MENSAGEM: ");
                    mensagem = leitor.nextLine();
                    if (mensagem.isEmpty()) {
                        mensagem = "MENSAGEM: SEM MENSAGEM";
                    } else {
                        mensagem = "MENSAGEM: " + mensagem;
                        //formata a mensagem
                    }
                } while (mensagem.equals("MENSAGEM: SEM MENSAGEM"));

                resultado = assunto + "\n" + mensagem;
                //junta as duas partes já formatadas

                saida.println(resultado);
                /*envia resultado como saida do cliente
                que vai para o while do servidor que está escutando*/

                System.out.println("ASSUNTO: ");
            }
        } catch (IOException e) {
            //caso aconteça algum erro de entrada e saida no codigo
            System.out.println("ERRO NA CONEXÃO: " + e);
        }
    }
}
