public class ListaDoble {
    Nodo primero;
    Nodo ultimo;


    public void agregar(String dato, String genero){
        Nodo nuevo = new Nodo(dato);  // Usar constructor con dato
        nuevo.setDato(dato);
        nuevo.setGenero(genero);

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

    public void eliminar(String dato) {
        if (primero == null) {
            return; // Lista vacía
        }

        // Eliminar el primer nodo
        if (primero.getDato().equals(dato)) {
            Nodo temp = primero;
            primero = primero.getSiguiente();
            if (primero != null) {
                primero.setAnterior(null);
            } else {
                ultimo = null; // La lista quedó vacía
            }
            return;
        }

        // Eliminar el último nodo
        if (ultimo.getDato().equals(dato)) {
            Nodo temp = ultimo;
            ultimo = ultimo.getAnterior();
            if (ultimo != null) {
                ultimo.setSiguiente(null);
            } else {
                primero = null; // La lista quedó vacía
            }
            return;
        }

        // Eliminar nodo intermedio
        Nodo actual = primero.getSiguiente();
        while (actual != null && actual != ultimo) {
            if (actual.getDato().equals(dato)) {
                Nodo anterior = actual.getAnterior();
                Nodo siguiente = actual.getSiguiente();

                anterior.setSiguiente(siguiente);
                if (siguiente != null) {
                    siguiente.setAnterior(anterior);
                }
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public void imprimir() {
        if (primero == null) {
            System.out.println("  (vacio)");
            return;
        }

        Nodo temp = primero;
        while (temp != null) {
            System.out.println("  " + temp.getDato());
            temp = temp.getSiguiente();
        }
    }
}
