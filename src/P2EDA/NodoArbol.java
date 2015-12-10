
package P2EDA;


public class NodoArbol<Id extends Comparable<Id>, In> {

    private Id identificador;
    private In informacion;
    private NodoArbol<Id, In> izquierda; //puntero que enlaca con el siguiente
    private NodoArbol<Id, In> derecha; 

    public NodoArbol() {
    }

    public NodoArbol(Id identificador, In informacion) {
        this.identificador = identificador;
        this.informacion = informacion;
    }

    public Id getIdentificador() {
        return identificador;
    }
   
    public void setIdentificador(Id identificador) {
        this.identificador = identificador;
    }

    public In getInformacion() {
        return informacion;
    }

    public void setInformacion(In informacion) {
        this.informacion = informacion;
    }

    public boolean esMayor (NodoArbol<Id,In> elemento){
        if(identificador.compareTo(elemento.getIdentificador()) > 0){
            return true;
        }
        return false;
    }

    public NodoArbol<Id, In> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol<Id, In> izquierda) {
        this.izquierda = izquierda;
    }

    public NodoArbol<Id, In> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol<Id, In> derecha) {
        this.derecha = derecha;
    }
}
