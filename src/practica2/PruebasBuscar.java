
package practica2;

import java.util.Random;
import practica2.P1.ColeccionLista;

/**
 * @author Jose Luis Varo Guzmán - NIP: 697662
 * @author Aitor Arcos Almazaán - NIP: 705303
 */
public class PruebasBuscar {

    //metodo que nos hace las baterías de búsquedas de 10000 identificadores;
    //nos busca 10000 veces los 10000 identificadoes aleatorios distintos generados
    private static void buscarEImprimir(ColeccionLista lista,
            ColeccionAbb<Integer, Integer> arbol, Random aleatorio) {
        long tStart;
        long tEnd;
        long tLista;
        int numAl;
        
        //para el arbol
        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numAl = aleatorio.nextInt(100000);
            arbol.obtenerInformacion(numAl);
        }
        tEnd = System.currentTimeMillis();
        tLista = tEnd - tStart;
        System.out.println("Tiempo para "+ arbol.getClass() +":\t" + tLista + " milisegundos.");

        //para la lista
        tStart = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numAl = aleatorio.nextInt(100000);
            lista.obtenerInformacion(numAl);
        }
        tEnd = System.currentTimeMillis();
        tLista = tEnd - tStart;
        System.out.println("Tiempo para "+ lista.getClass() +" :\t" + tLista + " milisegundos.");

    }

    public static void main(String args[]) {
        ColeccionLista lista = new ColeccionLista();
        ColeccionAbb<Integer, Integer> arbol = new ColeccionAbb<>();

        int numAl;
        Random aleatorio = new Random();

        //introducimos los datos aleatorios
        
        System.out.println("Introduciendo 1000 elementos en colecciones...");
        for (int i = 0; i < 1000; i++) {
            numAl = aleatorio.nextInt(100000);
            arbol.meter(numAl, numAl);
            lista.meter(numAl, numAl);
        }

        buscarEImprimir(lista, arbol, aleatorio);
        
       
        System.out.println("Introduciendo 10000 elementos en colecciones...");
        for (int i = 0; i < 9000; i++) { //son 9000 porque antes ya hemos introducido 1000
            numAl = aleatorio.nextInt(100000);
            arbol.meter(numAl, numAl);
            lista.meter(numAl, numAl);
        }
        buscarEImprimir(lista, arbol, aleatorio);
        
        System.out.println("Introduciendo 100000 elementos en colecciones...");
        for (int i = 0; i < 90000; i++) { //son 90000 porque antes ya hemos introducido 10000
            numAl = aleatorio.nextInt(100000);
            arbol.meter(numAl, numAl);
            lista.meter(numAl, numAl);
        }
        buscarEImprimir(lista, arbol, aleatorio);
    }
}
