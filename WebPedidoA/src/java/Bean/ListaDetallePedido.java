
package Bean;

import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class ListaDetallePedido {
    private String nombreProducto;
    private String descripcionProducto;
    private Integer cantidadProducto;
    private BigDecimal precio;
    private BigDecimal total; 

    public ListaDetallePedido(String nombreProducto, String descripcionProducto, Integer cantidadProducto, BigDecimal precio, BigDecimal total) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.precio = precio;
        this.total = total;
    }

 
    public String getNombreProducto() {
        return nombreProducto;
    }

  
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * @param descripcionProducto the descripcionProducto to set
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * @return the cantidadProducto
     */
    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    /**
     * @param cantidadProducto the cantidadProducto to set
     */
    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    /**
     * @return the precio
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
