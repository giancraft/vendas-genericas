package DTO;

public class MarcaDTO  extends DTOBase{
    public boolean isEmpty() {
        return nome == null || nome.isEmpty() || id == 0;
    }
	private String nome;
	private int id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
