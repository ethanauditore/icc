package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre   = nombre;
        this.cuenta   = cuenta;
        this.promedio = promedio;
        this.edad     = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        return cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        return String.format("Nombre   : %s\n" +
                             "Cuenta   : %09d\n" +
                             "Promedio : %2.2f\n" +
                             "Edad     : %d",
                             nombre, cuenta, promedio, edad);}

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        return nombre.equals(estudiante.nombre)
            && cuenta == estudiante.cuenta
            && promedio == estudiante.promedio
            && edad == estudiante.edad;
    }

    /**
     * Regresa el estudiante serializado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deserializa}.
     * @return la serialización del estudiante en una línea de texto.
     */
    @Override public String serializa() {
        return String.format("%s\t%d\t%2.2f\t%d\n",
                             nombre, cuenta, promedio, edad);
    }

    /**
     * Deserializa una línea de texto en las propiedades del estudiante. La
     * serialización producida por el método {@link Estudiante#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de un estudiante.
     */
    @Override public void deserializa(String linea) {
        if (linea == null || linea.isEmpty())
            throw new ExcepcionLineaInvalida();
        linea = linea.trim();
        String[] s = linea.split("\t");
        if (s.length != 4)
            throw new ExcepcionLineaInvalida();
        nombre = s[0];
        try {
            cuenta = Integer.valueOf(s[1]);
            promedio = Double.valueOf(s[2]);
            edad = Integer.valueOf(s[3]);
        } catch (NumberFormatException nfe) {
            throw new ExcepcionLineaInvalida();
        }
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    @Override public void actualiza(Estudiante estudiante) {
        if (estudiante == null)
            throw new IllegalArgumentException();
        this.nombre = estudiante.nombre;
        this.cuenta = estudiante.cuenta;
        this.promedio = estudiante.promedio;
        this.edad = estudiante.edad;
    }

    /**
     * Nos dice si el estudiante caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoEstudiante campo, Object valor) {
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException();
        CampoEstudiante c = (CampoEstudiante) campo;
        switch (c) {
        case NOMBRE:
            return cazaNombre(valor);
        case CUENTA:
            return cazaCuenta(valor);
        case PROMEDIO:
            return cazaPromedio(valor);
        case EDAD:
            return cazaEdad(valor);
        default:
            return false;
        }
    }

    private boolean cazaNombre(Object valor) {
        if (!(valor instanceof String))
            return false;
        String s = (String) valor;
        return !s.isEmpty() && nombre.indexOf(s) != -1;
    }

    private boolean cazaCuenta(Object valor) {
        if (!(valor instanceof Integer))
            return false;
        Integer n = (Integer) valor;
        return n <= cuenta;
    }

    private boolean cazaPromedio(Object valor) {
        if (!(valor instanceof Double))
            return false;
        Double n = (Double) valor;
        return n <= promedio;
    }

    private boolean cazaEdad(Object valor) {
        if (!(valor instanceof Integer))
            return false;
        Integer n = (Integer) valor;
        return n <= edad;
    }
}
