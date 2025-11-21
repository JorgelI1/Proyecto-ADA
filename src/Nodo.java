public class Nodo {
    int dato;
    Nodo siguiente;
    Nodo anterior;

    public Nodo() {
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
    public int getDato(){
        return dato;
    }
    public void setDato(int dato){
        this.dato = dato;
    }
}


