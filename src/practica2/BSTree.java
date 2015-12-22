
package practica2;

/**
 * @author Jose Luis Varo Guzmán - NIP: 697662
 * @author Aitor Arcos Almazaán - NIP: 705303
 */

//====================================================================//
//======================== BINARY SEARCH TREE ========================//
//====================================================================//
public class BSTree<E extends Comparable<E>> {

	//================================================================//
	private E e;                    //Elemento generico del arbol
	private BSTree<E> left = null;  //Arbol con elementos menores
	private BSTree<E> right = null; //Arbol con elementos mayores
	private int height = 0;         //Altura del arbol

	//!!--------------------------------------------------------------!!
	//!!-| Estas constantes son para mayor legibilidad; |-------------!!
	//!!-|  no deberias cambiar sus valores.			|-------------!!

	 public static final boolean ADD_IS_EXCLUSIVE = false;
	 public static final boolean ADD_IS_NON_EXCLUSIVE = false;
	 public static final boolean DELETE_SAVES_CHILDREN = true;
	 public static final boolean DELETE_DISCARDS_CHILDREN = true;
	 public static final boolean GIVE_PARENT_IF_NOT_FOUND = true;
	 public static final int NO_HEIGHT = -1;

	 //Vendria bien poder conectar estas tres a las constantes de
	 // 'Comparable' o algo.
	 private static final int LESSER_THAN = -1;
	 private static final int EQUAL = 0;
	 private static final int GREATER_THAN = 1;
	 //En caso de que por lo menos uno de ellos sea null
	 private static final int NOT_COMPARABLE = 2;

	//!!--------------------------------------------------------------!!
	//================================================================//

	public BSTree() {}

	public BSTree(E e) {
		this.e = e;
	}

	//----------------------------------------------------------------//
	//-- Meter un elemento en el arbol -------------------------------//
	/*
	 * Mediante add(...), meter un 'BSTree' en este 'BSTree'.
	 * Devuelve 'true' en caso de tener exito, y 'false' en caso
	 *  contrario:
	 *
	 * 		add(E)
	 * 		add(BSTree<E>)
	 *
	 * 		add(E, boolean)
	 * 		add(BSTree<E>, boolean)
	 *
	 * Todos los 'add(...)' llaman a 'add(BSTree<E>, boolean)', y
	 *  'add(BSTree<E>, boolean)' llama a 'addNewTree(BSTree)'.
	 * 'add(E, ...)' crea un nuevo 'BSTree<E>' a partir del 'E'.
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
	 *  introducir (y la exclusividad sea 'true'), la funcion fallara y
	 *  devolvera false.
	 */

	public boolean add(E e) {
		return add(new BSTree<E>(e), ADD_IS_NON_EXCLUSIVE);
	}

	public boolean add(E e, boolean beExclusive) {
		return add(new BSTree<E>(e), beExclusive);
	}

	public boolean add(BSTree<E> newTree) {
		return add(newTree, ADD_IS_NON_EXCLUSIVE);
	}

	public boolean add(BSTree<E> newTree, boolean beExclusive) {
		if ((beExclusive && !isThere(newTree)) || !beExclusive
		 && newTree != null) {
			if (newTree.getE() != null){
				addNewTree(newTree);
                               
				return true;
			}
		}
		return false;
	}

	private boolean addNewTree(BSTree<E> newTree) {
		BSTree<E> parentTree = findTree(newTree.getE(),
		 GIVE_PARENT_IF_NOT_FOUND);
                
		//Arbol vacio => lo rellenamos
		if (getE() == null) {
			setE(newTree.getE());
			add(newTree.getLeft());
			add(newTree.getRight());
			return true;
		} else if(parentTree != null){
			//Padre mayor o igual que nuevo arbol => Izquierda
			if (parentTree.compare(newTree) == GREATER_THAN
			 || parentTree.compare(newTree) == EQUAL) {
				parentTree.setLeft(newTree);
			}

			//Padre menor que nuevo arbol => Derecha
			if (parentTree.compare(newTree) == LESSER_THAN) {
				parentTree.setRight(newTree);
			}

			//La nueva altura es la del padre +1
			height = parentTree.getHeight() + 1;
			return true;
		}
		return false;
	}

	//----------------------------------------------------------------//
	//-- Borrar un elemento en el arbol ------------------------------//
	/*
	 * Mediante delete(...), borrar un BSTree<E> de la estructura y,
	 *  en el proceso, se puede reintroducir o borrar los hijos del nodo
	 *  eliminado.
	 * Devuelve 'true' en caso de tener exito, y 'false' en caso
	 *  contrario:
	 *
	 * 		delete(E)
	 * 		delete(BSTree<E>)
	 * 		delete(E, boolean)
	 * 		delete(BSTree<E>, boolean)
	 *
	 * Todos los 'delete(...)' acaban llamando a 'delete(BSTree<E>,
	 *  boolean)'.
	 *
	 * El boolean indica si debemos guardar los hijos del arbol a
	 *  eliminar, o si debemos eliminarlos junto con nuestro arbol.
	 */

	public boolean delete(E e) {
		return delete(findTree(e), DELETE_SAVES_CHILDREN);
	}

	public boolean delete(E e, boolean keepChildren) {
		return delete(findTree(e), keepChildren);
	}

	public boolean delete(BSTree<E> tree) {
		return delete(tree, DELETE_SAVES_CHILDREN);
	}

	public boolean delete(BSTree<E> tree, boolean keepChildren) {
		if (isThere(tree)){
			//Guardamos los hijos, nos preparamos para guardar el padre
			BSTree<E> parent;
			BSTree<E> left = tree.getLeft();
			BSTree<E> right = tree.getRight();

			//Cortamos lazos con los hijos
			tree.setLeft(null);
			tree.setRight(null);

			//Si tenemos padre, que corte lazos con nosotros
			if (tree.getHeight() > 0){
				//Nos buscamos a nosotros mismos, pero nos detenemos
				// una altura antes de encontrarnos. Este sera nuestro
				// padre
				parent = findTree(tree.getE(), tree.getHeight() - 1);

					if (parent.getLeft() != null){
						if(parent.getLeft().compare(tree)
						 == LESSER_THAN){
							parent.setLeft(null);
						}
					} else if(parent.getRight() != null){
						if(parent.getRight().compare(tree) == EQUAL){
							parent.setRight(null);
						}
					}

			//Si no, nos vaciamos, y hacemos de padre
			} else {
				tree.setE(null);
				parent = tree;
			}

			//Los hijos se asignan al padre (nosotros pero vacío si no
			// teníamos padre)
			if (keepChildren){
				parent.add(left);
				parent.add(right);
			}

			return true;
		}
		return false;
	}

	//----------------------------------------------------------------//
	//-- Buscar un elemento ------------------------------------------//
	/*
	 * Mediante findTree(...), encontrar y devolver un 'BSTree' con un
	 *  valor 'E'. Devuelve 'null' si no se encuentra al arbol:
	 *
	 * 		findTree(E)
	 * 		findTree(E, boolean)
	 *  	findTree(E, int)
	 *  	findTree(E, boolean, int)
	 *
	 * Todas las instancias 'findTree(...)' acaban llamando a
	 *  'findTree(E, boolean, int)', el cual a su vez llama a
	 *  'search(E)'.
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
	 * El valor del int indica si, durante la busqueda, debemos
	 *  detenernos a una altura. Si no se especifica al llamar a
	 *  findTree(...), el valor por defecto es -1, por lo que se busca
	 *  de forma normal. delete(...) hace uso de esto.
	 *
	 * Mediante 'isThere(...)' saber si existe ya, o no, un 'BSTree' con
	 *  un valor 'E'. Devuelve 'true' si 'findTree(E)' devuelve un
	 *  'BSTree', y 'false' en caso de devolver 'null':
	 *
	 * 		isThere(E)
	 * 		isThere(BSTree)
	 */

	public BSTree<E> findTree(E e) {
		return findTree(e, !GIVE_PARENT_IF_NOT_FOUND, NO_HEIGHT);
	}

	public BSTree<E> findTree(E e, int searchHeight) {
		return findTree(e, !GIVE_PARENT_IF_NOT_FOUND, searchHeight);
	}

	public BSTree<E> findTree(E e, boolean areParentsFineToo) {
		return findTree(e, areParentsFineToo, NO_HEIGHT);
	}

	public BSTree<E> findTree(E e, boolean areParentsFineToo, int searchHeight) {
		BSTree<E> tree;
		if (areParentsFineToo) {
			return search(e, searchHeight);
		} else {
			tree = search(e, searchHeight);
			if (tree.compare(e) == EQUAL) {
					return tree;
			}
		}
		return null;
	}

	private BSTree<E> search(E e, int searchHeight) {
		if (searchHeight >= getHeight() || searchHeight == NO_HEIGHT){
			if (e == null) {return null;}

			if (getLeft() != null) {
				if (this.compare(e) == GREATER_THAN) {
					return getLeft().search(e, searchHeight);
				}
			}

			if (getRight() != null) {
				if (this.compare(e) == LESSER_THAN) {
					return getRight().search(e, searchHeight);
				}
			}
		}
		return this;
	}

	public boolean isThere(E e) {
		return findTree(e) != null;
	}

	public boolean isThere(BSTree<E> tree) {
		return findTree(tree.getE()) != null;
	}

	//----------------------------------------------------------------//
	//-- Comparar un elemento ----------------------------------------//
	/*
	 *  Mediante compare(...), devolver el valor devuelto por compareTo
	 *  (suponiendo que sigue el estandar, deberia ser '-1', '0' o '1')
	 *
	 * 		compare(BSTree<E>)
	 * 		compare(BSTree<E>, BSTree<E>)
	 * 		compare(E)
	 * 		compare(E, E)
	 *
	 * Todas las funciones acaban llamando a 'compare(E, E)'
	 *
	 */

	protected int compare(BSTree<E> t) {
		return compare(this.e, t.getE());
	}

	protected int compare(BSTree<E> t1, BSTree<E> t2) {
		return compare(t1.getE(), t2.getE());
	}

	protected int compare(E e) {
		return compare(this.e, e);
	}

	protected int compare(E e1, E e2) {
		if (e1 != null && e2 != null){
			return e1.compareTo(e2);
		}
		return NOT_COMPARABLE;
	}

	//----------------------------------------------------------------//
	//-- Funciones I/O -----------------------------------------------//
	/*
	 * Introducir y obtener datos del BSTree
	 *
	 *  	getLeft()
	 * 		setLeft(BSTree<E>)
	 *
	 *  	getRight()
	 *  	setRight(BSTree<E>)
	 *
	 *  	getE()
	 *  	setE(E)
	 *
	 *  	getHeight()
	 *
	 */

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

	public int getHeight() {
		return height;
	}
}
