public class ListaDoble {
    Nodo primero;
    Nodo ultimo;

    public void agregar(String dato, String genero) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setDato(dato);
        nuevo.setGenero(genero);

        if (primero == null) {
            primero = nuevo;
            ultimo = primero;
        } else {
            Nodo temp = ultimo;
            ultimo = nuevo;
            ultimo.setAnterior(temp);
            temp.setSiguiente(ultimo);
        }
    }

    // Método sobrecargado para compatibilidad
    public void agregar(String dato) {
        agregar(dato, "Desconocido");
    }

    // Método para eliminar por nodo (O(1))
    public void eliminar(Nodo nodo) {
        if (nodo == null) return;

        if (nodo == primero) {
            primero = primero.getSiguiente();
            if (primero != null) {
                primero.setAnterior(null);
            } else {
                ultimo = null;
            }
        } else if (nodo == ultimo) {
            ultimo = ultimo.getAnterior();
            if (ultimo != null) {
                ultimo.setSiguiente(null);
            } else {
                primero = null;
            }
        } else {
            Nodo anterior = nodo.getAnterior();
            Nodo siguiente = nodo.getSiguiente();
            anterior.setSiguiente(siguiente);
            if (siguiente != null) {
                siguiente.setAnterior(anterior);
            }
        }
    }

    // Método para eliminar por dato (O(N) - para compatibilidad)
    public void eliminar(String dato) {
        Nodo nodo = buscarPorId(extraerId(dato));
        if (nodo != null) {
            eliminar(nodo);
        }
    }

    // Método para buscar por ID y devolver el nodo (O(N))
    public Nodo buscarPorId(String id) {
        Nodo temp = primero;
        while (temp != null) {
            if (temp.getDato().startsWith(id + " - ")) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    // Método auxiliar para extraer ID del formato "00001 - Título"
    private String extraerId(String libroCompleto) {
        if (libroCompleto == null) return "";
        String[] partes = libroCompleto.split(" - ", 2);
        return partes.length > 0 ? partes[0].trim() : "";
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
