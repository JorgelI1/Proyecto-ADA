public class ListaDoble {
    private Nodo primero;
    private Nodo ultimo;
    public void agregar(int dato){
        Nodo nuevo = new Nodo();
        nuevo.setDato(dato);

        if(primero == null){
            primero = nuevo;
            ultimo = primero;
        }
        else{
            Nodo temp = ultimo;
            ultimo = nuevo;
            ultimo.setAnterior(temp);
            temp.setSiguiente(ultimo);
        }
    }

    public void eliminar(int dato){
        Nodo temp = null; //Nodo a eliminar
        Nodo aux = null; //Nodo antes de eliminar
        if(primero == null){
            if(primero.getDato() == dato){
                //Eliminar el primero
                temp = primero;
                primero = temp.getSiguiente();
                if(primero == null){
                    ultimo = null;
                } else {
                    primero.setAnterior(null);
                }
            } else if (ultimo.getDato() == dato) {
                //Eliminar el ultimo
                temp = ultimo;
                ultimo = temp.getAnterior();
                ultimo.setSiguiente(null);
            } else {
                //Eliminar un nodo entre medio
                aux = primero;
                temp = primero.getSiguiente();
                while (temp != null) {
                    if (temp.getDato() == dato) {
                        aux.setSiguiente(temp.getSiguiente());
                        temp.getSiguiente().setAnterior(aux);
                        break;
                    }
                    aux = temp;
                    temp = temp.getSiguiente();
                }
            }
        }

    }

}
