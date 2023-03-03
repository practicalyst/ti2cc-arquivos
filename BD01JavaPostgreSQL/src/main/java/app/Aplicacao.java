package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.MusicaDAO;
import model.Musica;

public class Aplicacao {
	
	static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		int op;
		
		System.out.println("\n\n Sistema de Cadastro! \n");
		System.out.println("1 - Listar");
		System.out.println("2 - Inserir");
		System.out.println("3 - Excluir");
		System.out.println("4 - Atualizar");
		System.out.println("0 - Sair");
		
		
		do {
			System.out.print("\n Digite sua opção: ");
			op = sc.nextInt();
		}while(op > 4 || op < 0);
		
		return op;
	}
	public static void main(String[] args) throws Exception {
		
		MusicaDAO musicaDAO = new MusicaDAO();
		int opcao;
		
		System.out.println("\n\n Bem vindo ao Sistema de Cadastro de Banco de Dados!");
		
		do {
			opcao = menu();
			List<Musica> musicas = musicaDAO.get();
			
			
			int index;
			String nome;
			String autor;
			
			sc.nextLine(); // limpar buffer
			
			switch(opcao) {
			case 1:
				System.out.println("\nListando músicas no acervo: \n");
				for(Musica u: musicas) {
					System.out.println(u.toString());
				}
				break;
			case 2:
				index = musicas.get(musicas.size() - 1).getCodigo(); // pega o codigo da ultima musica cadastrada
				
				System.out.print("\nNome da Música: ");
				nome = sc.nextLine();
				
				System.out.print("\nNome do Autor: ");
				autor = sc.nextLine();
				
				Musica novaTrack = new Musica(index + 1, nome, autor);
				
				if(musicaDAO.insert(novaTrack) == true) {
					System.out.println("Musica inserida com sucesso! -> " + novaTrack.toString());
				}
				break;
			case 3:
				
				System.out.print("\nDigite o código da música que se quer excluir: ");
				index = sc.nextInt();
				musicaDAO.delete(index);
				
				break;
			case 4:
				
				System.out.print("\nDigite o código da música que se quer alterar: ");
				index = sc.nextInt();
				
				sc.nextLine();
				
				System.out.print("\nNome da Música: ");
				nome = sc.nextLine();
				
				System.out.print("\nNome do Autor: ");
				autor = sc.nextLine();
				
				Musica atualizarTrack = new Musica(index, nome, autor);
								
				musicaDAO.update(atualizarTrack);

				break;
			default:
				
			
			}			
			
		}while(opcao != 0);	
		
	}
		
		
}