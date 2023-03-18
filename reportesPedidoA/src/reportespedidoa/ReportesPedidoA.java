/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportespedidoa;

import Reportes.ReportePedido;
import Reportes.ReporteProductoMejor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ReportesPedidoA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static List reportePedido() {
        BigDecimal cant = new BigDecimal(0), total2 = new BigDecimal(0), precio2 = new BigDecimal(0);
        String nombreProducto;
        Integer cantidadProducto;
        List<ReportePedido> list = new ArrayList<ReportePedido>();
        for (Iterator it = Cruds.CrudPedidoDetalle.universo2().iterator(); it.hasNext();) {
            Object[] item = (Object[]) it.next();
            nombreProducto = (String) item[0];
            cantidadProducto = (Integer) item[2];
            cant = new BigDecimal(cantidadProducto);
            precio2 = (BigDecimal) item[3];
            total2 = cant.multiply(precio2);
            list.add(new ReportePedido(nombreProducto, cantidadProducto, precio2, total2));
            factory comp = new factory();
            comp.setReportePedido((ArrayList<ReportePedido>) list);
        }
        return list;
    }

    public static List reporteProductoMejor() {
        String nombreProducto;
        BigDecimal cantidadProducto;
        List<ReporteProductoMejor> list = new ArrayList<ReporteProductoMejor>();
        for (Iterator it = Cruds.CrudPedidoDetalle.reporteProductoMejor().iterator(); it.hasNext();) {
            Object[] item = (Object[]) it.next();
            nombreProducto = (String) item[0];
            cantidadProducto = (BigDecimal) item[1];
            list.add(new ReporteProductoMejor(nombreProducto, cantidadProducto));
            factory comp = new factory();
            comp.setReporteProductoMejor((ArrayList<ReporteProductoMejor>) list);
        }
        return list;
    }
}
