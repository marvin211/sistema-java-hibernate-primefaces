/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportespedidoa;

import Reportes.ReportePedido;
import Reportes.ReporteProductoMejor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class factory {

    private static ArrayList<ReportePedido> reportePedido = new ArrayList<ReportePedido>();
    private static ArrayList<ReporteProductoMejor> reporteProductoMejor = new ArrayList<ReporteProductoMejor>();
  
    public static List reportePedido() {
        return getReportePedido();
    }

    public static List reporteProductoMejor() {
        return getReporteProductoMejor();
    }

    /**
     * @return the reportePedido
     */
    public static ArrayList<ReportePedido> getReportePedido() {
        return reportePedido;
    }

    /**
     * @param aReportePedido the reportePedido to set
     */
    public static void setReportePedido(ArrayList<ReportePedido> aReportePedido) {
        reportePedido = aReportePedido;
    }

    /**
     * @return the reporteProductoMejor
     */
    public static ArrayList<ReporteProductoMejor> getReporteProductoMejor() {
        return reporteProductoMejor;
    }

    /**
     * @param aReporteProductoMejor the reporteProductoMejor to set
     */
    public static void setReporteProductoMejor(ArrayList<ReporteProductoMejor> aReporteProductoMejor) {
        reporteProductoMejor = aReporteProductoMejor;
    }

  

}
