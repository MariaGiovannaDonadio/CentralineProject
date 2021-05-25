public class Sensore {
    private int id;
    private String tipo;

    public Sensore(int id, String tipo){
        this.id = id;
        this.tipo = tipo;
    }

    public int getId(){
        return this.id;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String toString() {
        return Integer.toString(this.id);
    }

}
