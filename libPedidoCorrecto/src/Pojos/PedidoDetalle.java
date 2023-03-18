package Pojos;
// Generated 1/11/2021 08:47:37 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PedidoDetalle generated by hbm2java
 */
public class PedidoDetalle  implements java.io.Serializable {


     private PedidoDetalleId id;
     private Pedido pedido;
     private Producto producto;
     private Integer cantidad;
     private BigDecimal precio;

    public PedidoDetalle() {
    }

	
    public PedidoDetalle(PedidoDetalleId id, Pedido pedido, Producto producto) {
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
    }
    public PedidoDetalle(PedidoDetalleId id, Pedido pedido, Producto producto, Integer cantidad, BigDecimal precio) {
       this.id = id;
       this.pedido = pedido;
       this.producto = producto;
       this.cantidad = cantidad;
       this.precio = precio;
    }
   
    public PedidoDetalleId getId() {
        return this.id;
    }
    
    public void setId(PedidoDetalleId id) {
        this.id = id;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }




}


