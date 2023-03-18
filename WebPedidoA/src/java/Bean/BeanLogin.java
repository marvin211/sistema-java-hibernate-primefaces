/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Scope.SessionBean;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC-Laptop
 */
@ViewScoped
@ManagedBean
public class BeanLogin {
    @ManagedProperty (value="#{sessionBean}")
    private SessionBean sessionBean = null;
    private String contrasenia=null;

    public String login() throws IOException{
        FacesContext context=FacesContext.getCurrentInstance();
        if((contrasenia.equals(""))||(getSessionBean().getNombreUsuario().equals(""))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error "+"Usuario/Contraseña Vacíos",""));
            return null;
        }else{
            Pojos.Usuario usuarioSelect=Cruds.CrudUsuario.select(getSessionBean().getNombreUsuario());
            getSessionBean().setUsuario(usuarioSelect);
            String password=Cruds.Login.sha1(contrasenia);
            if(usuarioSelect.getUsuario()==null){
                getSessionBean().setNombreUsuario("");
                setContrasenia("");
                context.addMessage(null,new FacesMessage("Error","El usuario no existe"));
//                return "login";
            }else if(usuarioSelect.getContrasenia().equals(password)){
                getSessionBean().setNombreUsuario("");
                setContrasenia("");
                context.addMessage(null,new FacesMessage("EXITO","Bienvenido"));
                context.getExternalContext().redirect("index.xhtml");
               
            }else{
                setContrasenia("");
                context.addMessage(null,new FacesMessage("Error","Error en usuario/contrasenia"));
            }
            return "login";
        }
    }
    
    /**
     * @return the sessionBean
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * @param sessionBean the sessionBean to set
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
