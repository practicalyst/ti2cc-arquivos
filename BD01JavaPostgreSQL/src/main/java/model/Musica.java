package model;

public class Musica {
	private int codigo;
	private String nome;
	private String autor;
	
	public Musica() {
		this.codigo = -1;
		this.nome = "";
		this.autor = "";
	}
	
	public Musica(int codigo, String nome, String autor) {
		this.codigo = codigo;
		this.nome = nome;
		this.autor = autor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	@Override
	public String toString() {
		return "Musicas [codigo=" + codigo + ", nome=" + nome + ", autor=" + autor + "]";
	}	
}
