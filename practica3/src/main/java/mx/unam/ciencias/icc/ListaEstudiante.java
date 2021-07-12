package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Estudiante get() {
			return elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
        if (elemento == null)
            return;
        Nodo n = new Nodo(elemento);
        longitud++;
        n.anterior = rabo;
        if (rabo == null)
            cabeza = n;
        else
            rabo.siguiente = n;
        rabo = n;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
        if (elemento == null)
            return;
        Nodo n = new Nodo(elemento);
        longitud++;
        n.siguiente = cabeza;
        if (cabeza == null)
            rabo = n;
        else
            cabeza.anterior = n;
        cabeza = n;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
        if (elemento == null)
            return;
        else if (i < 1)
            agregaInicio(elemento);
        else if (i > longitud - 1)
            agregaFinal(elemento);
        else {
            longitud++;
            Nodo n = new Nodo(elemento);
            Nodo s = getIesimo(i, 0, cabeza);
            n.anterior = s.anterior;
            s.anterior.siguiente = n;
            n.siguiente = s;
            s.anterior = n;
        }
    }

    private Nodo getIesimo(int i, int c, Nodo n) {
        if (c == i)
            return n;
        return getIesimo(i, c + 1, n.siguiente);
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Estudiante elemento) {
        Nodo n = buscaNodo(elemento, cabeza);
        if (n == null)
            return;
        eliminaNodo(n);
    }

    private Nodo buscaNodo(Estudiante elemento, Nodo n) {
        if (n == null)
            return n;
        return n.elemento.equals(elemento) ? n : buscaNodo(elemento, n.siguiente);
    }

    private void eliminaNodo(Nodo n) {
        longitud--;
        if (cabeza == rabo)
            cabeza = rabo = null;
        else if (n == cabeza) {
            n.siguiente.anterior = null;
            cabeza = n.siguiente;
        } else if (n == rabo) {
            n.anterior.siguiente = null;
            rabo = n.anterior;
        } else {
            n.siguiente.anterior = n.anterior;
            n.anterior.siguiente = n.siguiente;
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        if (cabeza == null)
            return null;
        Estudiante e = cabeza.elemento;
        eliminaNodo(cabeza);
        return e;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
        if (rabo == null)
            return null;
        Estudiante e = rabo.elemento;
        eliminaNodo(rabo);
        return e;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Estudiante elemento) {
        return buscaNodo(elemento, cabeza) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
        return reversa(new ListaEstudiante(), cabeza);
    }

    private ListaEstudiante reversa(ListaEstudiante l, Nodo n) {
        if (n == null)
            return l;
        l.agregaInicio(n.elemento);
        return reversa(l, n.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
        return copia(new ListaEstudiante(), cabeza);
    }

    private ListaEstudiante copia(ListaEstudiante l, Nodo n) {
        if (n == null)
            return l;
        l.agregaFinal(n.elemento);
        return copia(l, n.siguiente);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        if (cabeza == null)
            return null;
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        if (rabo == null)
            return null;
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        if (i < 0 || i >= longitud)
            return null;
        return getIesimo(i, 0, cabeza).elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Estudiante elemento) {
        return indiceDe(elemento, 0, cabeza);
    }

    private int indiceDe(Estudiante elemento, int i, Nodo n) {
        if (n == null)
            return -1;
        return n.elemento.equals(elemento) ? i : indiceDe(elemento, i + 1, n.siguiente);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        return String.format("[%s]", toString("", cabeza, 0));
    }

    private String toString(String s, Nodo n, int c) {
        if (n == null)
            return s;
        s += c != 0 ? ", " : "";
        return toString(s + n.elemento, n.siguiente, c + 1);
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
        if (lista == null)
            return false;
        else if (longitud != lista.longitud)
            return false;
        return equals(cabeza, lista.cabeza);
    }

    private boolean equals(Nodo n1, Nodo n2) {
        if (n1 == null)
            return true;
        return n1.elemento.equals(n2.elemento) ? equals(n1.siguiente, n2.siguiente) : false;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }
}
