/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class BeanProveedor {
    
    private Integer codigoProveedor; 
    private String nombre;
    private String direccion;
    private String telefono;  
    private List listaProveedor;
    
    
     @PostConstruct
    public void mostrar(){
        setListaProveedor(Cruds.CrudProveedor.universo());
    }
    
     public void insertar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            if(nombre.equals("") ||(direccion.equals("")) ||(telefono.equals(""))  ){
            context.addMessage(null, new FacesMessage("Error","No se aceptan campos vacios"));    
            }
            else{
          boolean flag = Cruds.CrudProveedor.insert(nombre, direccion, telefono,1);
          if(flag){
              limpiar();
              mostrar();
              context.addMessage(null, new FacesMessage("Exito","Registro Guardado"));
          }else{
              context.addMessage(null, new FacesMessage("Error","No se pudo guardar el registro"));
          }
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
     
     public void limpiar(){
         setNombre("");
         setDireccion("");
         setTelefono("");
        
     }
     
     public void modificar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=Cruds.CrudProveedor.update(codigoProveedor, nombre, direccion, telefono,1);
            if(flag){
                limpiar();
                mostrar();
                context.addMessage(null,new FacesMessage("Exito","Registro modificado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Registro no Actualizado")); 
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
    
    public void anular(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=Cruds.CrudProveedor.anular(codigoProveedor,1);
            if(flag){
                limpiar();
                mostrar();
                context.addMessage(null,new FacesMessage("Exito","Registro anulado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","No se pudo anular el registro")); 
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
    public void enviarDatos(Pojos.Proveedor proveedor){
        setNombre(proveedor.getNombre());
        setDireccion(proveedor.getDireccion());
        setTelefono(proveedor.getTelefono());
        setCodigoProveedor(proveedor.getCodigoProveedor());
    }
    
    public void enviarDatosAnular(Pojos.Proveedor proveedor){
        setCodigoProveedor(proveedor.getCodigoProveedor());
        
    }

    
    

    
    
    
    
    
    
    
    
    /**
     * @return the codigoProveedor
     */
    public Integer getCodigoProveedor() {
        return codigoProveedor;
    }

    /**
     * @param codigoProveedor the codigoProveedor to set
     */
    public void setCodigoProveedor(Integer codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the listaProveedor
     */
    public List getListaProveedor() {
        return listaProveedor;
    }

    /**
     * @param listaProveedor the listaProveedor to set
     */
    public void setListaProveedor(List listaProveedor) {
        this.listaProveedor = listaProveedor;
    }
    
}
