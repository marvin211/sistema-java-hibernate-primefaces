/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Pojos.Proveedor;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Usuario
 */
public class CrudProveedor {
    
     public static boolean insert(String nombre,String direccion, String telefono, Integer usuarioIngreso){
        boolean bandera=false;
        Date fechaIngreso=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Proveedor.class);
        criteria.add(Restrictions.eq("nombre",nombre));
        Proveedor insert=(Proveedor)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
            transaccion=session.beginTransaction();
            if(insert==null){
               insert=new Proveedor();
               insert.setEstado(true);
               insert.setNombre(nombre);
               insert.setDireccion(direccion);
               insert.setTelefono(telefono);
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
     
     
    public static boolean update(Integer codigoProveedor,String nombre,String direccion, String telefono, Integer usuarioModificacion){
        Date fechaModifica=new Date();
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Proveedor.class);
        criteria.add(Restrictions.eq("codigoProveedor",codigoProveedor));
        criteria.add(Restrictions.eq("estado", true));
        Proveedor update=(Proveedor)criteria.uniqueResult();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
        if(update!=null){
            update.setNombre(nombre);
            update.setDireccion(direccion);
            update.setTelefono(telefono);
            update.setUsuarioModificacion(usuarioModificacion);
            update.setFechaModificacion(fechaModifica);
            session.update(update);
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
    
    
  public static boolean anular (Integer codigoProveedor,Integer usuarioAnula){
   boolean bandera=false;
   Date fecha=new Date();
   Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
   Criteria criteria=session.createCriteria(Proveedor.class);
   criteria.add(Restrictions.eq("codigoProveedor",codigoProveedor));
   criteria.add(Restrictions.eq("estado", true));
   Proveedor anular=(Proveedor)criteria.uniqueResult();
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
  
   public static List<Proveedor>universo(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Proveedor.class);
       criteria.add(Restrictions.eq("estado", true));
        criteria.addOrder(Order.desc("codigoProveedor"));
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en la consulta proveedor: "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 
    
}
