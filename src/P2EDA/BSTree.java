
//====================================================================//
//======================== BINARY SEARCH TREE ========================//
//====================================================================//

public class BSTree<E extends Comparable<E>>{

	//================================================================//
	
	private E e;					//Elemento generico del arbol
    private BSTree<E> left = null;  //Arbol con elementos menores
    private BSTree<E> right = null; //Arbol con elementos mayores 
    private int height = 0;			//Altura del arbol

	//!!--------------------------------------------------------------!!
	//!!-| Estas constantes son para mayor legibilidad; |-------------!!
	//!!-|  no deberias cambiar sus valores.            |-------------!!
	
	
	public static final boolean GIVE_PARENT_IF_NOT_FOUND = true;
	public static final boolean NON_EXCLUSIVE = false;
	
	//Vendria bien poder conectar estas tres a las constantes de
	// 'Comparable' o algo.
	private static final int LESSER_THAN   = -1;
	private static final int EQUAL		   =  0;
	private static final int GREATER_THAN  =  1;
	
	//!!--------------------------------------------------------------!!

	//================================================================//

    public BSTree() {}
    
    public BSTree(E e){
		this.e = e;
	}

	//----------------------------------------------------------------//
	//-- Meter un elemento en el arbol -------------------------------//

	/* Mediante add(...), meter un 'BSTree' en este 'BSTree'.
	 * Devuelve 'true' en caso de tener exito, y 'false' en caso
	 *  contrario:
	 * 
	 * 		add(E)
	 * 		add(BSTree)
	 * 
	 * 		add(E, boolean)
	 * 		add(BSTree, boolean)
	 * 
	 * Todos los 'add(...)' llaman a 'add(BSTree, boolean)', y
	 *  'add(BSTree, boolean)' llama a 'addNewTree(BSTree)'. 
	 * 'add(E, ...)' crea un nuevo 'BSTree' a partir del 'E'.
	 * 
	 * 
	 * El valor del 'boolean' indica si hay exclusividad, es decir, si
	 *  un valor puede o no repetirse en nuestro arbol:
	 * 
	 * 		'false' => Pueden repetirse 
	 * 		'true'  => No pueden repetirse
	 * 
	 * El valor es 'false' si no se especifica al llamar a la funcion.
	 * En caso de que se encuentre el mismo valor que se intentaba
	 *  introducir (y la exclusividad sea 'true'), la funcion fallara¡ y
	 *  devolvera false.
	 */

	public boolean add(E e){
		return add(new BSTree<E>(e), NON_EXCLUSIVE);
	} 
	public boolean add(E e, boolean beExclusive){
		return add(new BSTree<E>(e), beExclusive);
	}

	public boolean add(BSTree<E> newTree){
		return add(newTree, NON_EXCLUSIVE);
	}

	public boolean add(BSTree<E> newTree, boolean beExclusive){
		if ((beExclusive && !isThere(newTree)) || !beExclusive){
			addNewTree(newTree);
			return true;
		}
		return false;
	}

	private int addNewTree(BSTree<E> newTree){
		//!!--WIP--!!//
		//BSTree<E> parentTree = findTree(newTree.getE(),
		// GIVE_PARENT_IF_NOT_FOUND);
		
		//if parentTree == null
		 
		 
		return 0;
	}

	//----------------------------------------------------------------//
	//-- Buscar un elemento ------------------------------------------//

	/* Mediante findTree(...), encontrar y devolver un 'BSTree' con un
	 *  valor 'E'. Devuelve 'null' si no se encuentra al arbol:
	 * 
	 * 		findTree(E)
	 * 		findTree(E, boolean)
	 * 
	 * 'findTree(E)' llama a 'findTree(E, boolean)', el cual a su vez
	 *  llama a 'search(E)'.
	 * 
	 * El valor del 'boolean' indica si, en caso de no encontrar el
	 *  'BSTree', se debe o no devolver el 'BSTree' que seria su padre:
	 * 
	 * 		'true'  => Devolver padre si no se encuentra el nodo
	 * 		'false' => Devolver null normalmente
	 * 
	 * El valor es 'false' si no se especifica al llamar la funcion.
	 * Esto se utiliza en 'addNewTree()'.
	 * 
	 * Mediante 'isThere(...)' saber si existe ya, o no, un 'BSTree' con
	 *  un valor 'E'. Devuelve 'true' si 'findTree(E)' devuelve un
	 *  'BSTree', y 'false' en caso de devolver 'null':
	 * 
	 * 		isThere(E)
	 * 		isThere(BSTree)
	 */

	public BSTree<E> findTree(E e){
		return findTree(e, !GIVE_PARENT_IF_NOT_FOUND);
	}
	
	public BSTree<E> findTree(E e, boolean areParentsFineToo){
		BSTree<E> tree;
		if (areParentsFineToo){
			return search(e);
		}else{
			tree = search(e);
			if (tree.compare(e) == EQUAL){
				return tree;
			}
		}
		return null;
	}

	private BSTree<E> search(E e){
		if (e == null){
			return null;
		} else if (getLeft() != null){
			if (compare(e) == GREATER_THAN){
				return getLeft().search(e);
			}
		} else if (getRight() != null) {
			if (compare(e) == LESSER_THAN){
				return getRight().search(e);
			}
		}
		return this;
	}
	
	public boolean isThere(E e){
		return findTree(e) != null;
	}

	public boolean isThere(BSTree<E> tree){
		return findTree(tree.getE()) != null;
	}


	//----------------------------------------------------------------//

	public int compare(E e){
		return this.e.compareTo(e);
	}

    public BSTree<E> getLeft() {
        return left;
    }

    public void setLeft(BSTree<E> left) {
        this.left = left;
    }

    public BSTree<E> getRight() {
        return right;
    }

    public void setRight(BSTree<E> right) {
        this.right = right;
    }
    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
    
    public int getHeight(){
		return height;
	}
}
