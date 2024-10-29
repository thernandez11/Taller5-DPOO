package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest 
{
	private ProductoMenu productoMenu1;
	private Pedido pedido1;
	private File archivo;

    @BeforeEach
    void setUp( ) throws Exception
    {	
    	productoMenu1 = new ProductoMenu( "Hamburguesa Sencilla", 15000 );
    	pedido1 = new Pedido("Alejandro Gomez", "Su casa");
    	archivo = new File("factura.txt");
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
  
    @Test
    void testGetNombreCliente( )
    {
        assertEquals( "Alejandro Gomez", pedido1.getNombreCliente(), "El nombre del cliente no es el esperado." );
    }

    @Test
    void testGetPrecioTotalPedido( )
    {
    	pedido1.agregarProducto(productoMenu1);
        assertEquals( 17850, pedido1.getPrecioTotalPedido(), "El precio total del pedido no es correcto." );
    }
    
    @Test
    void testGenerarTextoFactura( )
    {	
    	pedido1.agregarProducto(productoMenu1);
    	StringBuffer sb = new StringBuffer( );
    	sb.append( "Cliente: " + "Alejandro Gomez" + "\n" );
	    sb.append( "Dirección: " + "Su casa" + "\n" );
	    sb.append( "----------------\n" );
	    sb.append( "Hamburguesa Sencilla" + "\n" );
        sb.append( "            " + 15000 + "\n" );
	    sb.append( "----------------\n" );
        sb.append( "Precio Neto:  " + 15000 + "\n" );
        sb.append( "IVA:          " + 2850 + "\n" );
        sb.append( "Precio Total: " + 17850 + "\n" );
	    String textoFactura = sb.toString();
	    
        assertEquals(textoFactura, pedido1.generarTextoFactura(), "La factura generada para el combo no es la esperada." );
    }
    
    @Test
    void testGuardarFactura( )
    {	
        
        if (archivo.exists()) {
            archivo.delete();
        }

        try {
			pedido1.guardarFactura(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        assertTrue(archivo.exists(), "La factura no se guardo de la forma esperada.");
    }
    
    @Test
    void testGuardarFacturaException( )
    {	
        
        if (archivo.exists()) {
            archivo.delete();
        }

        try {
			pedido1.guardarFactura(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        assertTrue(archivo.exists(), "La factura no se guardo de la forma esperada.");
    }
}
