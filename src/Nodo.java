public class Nodo {
    String dato;
    String genero;  // Nuevo campo para guardar el género
    Nodo siguiente;
    Nodo anterior;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Nodo getSiguiente(){
        return siguiente;
    }
    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }
    public Nodo getAnterior(){
        return anterior;
    }
    public void setAnterior(Nodo anterior){
        this.anterior = anterior;
    }
    public String getDato(){
        return dato;
    }
    public void setDato(String dato){
        this.dato = dato;
    }
    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
}


