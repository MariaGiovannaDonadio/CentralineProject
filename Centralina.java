public class Centralina {
    private int id;
    private String nome;

    public Centralina(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String toString(){
        return this.nome;
    }

}
