/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;


import Pojos.Categoria;
import Pojos.Producto;
import Pojos.Proveedor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author madel
 */
public class CrudProducto {
    
    
    
//Evento que permite ir a traer determiandos campos
public static Producto select(Integer codigoProducto){
    BigDecimal precioCero = new BigDecimal(0);
    Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
    Criteria criteria=session.createCriteria(Producto.class); 
    //criterios de busquera
    criteria.add(Restrictions.eq("codigoProducto",codigoProducto));
    criteria.add(Restrictions.eq("estado",true));//Me desplega todo los que estan activos 1
    Producto select =(Producto)criteria.uniqueResult();
    
    //Dependiendo de lo que quiero consultar que me muestre un 0 sino lo encuentra
    if(select==null){//Si viene un valor vacio
        select= new Producto();
        select.setPrecioProducto(precioCero);//Que muestre un 0 sino encuentra el precio
    }
    
    session.close();
    return select;   
}
    
    
    
    
    public static boolean insert(String nombreProducto,String descripcionProducto, Integer codigoCategoria,Integer cantidadProducto,BigDecimal precioProducto,Integer codigoProveedor,Integer usuarioIngreso){
        boolean bandera=false;
        Date fechaIngreso=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("nombreProducto",nombreProducto));
        Producto insert=(Producto)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
            transaccion=session.beginTransaction();
            if(insert==null){
               insert=new Producto();
               Categoria codCategoria = new Categoria();
               Proveedor codProveedor = new Proveedor();
               
               insert.setEstado(true);
               insert.setNombreProducto(nombreProducto);
               insert.setDescripcionProducto(descripcionProducto);
               codCategoria.setCodigoCategoria(codigoCategoria);
               insert.setCategoria(codCategoria);
               
               insert.setCantidadProducto((int)cantidadProducto);
               insert.setPrecioProducto(precioProducto);
               codProveedor.setCodigoProveedor(codigoProveedor);
               insert.setProveedor(codProveedor);
               
               insert.setUsuarioIngreso(usuarioIngreso);
               insert.setFechaIngreso(fechaIngreso);
               session.save(insert);
               bandera=true;
            }
            transaccion.commit();
        }catch(HibernateException e){
            System.out.println("error al ingresar"+e);
            transaccion.rollback();
        }
        finally{
            session.close();
        }
        return bandera;
    }
    
    public static boolean update(Integer codigoProducto,String nombreProducto,String descripcionProducto, Integer codigoCategoria, Integer cantidadProducto,BigDecimal precioProducto, Integer codigoProveedor, Integer usuarioMoficacion){
        Date fechaModifica=new Date();
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("codigoProducto",codigoProducto));
        criteria.add(Restrictions.eq("estado",true));
        
        Producto update1 = (Producto) criteria.uniqueResult();
 
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
        if(update1!=null){
               
               Categoria codCategoria = new Categoria();
               Proveedor codProveedor = new Proveedor();
               
               
               update1.setEstado(true);
               update1.setNombreProducto(nombreProducto);
               update1.setDescripcionProducto(descripcionProducto);
               codCategoria.setCodigoCategoria(codigoCategoria);
               update1.setCategoria(codCategoria);
               
               update1.setCantidadProducto((int)cantidadProducto);
               update1.setPrecioProducto(precioProducto);
               codProveedor.setCodigoProveedor(codigoProveedor);
               update1.setProveedor(codProveedor);
               
               
               update1.setUsuarioModificacion(usuarioMoficacion);           
               update1.setFechaModificacion(fechaModifica);
            session.update(update1);
            bandera=true;
        }
        transaction.commit();
        }catch(HibernateException e){
            System.out.println("Error "+e);
            transaction.rollback();
        }
        finally{
            session.close();
        }               
  return bandera;             
    }
    
  public static boolean anular (Integer codigoProducto,Integer usuarioAnula){
   boolean bandera=false;
   Date fecha=new Date();
   Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
   Criteria criteria=session.createCriteria(Producto.class);
   criteria.add(Restrictions.eq("codigoProducto",codigoProducto));
   criteria.add(Restrictions.eq("estado", true));
   Producto anular=(Producto)criteria.uniqueResult();
   Transaction transaccion=null;
   try{
       transaccion=session.beginTransaction();
       if(anular!=null){
           anular.setEstado(false);
           anular.setUsuarioModificacion(usuarioAnula);
           anular.setFechaModificacion(fecha);
           session.update(anular);
           bandera=true;
       }
       transaccion.commit();
   }catch(HibernateException e){
       System.out.println("error "+e);
       transaccion.rollback();
   }finally{
       session.close();
   }   
   return bandera;
  }
   public static List<Producto>universo(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Producto.class);
       criteria.add(Restrictions.eq("estado", true));
       criteria.addOrder(Order.desc("codigoProducto"));
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en consulta cliente "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 

//    ==========================================================================
   
    public static List<Producto>universoJoin(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Producto.class);
       criteria.add(Restrictions.eq("estado", true));
       criteria.addOrder(Order.desc("codigoProducto"));
       criteria.createAlias("categoria", "c");
       criteria.createAlias("proveedor", "p");
       
       lista=criteria.list();
       
       }catch(HibernateException e){
           System.out.println("error en consulta categoria "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 
    

}
