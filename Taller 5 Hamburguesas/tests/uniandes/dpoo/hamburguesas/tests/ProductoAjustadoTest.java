package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest 
{
	private ProductoAjustado productoAjustado1;
	private ProductoMenu productoMenu1;
	

    @BeforeEach
    void setUp( ) throws Exception
    {
    	productoMenu1 = new ProductoMenu( "Hamburguesa Sencilla", 15000 );
        productoAjustado1 = new ProductoAjustado(productoMenu1);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "Hamburguesa Sencilla", productoAjustado1.getNombre(), "El nombre del producto ajustado no es el esperado." );
    }

    @Test
    void testGetCostoAdicional( )
    {
        assertEquals( 15000, productoAjustado1.getPrecio(), "El costo del producto ajustado no es el esperado." );
    }
    
    @Test
    void testGetGenerarTextoFactura( )
    {
    	StringBuffer sb = new StringBuffer( );
        sb.append( productoMenu1 );
        sb.append( "            " + 15000 + "\n" );
        String textoFactura = sb.toString();
        assertEquals( textoFactura, productoAjustado1.generarTextoFactura(), "El texto de la factura generada no es el esperado." );
    }
}
