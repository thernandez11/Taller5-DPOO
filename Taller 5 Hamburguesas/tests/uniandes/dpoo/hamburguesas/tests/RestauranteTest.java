package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;



public class RestauranteTest 
{
	private Restaurante restaurante1;
	
	@BeforeEach
    void setUp( ) throws Exception
    {	
    	restaurante1 = new Restaurante();
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testIniciarPedido() {
        try {
			restaurante1.iniciarPedido("Alejandro Gomez", "Su casa");
		} catch (YaHayUnPedidoEnCursoException e) {
			e.printStackTrace();
		}
        assertNotNull(restaurante1.getPedidoEnCurso(), "El pedido no fue iniciado de manera correcta.");
    }

    @Test
    void testIniciarPedidoException() throws YaHayUnPedidoEnCursoException {
        restaurante1.iniciarPedido("Alejandro Gomez", "Su casa");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante1.iniciarPedido("Juan Jose Lopez", "Otra casa");});
    }

    @Test
    void testCerrarYGuardarPedido() throws Exception {
        restaurante1.iniciarPedido("Alejandro Gomez", "Su casa");
        restaurante1.cerrarYGuardarPedido();

        File archivoFactura = new File("./facturas/factura_0.txt");
        assertTrue(archivoFactura.exists(), "El archivo de la factura no fue creado de la manera esperada.");
        archivoFactura.delete();
    }

    @Test
    void testcerrarYGuardarPedidoException() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante1.cerrarYGuardarPedido());
    }

    @Test
    void testCargarInformacionRestaurante() throws Exception {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoCombos = new File("data/combos.txt");
        File archivoMenu = new File("data/menu.txt");

        restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

        ArrayList<Ingrediente> ingredientes = restaurante1.getIngredientes();
        ArrayList<Combo> combos = restaurante1.getMenuCombos();
        ArrayList<ProductoMenu> menus = restaurante1.getMenuBase();
        assertNotNull(ingredientes, "La lista de ingredientes no debería ser null.");
        assertEquals(15, ingredientes.size(), "No se cargaron todos los ingredientes de manera correcta.");

        Ingrediente primerIngrediente = ingredientes.get(0);
        assertEquals("lechuga", primerIngrediente.getNombre(), "El primer ingrediente no es Lechuga.");
        assertEquals(1000, primerIngrediente.getCostoAdicional(), "El costo adicional del primer ingrediente no es correcto.");
        
        Combo primerCombo = combos.get(0);
        assertEquals("combo corral", primerCombo.getNombre(), "El primer combo no es combo corral.");
        
        ProductoMenu primerProducto = menus.get(0);
        assertEquals("corral", primerProducto.getNombre(), "El primer producto no es corral.");
        assertEquals(14000, primerProducto.getPrecio(), "El costo del primer producto no es el esperado.");
    }
    
    @Test
    void testCargarInformacionRestaurante_Number() throws Exception {
        File archivoIngredientes = new File("data/ingredientesIncorrectos.txt");
        File archivoCombos = new File("data/combos.txt");
        File archivoMenu = new File("data/menu.txt");

        assertThrows(NumberFormatException.class, () -> {restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);}, "Se esperaba que se lanzara excepcion.");
    }
    
    @Test
    void testCargarInformacionRestaurante_IngredienteRepetido() {
        File archivoIngredientes = new File("data/ingredientesRepetidos.txt");
        File archivoCombos = new File("data/combos.txt");
        File archivoMenu = new File("data/menu.txt");
        
        assertThrows(IngredienteRepetidoException.class, () -> {restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);}, "Se esperaba que se lanzara una excepcion.");
    }

    @Test
    void testCargarInformacionRestaurante_ProductoRepetido() {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoCombos = new File("data/combos.txt");
        File archivoMenu = new File("data/menusRepetidos.txt");

        assertThrows(ProductoRepetidoException.class, () -> {restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);}, "Se esperaba que se lanzara una excepcion.");
    }

    @Test
    void testCargarInformacionRestaurante_ComboRepetido() {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoCombos = new File("data/combosRepetidos.txt");
        File archivoMenu = new File("data/menu.txt");

        assertThrows(ProductoRepetidoException.class, () -> {restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);}, "Se esperaba que se lanzara una excepcion.");
    }
    
    @Test
    void testCargarInformacionRestaurante_ComboIncompleto() {
        File archivoIngredientes = new File("data/ingredientes.txt");
        File archivoCombos = new File("data/combosIncompletos.txt");
        File archivoMenu = new File("data/menu.txt");

        assertThrows(ProductoFaltanteException.class, () -> {restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);}, "Se esperaba que se lanzara una excepcion.");
    }

}
