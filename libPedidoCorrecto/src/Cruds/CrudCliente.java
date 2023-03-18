/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Pojos.Cliente;
import java.awt.image.RescaleOp;
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
public class CrudCliente {
    public static boolean insert(String nombreCliente,String apellidoCliente,String direccionCliente,String ciudadCliente,int usuarioIngreso){
        boolean bandera=false;
        Date fechaIngreso=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("nombreCliente",nombreCliente));
        Cliente insert=(Cliente)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
            transaccion=session.beginTransaction();
            if(insert==null){
               insert=new Cliente();
               insert.setEstado(true);
               insert.setNombreCliente(nombreCliente);
               insert.setApellidoCliente(apellidoCliente);
               insert.setDireccionCliente(direccionCliente);
               insert.setCiudadCliente(ciudadCliente);
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
    public static boolean update(Integer codigoCliente,String nombreCliente,String apellidoCliente,String direccionCliente,String ciudadCliente,Integer codigoUsuario){
        Date fechaModifica=new Date();
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("codigoCliente",codigoCliente));
        criteria.add(Restrictions.eq("estado", true));
        Cliente update=(Cliente)criteria.uniqueResult();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
        if(update!=null){
            update.setNombreCliente(nombreCliente);
            update.setApellidoCliente(apellidoCliente);
            update.setDireccionCliente(direccionCliente);
            update.setCiudadCliente(ciudadCliente);
            update.setUsuarioModificacion(codigoUsuario);
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
    
  public static boolean anular (Integer codigoCliente,Integer usuarioAnula){
   boolean bandera=false;
   Date fecha=new Date();
   Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
   Criteria criteria=session.createCriteria(Cliente.class);
   criteria.add(Restrictions.eq("codigoCliente",codigoCliente));
   criteria.add(Restrictions.eq("estado", true));
   Cliente anular=(Cliente)criteria.uniqueResult();
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
   public static List<Cliente>universo(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Cliente.class);
       criteria.add(Restrictions.eq("estado", true));
       criteria.addOrder(Order.desc("codigoCliente"));
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en consulta cliente "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 
   
 public static List<Cliente>reporteCliente(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Cliente.class);
       criteria.add(Restrictions.eq("estado", true));
         criteria.setProjection(Projections.projectionList()
                .add(Projections.property("nombreCliente"))
                .add(Projections.property("apellidoCliente"))
                .add(Projections.property("direccionCliente"))
                .add(Projections.property("ciudadCliente"))
        );
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en consulta cliente "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   }    
   
    public static List<Cliente>selectReporte(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Cliente.class);
       criteria.add(Restrictions.eq("estado", true));
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en consulta cliente "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 
}
