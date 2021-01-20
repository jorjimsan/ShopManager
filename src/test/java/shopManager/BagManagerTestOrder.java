package shopManager;

import shopmanager.*;

import static org.junit.jupiter.api.Assertions.*;


import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import exceptions.NoEnoughStock;
import exceptions.NotInStock;
import exceptions.UnknownRepo;
import model.Product;
import model.Order;
import persistency.OrderRepository;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class BagManagerTestOrder {

	// Mensanjes
	private static Logger trazador=Logger.getLogger(ProductTest.class.getName());

	//Creo los objetos sustitutos (representantes o mocks)
	//Son objetos contenidos en MyBagManager de los que aún no disponemos el código
	@Mock(serializable = true)
	private static Product producto1Mock= Mockito.mock(Product.class);
	@Mock(serializable = true)
	private static Product producto2Mock= Mockito.mock(Product.class);
	@Mock
	private static StockManager stockMock= Mockito.mock(StockManager.class);
	@Mock 
	private static OrderRepository repositoryMock= Mockito.mock(OrderRepository.class);
	@Mock
	private static Order orderMock=Mockito.mock(Order.class);
	
	//Inyección de dependencias
	//Los objetos contenidos en micestaTesteada son reemplazados automáticamente por los sustitutos (mocks)
	@InjectMocks
	private static MyBagManager micestaTesteada;
	
	/**
	 * @see BeforeEach {@link org.junit.jupiter.api.BeforeEach}
	 */
	
	@BeforeEach
	void setUpBeforeClass(){
		//Todos los tests empiezan con la bolsa vacÃƒÂ¯Ã‚Â¿Ã‚Â½a
		
		   micestaTesteada.reset();

	}
	
	@Test
	@Tag("unidad")
	@DisplayName("Prueba del metodo que asienta el pedido")
	void testOrder() throws NoEnoughStock, NotInStock, UnknownRepo {
		
		// Comienzo del Test
		trazador.info("Comienza el test de order");
		
		// El procedimiento rellenaCesta mete dos productos (mocks) en la cesta
		rellenaCesta();

		// Invoco al método order (esto creará un objeto de tipo order, por tanto no es puramente un test unitario)
		micestaTesteada.order();
		
		// Obtiene el id unívoco del primer pedido
		String idPedido1 = micestaTesteada.order().getId();
		
		// VERIFICAMOS QUE EL ID DEVUELTO DEL PRIMER PEDIDO NO ES NULL
		
		// Comprobamos que el string devuelto no es null
		assertNotNull(idPedido1,"El ID devuelto es Null");
		
		// VERIFICA QUE SE BORRA LA CESTA AL EFECTUAR EL PEDIDO CON ORDER:
		
		// Tras ejecutar order() la cesta no debe contener ninguno de los productos "id1" e "id2" que 
		// se añadieron en rellenaCesta() para efectuar el pedido
		assertTrue(micestaTesteada.findProduct("id1").isEmpty(),"La cesta contiene un producto con id1 que se ha eliminado");
		assertTrue(micestaTesteada.findProduct("id2").isEmpty(),"La cesta contiene un producto con id2 que se ha eliminado");

		// NUEVO PEDIDO PARA PROBAR QUE SE GESTIONA UN ID UNIVOCO PARA CADA PEDIDO, LLENAMOS LA CESTA

		// Vaciamos la cesta antes del nuevo pedido
		micestaTesteada.reset();
		
		// El procedimiento rellenaCesta mete dos productos (mocks) en la cesta
		rellenaCesta();

		// Invoco al método order (esto creará un objeto de tipo order, por tanto no es puramente un test unitario)
		micestaTesteada.order();

		// Obtiene el id univoco del segundo pedido
		String idPedido2 = micestaTesteada.order().getId();
		
		// VERIFICAMOS QUE EL ID DEVUELTO DEL PRIMER PEDIDO NO ES NULL
		// Comprobamos que el string devuelto no es null
		assertNotNull(idPedido2,"El ID devuelto es Null");
		
		// VERIFICA QUE SE BORRA LA CESTA AL EFECTUAR EL PEDIDO CON ORDER:
		
		// Tras ejecutar order() la cesta no debe contener ninguno de los productos "id1" e "id2" que 
		// se añadieron en rellenaCesta() para efectuar el pedido
		assertTrue(micestaTesteada.findProduct("id1").isEmpty(),"La cesta contiene un producto con id1 que se ha eliminado");
		assertTrue(micestaTesteada.findProduct("id2").isEmpty(),"La cesta contiene un producto con id2 que se ha eliminado");
		
		// COMPARAMOS QUE LOS ID DE LOS DIFERENTES PEDIDOS NO SON IGUALES
		assertNotEquals(idPedido1, idPedido2, "No asigna id univoco a cada pedido");
		
	}
	
	/**
	 * Rellena una cesta con los dos mocks declarados al inicio
	 * @throws NoEnoughStock Si no hay suficiente stock del producto a aÃƒÂ¯Ã‚Â¿Ã‚Â½adir
	 * @throws NotInStock Si no existe el producto en el stock
	 */
	void rellenaCesta() throws NoEnoughStock, NotInStock {
		Mockito.when(producto1Mock.getId()).thenReturn("id1");
		Mockito.when(producto1Mock.getNumber()).thenReturn(1);
		Mockito.when(producto2Mock.getId()).thenReturn("id2");
		Mockito.when(producto2Mock.getNumber()).thenReturn(2);
		micestaTesteada.addProduct(producto1Mock);
		micestaTesteada.addProduct(producto2Mock);
	}

}
