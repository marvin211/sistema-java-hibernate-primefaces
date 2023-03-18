/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libpedido;

import Cruds.CrudCategoria;
import Cruds.CrudCliente;
import Cruds.CrudPedidoDetalle;
import Cruds.CrudProducto;
import Cruds.CrudProveedor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author madel
 */
public class LibPedido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date fecha = new Date();

//        System.out.println(Cruds.CrudPedidoDetalle.insert(1, fecha, 1));
//   System.out.println(CrudCliente.anular(11, 1));
//        for (int i = 0; i < CrudProducto.universo().size(); i++) {
//            System.out.println(CrudProducto.universo().get(i).getNombreProducto() + " " + CrudProducto.universo().get(i).getDescripcionProducto());
//        }
//BigDecimal precio=new BigDecimal(10);
//System.out.println(CrudPedido2.insertar(1, 1, 5, precio, 1));
//        DecimalFormat df = new DecimalFormat("#,###.00");
//        System.out.println(df.format(new BigDecimal(123456.75)));
 // Cruds.CrudProducto.update(2,"ID 2 Actualizado", "ID 2 Actualizado", 60, new BigDecimal(20), 1);
 
 // Cruds.CrudProducto.insert("pan", "Idd", 60, new BigDecimal(20), 1);
//System.out.println(Cruds.CrudProducto.anular(6,1));

//       System.out.println(""+Cruds.CrudProducto.select(90).getPrecioProducto());

       // System.out.println( Cruds.CrudPedidoDetalle.universo2());

        //Cruds.CrudPedidoDetalle.insert(17, 2, new BigDecimal(2));
        
     //  Cruds.CrudProveedor.anular(1,1);
        
      // Cruds.CrudProducto.update(4, "Modificado", "modificado",8, 30,new BigDecimal(2), 1, 1);
  }
    
}
    
