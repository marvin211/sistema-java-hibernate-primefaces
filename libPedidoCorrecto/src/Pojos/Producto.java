package Pojos;
// Generated 1/11/2021 08:47:37 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private Integer codigoProducto;
     private Categoria categoria;
     private Proveedor proveedor;
     private boolean estado;
     private String nombreProducto;
     private String descripcionProducto;
     private int cantidadProducto;
     private BigDecimal precioProducto;
     private Integer usuarioIngreso;
     private Date fechaIngreso;
     private Integer usuarioModificacion;
     private Date fechaModificacion;
     private Set<PedidoDetalle> pedidoDetalles = new HashSet<PedidoDetalle>(0);

    public Producto() {
    }

	
    public Producto(Categoria categoria, Proveedor proveedor, boolean estado, String nombreProducto, String descripcionProducto, int cantidadProducto, BigDecimal precioProducto) {
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.estado = estado;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
    }
    public Producto(Categoria categoria, Proveedor proveedor, boolean estado, String nombreProducto, String descripcionProducto, int cantidadProducto, BigDecimal precioProducto, Integer usuarioIngreso, Date fechaIngreso, Integer usuarioModificacion, Date fechaModificacion, Set<PedidoDetalle> pedidoDetalles) {
       this.categoria = categoria;
       this.proveedor = proveedor;
       this.estado = estado;
       this.nombreProducto = nombreProducto;
       this.descripcionProducto = descripcionProducto;
       this.cantidadProducto = cantidadProducto;
       this.precioProducto = precioProducto;
       this.usuarioIngreso = usuarioIngreso;
       this.fechaIngreso = fechaIngreso;
       this.usuarioModificacion = usuarioModificacion;
       this.fechaModificacion = fechaModificacion;
       this.pedidoDetalles = pedidoDetalles;
    }
   
    public Integer getCodigoProducto() {
        return this.codigoProducto;
    }
    
    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Proveedor getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public boolean isEstado() {
        return this.estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getNombreProducto() {
        return this.nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getDescripcionProducto() {
        return this.descripcionProducto;
    }
    
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    public int getCantidadProducto() {
        return this.cantidadProducto;
    }
    
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public BigDecimal getPrecioProducto() {
        return this.precioProducto;
    }
    
    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }
    public Integer getUsuarioIngreso() {
        return this.usuarioIngreso;
    }
    
    public void setUsuarioIngreso(Integer usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public Integer getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    
    public void setUsuarioModificacion(Integer usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Set<PedidoDetalle> getPedidoDetalles() {
        return this.pedidoDetalles;
    }
    
    public void setPedidoDetalles(Set<PedidoDetalle> pedidoDetalles) {
        this.pedidoDetalles = pedidoDetalles;
    }




}


