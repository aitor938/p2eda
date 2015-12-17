public interface Coleccion<Id extends Comparable<Id>, In> {

	public boolean meter(Id identificador, In informacion);
        
        public boolean estaElemento(Id identificador); //esta?
        
        public In obtenerInformacion(Id identificador);
        
        public boolean borrar(Id identificador);
        
        public int size(); //tamaño
        
        public boolean esVacia();
        
        public String listar();
        
        //public void iniciarIterador();
        
        //public boolean existeSiguiente();
        
        //public Tupla<Id,In> siguiente();
}
