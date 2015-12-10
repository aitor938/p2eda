package P2EDA;

public class ColeccionAbb<Id extends Comparable<Id>, In> implements Coleccion<Id, In> {

    private NodoArbol<Id,In> raiz;
    private int size;
    private NodoArbol<Id,In> iterador;
    
    @Override
    public boolean meter(Id identificador, In informacion) {
        NodoArbol<Id,In> elemento = new NodoArbol<>(identificador, informacion);
        if (raiz == null){
            raiz = elemento;
        } else {
            if (raiz.esMayor(elemento)){
                meterRec(raiz.getDerecha(),elemento,raiz);
            } else {
                
            }
        }
        return false;
    }
    
    private boolean meterRec(NodoArbol<Id,In> actual, NodoArbol<Id,In> elemento,NodoArbol<Id,In> anterior){ //el que era el actial ahora es el anterior
        
        return false;
    }

    @Override
    public boolean estaElemento(Id identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public In obtenerInformacion(Id identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(Id identificador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean esVacia() {
        return raiz == null;
    }

    @Override
    public String listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciarIterador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tupla<Id, In> siguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
