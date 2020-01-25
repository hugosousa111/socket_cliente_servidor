
import java.io.IOException; //bib das exceções
import java.net.ServerSocket; //bib do servidor 
import java.net.Socket; //bib para o cliente no socket
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
public class Servidor {

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket servidor = new ServerSocket(40000);
            //40000 é escolhido como porta pois é uma porta registada (1024 a 49151)
            
            System.out.println("Servidor Iniciado");
            System.out.println("Porta 40000 aberta");
            System.out.println("Para finalizar digite CTRL+C");
            
            Socket cliente = servidor.accept();
            //espera até a conexão com o cliente

            System.out.println("Cliente " + cliente.getInetAddress().getHostAddress() + " conectado");
            //busca o endereço IP do cliente (host) 

            Scanner leitor = new Scanner(cliente.getInputStream());
            //para ler o que o usuário enviou 

            while (leitor.hasNextLine()) {
                System.out.println(leitor.nextLine());
                //fica dentro do while "ouvindo" o cliente e exibindo o que ele envia
            }

        } catch (IOException e) {
            //caso aconteça algum erro de entrada e saida no codigo
            System.out.println("ERRO NA CONEXÃO: " + e);
        }

    }
}
