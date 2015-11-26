package P2EDA;


public class Elemento<Id extends Comparable<Id>, In> {

    private Id identificador;
    private In informacion;
    private Elemento<Id, In> izquierda; //puntero que enlaca con el siguiente
    private Elemento<Id, In> derecha; 

    public Elemento() {
    }

    public Elemento(Id identificador, In informacion) {
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

    public boolean esMayor (Elemento<Id,In> elemento){
        if(identificador.compareTo(elemento.getIdentificador()) > 0){
            return true;
        }
        return false;
    }

    public Elemento<Id, In> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Elemento<Id, In> izquierda) {
        this.izquierda = izquierda;
    }

    public Elemento<Id, In> getDerecha() {
        return derecha;
    }

    public void setDerecha(Elemento<Id, In> derecha) {
        this.derecha = derecha;
    }
}
