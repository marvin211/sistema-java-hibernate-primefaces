/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Pojos.Categoria;
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
public class CrudCategoria {
        public static boolean insert(String nombreCategoria,String descripcionCategoria, Integer usuarioIngreso){
        boolean bandera=false;
        Date fechaIngreso=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Categoria.class);
        criteria.add(Restrictions.eq("nombreCategoria",nombreCategoria));
        Categoria insert=(Categoria)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
            transaccion=session.beginTransaction();
            if(insert==null){
               insert=new Categoria();
               insert.setEstado(true);
               insert.setNombreCategoria(nombreCategoria);
               insert.setDescripcionCategoria(descripcionCategoria);
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
        
    public static boolean update(Integer codigoCategoria,String nombreCategoria,String descripcionCategoria,Integer usuarioModificacion){
        Date fechaModifica=new Date();
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Categoria.class);
        criteria.add(Restrictions.eq("codigoCategoria",codigoCategoria));
        criteria.add(Restrictions.eq("estado", true));
        Categoria update=(Categoria)criteria.uniqueResult();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
        if(update!=null){
            update.setNombreCategoria(nombreCategoria);
            update.setDescripcionCategoria(descripcionCategoria);
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
    
  public static boolean anular (Integer codigoCategoria,Integer usuarioAnula){
   boolean bandera=false;
   Date fecha=new Date();
   Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
   Criteria criteria=session.createCriteria(Categoria.class);
   criteria.add(Restrictions.eq("codigoCategoria",codigoCategoria));
   criteria.add(Restrictions.eq("estado", true));
   Categoria anular=(Categoria)criteria.uniqueResult();
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
  
   public static List<Categoria>universo(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(Categoria.class);
       criteria.add(Restrictions.eq("estado", true));
        criteria.addOrder(Order.desc("codigoCategoria"));
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
