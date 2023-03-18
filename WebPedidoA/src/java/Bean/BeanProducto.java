package Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;



@ManagedBean
@ViewScoped
public class BeanProducto {

    private Integer codigoProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer cantidadProducto;
    private BigDecimal precioProducto;
    private List listaProducto;
    
    private List listaCategoria;
    private List listaProveedor;
    private Integer codigoCategoria;
    private Integer codigoProveedor;
     
         @PostConstruct
    public void mostrar(){
        setListaProducto(Cruds.CrudProducto.universoJoin());
        comboCategoria(); 
        comboProveedor();
    }
     
      public List<SelectItem> comboCategoria() {
        this.setListaCategoria(new ArrayList<SelectItem>());
        List<Pojos.Categoria> lstCategoria = Cruds.CrudCategoria.universo();
        
        for (Pojos.Categoria categoria : lstCategoria) {
            SelectItem categoriaItem = new SelectItem(categoria.getCodigoCategoria(), categoria.getNombreCategoria());
            
            getListaCategoria().add(categoriaItem);
        }
        
        return getListaCategoria();
    }
    
        public List<SelectItem> comboProveedor() {
        this.setListaProveedor(new ArrayList<SelectItem>());
        List<Pojos.Proveedor> lstProveedor = Cruds.CrudProveedor.universo();
        
        for (Pojos.Proveedor proveedor : lstProveedor) {
            SelectItem proveedorItem = new SelectItem(proveedor.getCodigoProveedor(), proveedor.getNombre());
            
            getListaProveedor().add(proveedorItem);
        }
        
        return getListaProveedor();
    }
    
    public void insertar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (nombreProducto.equals("") || (descripcionProducto.equals("")) || (cantidadProducto.equals("")) || (precioProducto.equals(""))) {
                context.addMessage(null, new FacesMessage("Error", "No se aceptan campos vacios"));
            } else {
                boolean flag = Cruds.CrudProducto.insert(nombreProducto, descripcionProducto, codigoCategoria, cantidadProducto, precioProducto, codigoProveedor,1);
               
                if (flag) {
                    limpiar();
                    mostrar();
                    context.addMessage(null, new FacesMessage("Exito", "Registro Guardado"));
                } else {
                    context.addMessage(null, new FacesMessage("Error", " El nombre del producto ya existe "));
                }
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error", "Error " + e));
        }
    }
    
         public void modificar(){
        FacesContext context=FacesContext.getCurrentInstance();
        try{
            
            boolean flag=Cruds.CrudProducto.update(codigoProducto, nombreProducto, descripcionProducto, codigoCategoria, cantidadProducto, precioProducto, codigoProveedor, 1);
            
            if(flag){
                limpiar();
                mostrar();
                context.addMessage(null,new FacesMessage("Exito","Registro Actualizado"));
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
            boolean flag=Cruds.CrudProducto.anular(codigoProducto,1);
            if(flag){
                limpiar();
                mostrar();
                context.addMessage(null,new FacesMessage("Exito","Registro anulado"));
            }else{
                context.addMessage(null, new FacesMessage("Error","Error, al intentar anular el registro")); 
            }
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error","Error "+e));
        }
    }
    public void enviarDatos(Pojos.Producto producto){
        setCodigoProducto(producto.getCodigoProducto());
        setNombreProducto(producto.getNombreProducto());
        setDescripcionProducto(producto.getDescripcionProducto());
        setCantidadProducto(producto.getCantidadProducto());
        setPrecioProducto(producto.getPrecioProducto());
        setCodigoCategoria(producto.getCategoria().getCodigoCategoria());
        setCodigoProveedor(producto.getProveedor().getCodigoProveedor());
        
    }
    public void enviarDatosAnular(Pojos.Producto producto){
        setCodigoProducto(producto.getCodigoProducto());
        
    }
       public void limpiar(){
         this.setNombreProducto("");
         this.setDescripcionProducto("");
         this.setCantidadProducto(null);
         this.setPrecioProducto(null);
         this.setCodigoCategoria(null);
         this.setCodigoProveedor(null);
     }
    
    /**
     * @return the codigoProducto
     */
    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
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
     * @return the precioProducto
     */
    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * @return the listaProducto
     */
    public List getListaProducto() {
        return listaProducto;
    }

    /**
     * @param listaProducto the listaProducto to set
     */
    public void setListaProducto(List listaProducto) {
        this.listaProducto = listaProducto;
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

 

}
