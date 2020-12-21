import java.util.Arrays;
/**
 * La clase representa a una lista de 
 * números enteros
 * 
 * @author - Pedro J. Aquerreta
 * 
 */

public class ListaNumeros 
{
    // definir atributos
    int[] lista;
    int pos;
    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        pos = 0;
    }

    /**
     * Añade un valor siempre al principio de la lista
     * 
     * @param numero el valor que se añade. No se hace nada si la lista está
     *               completa
     * @return true si se ha podido añadir, false en otro caso
     */
    public boolean addElemento(int numero) {
        boolean posible = false;
        if(!estaCompleta()){
            System.arraycopy(lista, 0, lista, 1, pos);
            lista[0] = numero;
            pos++;
            posible = true;
        }
        return posible;
    }

    /**
     * devuelve true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos == lista.length;

    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. 
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;

    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * Representación textual de la lista de la forma indicada 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        String listStr = "";
        int aImprimir = 0;
        if(!estaVacia()){
            for(int linea = 0; linea <= 1; linea++){
                for(int i = 0; i < pos; i++){
                    if(linea == 0){
                        aImprimir = lista[i];
                    }
                    else{
                        aImprimir = i;
                    }
                    listStr += String.format("%8d", aImprimir);
                }
                listStr += "\n";
            }
        }
        return listStr;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     * Búsqueda lineal de numero en la lista
     * @param numero el nº a buscar
     * @return un array con las posiciones en las que se ha encontrado
     *  
     */
    public int[] buscarPosicionesDe(int numero) {
        int[] lineal = new int[pos];
        int posLineal = 0;
        for (int i = 0; i < pos; i++) {
            if (lista[i] == numero) {
                lineal[posLineal] = i;
                posLineal ++;
            }
        }
        return Arrays.copyOf(lineal, posLineal);

    }

    /**
     * Hace una búsqueda binaria del numero indicado devolviendo -1 si no se
     * encuentra o la posición en la que aparece
     * 
     * El array original lista no se modifica 
     * Para ello crea previamente una copia
     * de lista y trabaja con la copia
     * 
     * Usa exclusivamente métodos de la clase Arrays
     * 
     */
    public int buscarBinario(int numero) {
        int[] copia = Arrays.copyOf(lista, 0);
        Arrays.sort(copia);
        int index = Arrays.binarySearch(copia, numero);
        if(index < 0){
            index = -1;
        }
        return index;
    }

    /**
     * borra el primer elemento de la lista
     */
    public void borrarPrimero() {
        pos--;
        System.arraycopy(lista, 1, lista, 0, pos);

    }

    /**
     *  Invierte cada uno de los grupos de n elementos que hay en lista
     *  
     *  Si el nº de elementos en lista no es divisible entre n los elementos restantes 
     *  quedan igual
     *  
     *  (leer enunciado)
     *  
     */
    public void invertir(int n) {
        int[] copia = new int[pos];
        int posCopia = 0;
        for(int i = n; i < pos; i += n){
            for(int j = i - 1 ; j >= i - n; j--){
                copia[posCopia] = lista[j];
                posCopia++;
            }
        }
        System.arraycopy(copia, 0, lista, 0, posCopia);
    }

    /**
     * devuelve un ragged array de 2 dimensiones con tantas filas como valores
     * tenga el atributo lista y rellena el array de la forma indicada
     * (leer enunciado)
     * 
     */
    public int[][] toArray2D() {
        int[][] array2D = new int[pos][];
        for(int f = 0; f < pos; f++){
            array2D[f] = new int[f + 1];
            array2D[f][0] = 1;
            for(int c = 1; c < array2D[f].length - 1; c++){
                array2D[f][c] = array2D[f - 1][c] + array2D[f - 1][c - 1];
            }
            array2D[f][array2D[f].length - 1] = 1;
        }
        return array2D;
    }

    /**
     * Punto de entrada a la aplicación 
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) {
        ListaNumeros lista = new ListaNumeros(20);

        System.out.println("--- addElemento() y toString() -------");
        int[] valores = {21, -5, 6, -7, 21, -17, 21, 15, 22, 21, 77};
        for (int i = 0; i < valores.length; i++) {
            lista.addElemento(valores[i]);
        }
        System.out.println(lista.toString());

        System.out.println("--- buscarPosiciones() -------");
        int numero = 21;
        System.out.println(lista.toString());
        System.out.println("\t" + numero + " aparece en posiciones ");
        // seguir completando
        System.out.println(Arrays.toString(
                lista.buscarPosicionesDe(numero)));

        System.out.println("--- buscarBinario() -------");
        int numero2 = 45;
        System.out.println(lista.toString());
        System.out.print("\tEl numero " + numero2);
        if(lista.buscarBinario(numero2) < 0){
            System.out.print(" no");
        }
        System.out.println(" aparece en esta lista");

        System.out.println("--- invertir() -------");
        int inversor = 4;
        System.out.println(lista.toString());
        lista.invertir(inversor);
        System.out.println("\tLa lista invertida cada " + inversor +
            " es:");
        System.out.println(lista.toString());

        System.out.println("--- toArray2D() -------");
        for(int i = 0; i < lista.getTotalNumeros(); i++){
            System.out.println(Arrays.toString(lista.toArray2D()[i]));
        }
    }
}
