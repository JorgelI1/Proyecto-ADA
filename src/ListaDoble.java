public class ListaDoble {
    Nodo primero;
    Nodo ultimo;

    public void agregar(String dato){
        Nodo nuevo = new Nodo(dato);  // Usar constructor con dato

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
            return; // Lista vacía, no hay nada que eliminar
        }

        Nodo temp = null;
        Nodo aux = null;

        // Eliminar el primer nodo
        if (primero.getDato().equals(dato)) {
            temp = primero;
            primero = temp.getSiguiente();
            if (primero == null) {
                ultimo = null;
            } else {
                primero.setAnterior(null);
            }
            return;
        }
        // Eliminar el último nodo
        else if (ultimo.getDato().equals(dato)) {
            temp = ultimo;
            ultimo = temp.getAnterior();
            if (ultimo != null) {
                ultimo.setSiguiente(null);
            } else {
                primero = null; // Si era el único nodo
            }
            return;
        }
        // Eliminar nodo intermedio
        else {
            aux = primero;
            temp = primero.getSiguiente();
            while (temp != null && temp != ultimo) {
                if (temp.getDato().equals(dato)) {
                    aux.setSiguiente(temp.getSiguiente());
                    if (temp.getSiguiente() != null) {
                        temp.getSiguiente().setAnterior(aux);
                    }
                    return;
                }
                aux = temp;
                temp = temp.getSiguiente();
            }
        }
    }

    public void imprimir() {
        if (primero == null) {
            System.out.println("La lista está vacía");
            return;
        }

        //System.out.println("Recorrido hacia adelante:");//Recorrido hacia adelante:
        Nodo temp = primero;
        while(temp != null){
            System.out.print(temp.getDato() + " -> ");
            temp = temp.getSiguiente();
        }
        System.out.println("null");

        /*System.out.println("Recorrido hacia atrás:");
        temp = ultimo;
        while(temp != null){
            System.out.print(temp.getDato() + " -> ");
            temp = temp.getAnterior();
        }
        System.out.println("null");*/
    }
}
