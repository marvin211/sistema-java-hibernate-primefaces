package Bean;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reportespedidoa.factory;



/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class BeanPedido {
    
    private Integer codigoCliente;
    private List listaCliente;
    private List listaProducto;
    private Integer codigoProducto;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal total;
    private List listaDetalle;
    private BigDecimal totalFinal;
    private List listaReporte;
     private List listaReporteProductoMejor;
    
    private List<ListaDetallePedido> list = new ArrayList<ListaDetallePedido>();    
    
    @PostConstruct
    public void inicio() {
        comboCliente();
        comboProducto();
        mostrar();
    }

//    public void mostrar(){
//        setListaDetalle(Cruds.CrudPedidoDetalle.universo());
//    }
    public void mostrar() {
        String nombreProducto, descripcionProducto;
        Integer cantidadProducto;
        BigDecimal precio2, total2, cant = new BigDecimal(0);

        list.clear();//Limpiar la lista
        setTotalFinal(new BigDecimal(0));
        
        for (Iterator it = Cruds.CrudPedidoDetalle.universo2().iterator(); it.hasNext();) {
            Object[] item = (Object[]) it.next();
            nombreProducto = (String) item[0];
            descripcionProducto = (String) item[1];
            cantidadProducto = (Integer) item[2];
            cant = new BigDecimal(cantidadProducto);
            precio2 = (BigDecimal) item[3];
            total2 = cant.multiply(precio2);
            setTotalFinal(getTotalFinal().add(total2));
            getList().add(new ListaDetallePedido(nombreProducto, descripcionProducto, cantidadProducto, precio2, total2));
        }
        
    }
    
    public void consultaPrecio() {        
        setPrecio(Cruds.CrudProducto.select(getCodigoProducto()).getPrecioProducto());
    }
    
    public void totalPedido() {
        BigDecimal cantidad2 = new BigDecimal(getCantidad());
        
        setTotal(cantidad2.multiply(getPrecio()));
        
    }
    
    public List<SelectItem> comboCliente() {
        this.setListaCliente(new ArrayList<SelectItem>());
        List<Pojos.Cliente> lstCliente = Cruds.CrudCliente.universo();
        
        for (Pojos.Cliente cliente : lstCliente) {
            SelectItem clienteItem = new SelectItem(cliente.getCodigoCliente(), cliente.getNombreCliente() + " "
                    + cliente.getApellidoCliente());
            getListaCliente().add(clienteItem);
        }
        
        return getListaCliente();
    }
    
    public List<SelectItem> comboProducto() {
        this.setListaProducto(new ArrayList<SelectItem>());
        List<Pojos.Producto> lstProducto = Cruds.CrudProducto.universo();
        
        for (Pojos.Producto producto : lstProducto) {
            SelectItem productoItem = new SelectItem(producto.getCodigoProducto(), producto.getNombreProducto());
            
            getListaProducto().add(productoItem);
        }
        
        return getListaProducto();
    }
    
 
    public void reportePedido() throws IOException{
        FacesContext contex = FacesContext.getCurrentInstance();
        try {
          
            reportespedidoa.ReportesPedidoA.reportePedido();
            Map parametros = new HashMap();
            parametros.put("nombreCliente","Marvin Lopez");
          
            this.setListaReporte(factory.reportePedido());
            JRBeanCollectionDataSource ds = new  JRBeanCollectionDataSource(getListaReporte()) ;
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/ReportePedido.jasper"));
            byte [] bytes = JasperRunManager.runReportToPdf(jasper.getPath(),parametros, ds);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream(); 
            outStream.write(bytes,0,bytes.length);
            outStream.flush();
            outStream.close();
            FacesContext.getCurrentInstance().responseComplete();
  
        } catch (JRException e) {
            contex.addMessage(null, new FacesMessage("Error", "Error al cargar el reporte: "+e));
        }
    }
    
    
     public void reporteProductoMejor() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            reportespedidoa.ReportesPedidoA.reporteProductoMejor();
            setListaReporteProductoMejor(factory.reporteProductoMejor());
            JRBeanCollectionDataSource ds=new JRBeanCollectionDataSource(getListaReporteProductoMejor());
            File jasper=new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/reporteProductoMejor.jasper"));
            byte[]bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,ds);
            HttpServletResponse response=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream=response.getOutputStream();
            outStream.write(bytes,0,bytes.length);
            outStream.flush();
            outStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException e){
            context.addMessage(null, new FacesMessage("Error", "Error al cargar el reporte" + e));
        }
    }

    
    
    public void insertar() {
        
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (codigoProducto == null || (precio == null) || (cantidad == null) || (total == null)) {
                context.addMessage(null, new FacesMessage("Error", "No se aceptan campos vacios"));                
            } else {
                 
                   System.out.println("codigo producto: "+codigoProducto);
                   System.out.println("precio: "+precio);
                   
                boolean flag = Cruds.CrudPedidoDetalle.insert(codigoProducto, cantidad, precio);
             
                if (flag) {
                    limpiar();
                    mostrar();
                    context.addMessage(null, new FacesMessage("Exito", "Registro Guardado"));
                } else {
                    context.addMessage(null, new FacesMessage("Error", "no se ha guardado el registro"));
                }
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error", "Error " + e));
        }
    }
    
    public void limpiar() {
        this.setCodigoProducto(null);
        this.setCodigoCliente(null);
        this.setPrecio(null);
        this.setCantidad(null);
        this.setTotal(null);
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
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    /**
     * @return the listaDetalle
     */
    public List getListaDetalle() {
        return listaDetalle;
    }

    /**
     * @param listaDetalle the listaDetalle to set
     */
    public void setListaDetalle(List listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    /**
     * @return the totalFinal
     */
    public BigDecimal getTotalFinal() {
        return totalFinal;
    }

    /**
     * @param totalFinal the totalFinal to set
     */
    public void setTotalFinal(BigDecimal totalFinal) {
        this.totalFinal = totalFinal;
    }

    /**
     * @return the list
     */
    public List<ListaDetallePedido> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<ListaDetallePedido> list) {
        this.list = list;
    }

    /**
     * @return the listaReporte
     */
    public List getListaReporte() {
        return listaReporte;
    }

    /**
     * @param listaReporte the listaReporte to set
     */
    public void setListaReporte(List listaReporte) {
        this.listaReporte = listaReporte;
    }

    /**
     * @return the listaReporteProductoMejor
     */
    public List getListaReporteProductoMejor() {
        return listaReporteProductoMejor;
    }

    /**
     * @param listaReporteProductoMejor the listaReporteProductoMejor to set
     */
    public void setListaReporteProductoMejor(List listaReporteProductoMejor) {
        this.listaReporteProductoMejor = listaReporteProductoMejor;
    }
    
}
