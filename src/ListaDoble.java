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

    // Elimina el nodo 0(1)
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

    // Busca por titulo y devuelve nodo 0(N) creo
    public Nodo buscarPorTitulo(String titulo) {
        Nodo temp = primero;
        while (temp != null) {
            String tituloActual = extraerTitulo(temp.getDato());
            if (tituloActual.equalsIgnoreCase(titulo)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    // Extraer titulo
    private String extraerTitulo(String libroCompleto) {
        if (libroCompleto == null) return "";
        String[] partes = libroCompleto.split(" - ", 2);
        return partes.length == 2 ? partes[1].trim() : libroCompleto;
    }

    // Muestra libros disponibles (nuevo)
    public void imprimirSoloTitulos() {
        if (primero == null) {
            System.out.println("  (vacio)");
            return;
        }

        Nodo temp = primero;
        while (temp != null) {
            String titulo = extraerTitulo(temp.getDato());
            System.out.println("  " + titulo);
            temp = temp.getSiguiente();
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
