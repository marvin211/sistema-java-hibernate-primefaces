package Pojos;
// Generated 1/11/2021 08:47:37 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Categoria generated by hbm2java
 */
public class Categoria  implements java.io.Serializable {


     private Integer codigoCategoria;
     private boolean estado;
     private String nombreCategoria;
     private String descripcionCategoria;
     private Integer usuarioIngreso;
     private Date fechaIngreso;
     private Integer usuarioModificacion;
     private Date fechaModificacion;
     private Set<Producto> productos = new HashSet<Producto>(0);

    public Categoria() {
    }

	
    public Categoria(boolean estado, String descripcionCategoria) {
        this.estado = estado;
        this.descripcionCategoria = descripcionCategoria;
    }
    public Categoria(boolean estado, String nombreCategoria, String descripcionCategoria, Integer usuarioIngreso, Date fechaIngreso, Integer usuarioModificacion, Date fechaModificacion, Set<Producto> productos) {
       this.estado = estado;
       this.nombreCategoria = nombreCategoria;
       this.descripcionCategoria = descripcionCategoria;
       this.usuarioIngreso = usuarioIngreso;
       this.fechaIngreso = fechaIngreso;
       this.usuarioModificacion = usuarioModificacion;
       this.fechaModificacion = fechaModificacion;
       this.productos = productos;
    }
   
    public Integer getCodigoCategoria() {
        return this.codigoCategoria;
    }
    
    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }
    public boolean isEstado() {
        return this.estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }
    
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public String getDescripcionCategoria() {
        return this.descripcionCategoria;
    }
    
    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
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
    public Set<Producto> getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }




}


