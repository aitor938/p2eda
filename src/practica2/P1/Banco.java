package practica2.P1;

//Jose Luis Varo Guzmán - NIP: 697662
import java.io.*;
import java.util.Scanner;

class CCCId implements Comparable{
	private String ccc;
	
	public CCCId(){}
	
	public CCCId(String ccc){
		this.ccc = ccc;
	}
	
	public void setCCC(String ccc){
		this.ccc = ccc;
	}
	
	public String toString(){
		return ccc;
	}
	
	//Compara dos CCCIds de forma numérica
	public int compareTo(Object object){
		CCCId cccId = (CCCId)object;
		int id1 = Integer.parseInt(ccc);
		int id2 = Integer.parseInt(cccId.toString());
		if (id1 == id2){
			return 0;
		}else if(id1 > id2){
			return 1;
		}else{
			return -1;
		}
	}
}

//Jose Luis Varo Guzmán - NIP: 697662
class BankIn{
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nif;
	private float saldo;//No he encontrado un tipo "real" en Java,
						// supongo que te refieres a float.
						
	public BankIn(){}
	
	public BankIn(String nombre, String apellido1, String apellido2,
	  String nif, float saldo){
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nif = nif;
		this.saldo = saldo;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setApellido1(String apellido1){
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2){
		this.apellido2 = apellido2;
	}

	public void setNif(String nif){
		this.nif = nif;
	}
	
	public void setSaldo(float saldo){
		this.saldo = saldo;
	}
	
	public String toString(){
		return nombre + "; " + apellido1 + "; " + apellido2 + "; " 
		  + nif + "; " + Float.toString(saldo);
	}
	
}

//Jose Luis Varo Guzmán - NIP: 697662
public class Banco {
	
	private static ColeccionLista Lista = new ColeccionLista();
	private static final String INPUT_PATH = "entrada.txt";
	private static final String OUTPUT_PATH = "salida.txt";
	
	private static final String LEER_COMANDO = "";
	
	private static final String INSERTAR_CUENTA = "i";
	private static int DATOS_INSERTAR_CUENTA = 6;	
	private static String[] datosIC = new String[DATOS_INSERTAR_CUENTA];
	
	private static final String MODIFICAR_CUENTA = "m";
	private static int DATOS_MODIFICAR_CUENTA = 2;
	private static String[] datosMC = new String[
	  DATOS_MODIFICAR_CUENTA];	
	
	private static final String ELIMINAR_CUENTA = "e";
	private static int DATOS_ELIMINAR_CUENTA = 1;
	private static String[] datosEC = new String[DATOS_ELIMINAR_CUENTA];	
	
	private static final String BUSCAR_DATOS_CUENTA = "b";
	private static int DATOS_BUSCAR_DATOS_CUENTA = 1;
	private static String[] datosBDC = new String[
	  DATOS_BUSCAR_DATOS_CUENTA];	
	
	private static final String LISTAR_CUENTAS = "l";
	
	private static String comando = LEER_COMANDO;
	private static int contadorDatos = 0;
	
	public static void main (String args[]) {
		//Abre java a los archivos
		File inputFile = new File(INPUT_PATH);
		File outputFile = new File(OUTPUT_PATH);
		
		String line = "";
		String output;
		
		try {
			//Crea el de salida si falta
			if (!outputFile.exists()){
				System.out.println("No se ha encontrado "
				  + OUTPUT_PATH + ", preparando para crear");
				System.out.println("Creando " + OUTPUT_PATH);
				outputFile.createNewFile();
			}
			//Conecta lectores y escritores a los archivos
			Scanner scanner = new Scanner(inputFile);	
			PrintWriter writer = new PrintWriter(outputFile);
			
			//Lee y procesa
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				output = ejecutarCommando(line);
				System.out.print(output);
				writer.print(output);
			}
			
			//Cierra lectores y escritores
			scanner.close();
			writer.close();
			
		}catch(FileNotFoundException fnfe){
			if (!inputFile.exists()){
				System.out.println("No se ha encontrado " + INPUT_PATH);
			}
			if (!outputFile.exists()){
				System.out.println("No se ha encontrado " + OUTPUT_PATH);
			}
		}catch(IOException e){
			System.out.println("No se ha podido crear " + OUTPUT_PATH);
		}
	}
	
	//Si la línea leída es un comando.
	// En todo caso, procesamos la línea según el comando
	private static String ejecutarCommando(String line){
		String output = "";
		if(line.equals(INSERTAR_CUENTA)
		   || line.equals(MODIFICAR_CUENTA)
		   || line.equals(ELIMINAR_CUENTA)
		   || line.equals(BUSCAR_DATOS_CUENTA)
		   || line.equals(LISTAR_CUENTAS)){
			comando = line;
		}
		output = leerCommando(comando, line);
		
		return output;
	}
	
	//Si el comando procesa su propia línea, nos preparamos para leer
	// un número de líneas con datos. Si no, es un dato y lo procesamos.
	//Cuando hayamos leido y guardado todos los datos en un array,
	// interactuamos con Lista
	//Listar no requiere datos, y simplemente se ejecuta 
	private static String leerCommando(String commando, String line){
		String output = "";
		switch(comando){
			case INSERTAR_CUENTA:
				if (comando == line){
					output = "INSERCIóN";
					contadorDatos = DATOS_INSERTAR_CUENTA;
				} else {
					output = insertarCuenta(line);
				}
				break;
			
			case MODIFICAR_CUENTA:
				if (comando == line){
					output = "MODIFICACIóN";
					contadorDatos = DATOS_MODIFICAR_CUENTA;
				} else {
					output = modificarCuenta(line);
				}
				break;
			
			case ELIMINAR_CUENTA:
				if (comando == line){
					output = "ELIMINACIóN";
					contadorDatos = DATOS_ELIMINAR_CUENTA;
				} else {
					output = eliminarCuenta(line);
				}
				break;
			
			case BUSCAR_DATOS_CUENTA:
				if (comando == line){
					output = "BÚSQUEDA";
					contadorDatos = DATOS_BUSCAR_DATOS_CUENTA;
				} else {
					output = buscarDatosCuenta(line);
				}
				break;
			
			case LISTAR_CUENTAS:
				output = "LISTA: \n" + listarCuentas();
				break;
		}
		return output;
	}
	
	
	private static String insertarCuenta(String line){
		String output = "";
		datosIC[DATOS_INSERTAR_CUENTA - contadorDatos] = line;
		contadorDatos--;
		if (contadorDatos == 0){
			//Si no existe la cuenta con la CCCId especificada,
			// la insertamos
			CCCId id = new CCCId(datosIC[0]);
			BankIn in = new BankIn(datosIC[1], datosIC[2], datosIC[3],
			   datosIC[4], Float.parseFloat(datosIC[5]));
			if (Lista.meter(id, in)){
				output = ": " + id.toString() + "; " + in.toString()
				  + "\n";
			} else {
				output = " DESECHADA: " + id.toString() + "; "
				  + in.toString() + "\n";
			}
			comando = LEER_COMANDO;
		}
		return output;
	}
	
	
	private static String modificarCuenta(String line){
		String output = "";
		datosMC[DATOS_MODIFICAR_CUENTA - contadorDatos] = line;
		contadorDatos--;
		if (contadorDatos == 0){
			//Si existe la cuenta con la CCCId especificada, creamos los
			// datos modificados y los insertamos
			CCCId id = new CCCId(datosMC[0]);
			if (Lista.esta(id)){
				BankIn in = (BankIn)Lista.obtenerInformacion(id);
				in.setSaldo(Float.parseFloat(datosMC[1]));
				output = ": " + datosMC[0] + "; " + in.toString()
				  + "\n";
			}else{
				output = " DESECHADA: " + datosMC[0]  + "; "
				  + datosMC[1] + "\n";
			}
			comando = LEER_COMANDO;
		}
		return output;
	}
	
	
	private static String eliminarCuenta(String line){
		String output = "";
		datosEC[DATOS_ELIMINAR_CUENTA - contadorDatos] = line;
		contadorDatos--;
		if (contadorDatos == 0){
			//Si existe la cuenta con la CCCId especificada,
			// la eliminamos
			CCCId id = new CCCId(datosEC[0]);
			if (Lista.esta(id)){
				output = ": " + id.toString() + "; "
				  + Lista.obtenerInformacion(id).toString() + "\n";
				Lista.borrar(id);
			}else{	
				output = " DESECHADA: " + id.toString() + "\n";
			}
			comando = LEER_COMANDO;
		}
		return output;
	}
	
	
	private static String buscarDatosCuenta(String line){
		String output = "";
		datosBDC[DATOS_BUSCAR_DATOS_CUENTA - contadorDatos] = line;
		contadorDatos--;
		if (contadorDatos == 0){
			//Si existe la cuenta con la CCCId especificada, obtenemos
			// sus datos
			CCCId id = new CCCId(datosBDC[0]);
			if (Lista.esta(id)){
				output = " CON ÉXITO: " + id.toString() + "; "
				  + Lista.obtenerInformacion(id).toString() + "\n";
			}else{
				output = " SIN ÉXITO: " + id.toString();
			}
			comando = LEER_COMANDO;
		}
		return output;
	}

	
	private static String listarCuentas(){
		String output = Lista.listar();
		comando = LEER_COMANDO;
		return output;
	}
}

