package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest
{
	private Combo combo1;
	private ProductoMenu productoMenu1;
	private ProductoMenu productoMenu2;
	private ArrayList<ProductoMenu> productos1;

    @BeforeEach
    void setUp( ) throws Exception
    {	
    	productos1 = new ArrayList<ProductoMenu>();
    	productoMenu1 = new ProductoMenu( "Hamburguesa Sencilla", 15000 );
    	productoMenu2 = new ProductoMenu( "Agua sin gas", 3000 );
    	productos1.add(productoMenu1);
    	productos1.add(productoMenu2);
    	
        combo1 = new Combo( "Hamburguesa Sencilla y agua sin gas", 0.1, productos1);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "Hamburguesa Sencilla y agua sin gas", combo1.getNombre(), "El nombre del combo no es el esperado." );
    }

    @Test
    void testGetDescuento( )
    {
        assertEquals( 16200, combo1.getPrecio(), "El precio despues del descuento del combo no es el esperado." );
    }
    
    @Test
    void testGenerarTextoFactura( )
    {
        assertEquals( "Combo Hamburguesa Sencilla y agua sin gas\n Descuento: 0.1\n            16200\n", combo1.generarTextoFactura(), "La factura generada para el combo no es la esperada." );
    }
}
