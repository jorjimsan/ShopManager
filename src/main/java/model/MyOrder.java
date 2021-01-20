/**
 * 
 */
package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * @author Isabel Román, Jorge Jiménez-Sánchez
 */
public class MyOrder implements Order {

	
	/**
	 * Objeto tipo logger para gestionar los mensajes durante la ejecución
	 */
	private static Logger trazador=Logger.getLogger(MyProduct.class.getName());	
	/**
	 * Identificador del producto
	 */
	private String id;
	
	
	/**
	 * 
	 */
	public MyOrder() {
	}

	@Override
	public void setId(String id) {
		String msg="Estableciendo id a "+id;
    	trazador.info(msg);
        this.id=id;
	}

	@Override
	public String getId() {
    	String msg="Devolviendo el id como "+id;
    	trazador.info(msg);
    	return this.id;
	}

	@Override
	public void setProducts(Iterator<Product> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProducts(Collection<Product> products) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Product> getProductsById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Product> getProductsByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Product> getProductsByUnits() {
		// TODO Auto-generated method stub
		return null;
	}

}
