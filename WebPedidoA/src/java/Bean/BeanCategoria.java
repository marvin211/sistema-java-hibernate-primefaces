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

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class BeanCategoria {
    private Integer codigoCategoria; 
    private String nombreCategoria;
    private String descripcionCategoria;
    private List listaCategoria;

     @PostConstruct
    public void mostrar(){
        setListaCategoria(Cruds.CrudCategoria.universo());
    }
     public void insertar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            if(nombreCategoria.equals("") ||(descripcionCategoria.equals(""))){
            context.addMessage(null, new FacesMessage("Error","No se aceptan campos vacios"));    
            }
            else{
          boolean flag = Cruds.CrudCategoria.insert(nombreCategoria, descripcionCategoria,1);
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
         setNombreCategoria("");
         setDescripcionCategoria("");
        
     }
     public void modificar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=Cruds.CrudCategoria.update(codigoCategoria, nombreCategoria, descripcionCategoria,1);
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
            boolean flag=Cruds.CrudCategoria.anular(codigoCategoria,1);
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
    public void enviarDatos(Pojos.Categoria categoria){
        setNombreCategoria(categoria.getNombreCategoria());
        setDescripcionCategoria(categoria.getDescripcionCategoria());
        setCodigoCategoria(categoria.getCodigoCategoria());
    }
    public void enviarDatosAnular(Pojos.Categoria categoria){
        setCodigoCategoria(categoria.getCodigoCategoria());
        
    }
    
    
    /**
     * @return the codigoCategoria
     */
    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     * @param codigoCategoria the codigoCategoria to set
     */
    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    /**
     * @return the nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * @param nombreCategoria the nombreCategoria to set
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * @return the descripcionCategoria
     */
    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    /**
     * @param descripcionCategoria the descripcionCategoria to set
     */
    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    /**
     * @return the listaCategoria
     */
    public List getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List listaCategoria) {
        this.listaCategoria = listaCategoria;
    }
    
    
    
    
    
}
