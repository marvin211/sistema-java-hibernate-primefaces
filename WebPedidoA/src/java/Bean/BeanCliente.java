/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reportespedidoa.factory;


@ManagedBean
@ViewScoped
public class BeanCliente {
    private String nombreCliente;
    private String apellidoCliente;
    private String direccionCliente;
    private String ciudadCliente;
    private List listaCliente;
    private Integer codigoCliente;
    private List listaReporteCliente;
    
    @PostConstruct
    public void mostrar(){
        setListaCliente(Cruds.CrudCliente.universo());
    }
     public void insertar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            if(nombreCliente.equals("") ||(apellidoCliente.equals("")) ||(direccionCliente.equals("")) ||(ciudadCliente.equals(""))){
            context.addMessage(null, new FacesMessage("Error","No se aceptan campos vacios"));    
            }
            else{
          boolean flag = Cruds.CrudCliente.insert(nombreCliente, apellidoCliente, direccionCliente, ciudadCliente, 1);
          if(flag){
              limpiar();
              mostrar();
              context.addMessage(null, new FacesMessage("Exito","Registro Guardado"));
          }else{
              context.addMessage(null, new FacesMessage("Error"," El nombre de cliente ya existe "));
          }
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
     public void limpiar(){
         setNombreCliente("");
         setApellidoCliente("");
         setDireccionCliente("");
         setCiudadCliente("");
     }
     public void modificar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            boolean flag=Cruds.CrudCliente.update(codigoCliente, nombreCliente, apellidoCliente, direccionCliente, ciudadCliente, 1);
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
            boolean flag=Cruds.CrudCliente.anular(codigoCliente,1);
            if(flag){
                limpiar();
                mostrar();
                context.addMessage(null,new FacesMessage("Exito","Registro anulado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Registro no Actualizado")); 
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
    public void enviarDatos(Pojos.Cliente cliente){
        setNombreCliente(cliente.getNombreCliente());
        setApellidoCliente(cliente.getApellidoCliente());
        setDireccionCliente(cliente.getDireccionCliente());
        setCiudadCliente(cliente.getCiudadCliente());
        setCodigoCliente(cliente.getCodigoCliente());
    }
    public void enviarDatosAnular(Pojos.Cliente cliente){
        setCodigoCliente(cliente.getCodigoCliente());
        
    }
    
    
    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the apellidoCliente
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * @param apellidoCliente the apellidoCliente to set
     */
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    /**
     * @return the direccionCliente
     */
    public String getDireccionCliente() {
        return direccionCliente;
    }

    /**
     * @param direccionCliente the direccionCliente to set
     */
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    /**
     * @return the ciudadCliente
     */
    public String getCiudadCliente() {
        return ciudadCliente;
    }

    /**
     * @param ciudadCliente the ciudadCliente to set
     */
    public void setCiudadCliente(String ciudadCliente) {
        this.ciudadCliente = ciudadCliente;
    }

    /**
     * @return the listaCliente
     */
    public List getListaCliente() {
        return listaCliente;
    }

    /**
     * @param listaCliente the listaCliente to set
     */
    public void setListaCliente(List listaCliente) {
        this.listaCliente = listaCliente;
    }

    /**
     * @return the codigoCliente
     */
    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the listaReporteCliente
     */
    public List getListaReporteCliente() {
        return listaReporteCliente;
    }

    /**
     * @param listaReporteCliente the listaReporteCliente to set
     */
    public void setListaReporteCliente(List listaReporteCliente) {
        this.listaReporteCliente = listaReporteCliente;
    }
    
    
}
