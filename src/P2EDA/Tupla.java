package P2EDA;


public class Tupla<Id extends Comparable<Id>, In> {
    private Id identificador;
    private In informacion;

    public Tupla() {
    }

    public Tupla(Id identificador, In informacion) {
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
}
