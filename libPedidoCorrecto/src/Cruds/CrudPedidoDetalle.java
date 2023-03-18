/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Pojos.PedidoDetalle;
import Pojos.PedidoDetalleId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author madel
 */
public class CrudPedidoDetalle {
   
       public static List<PedidoDetalle>universo(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(PedidoDetalle.class);
       criteria.createAlias("producto", "p");
       criteria.addOrder(Order.desc("id"));
       lista=criteria.list();
       }catch(HibernateException e){
           System.out.println("error en consulta cliente "+e);
       }
       finally{
           session.getTransaction().commit();
       }
       return lista;
   } 
       
       public static List<PedidoDetalle>universo2(){
       List lista=null;
       Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
       try{ 
       session.beginTransaction();
       Criteria criteria=session.createCriteria(PedidoDetalle.class);
       criteria.createAlias("producto", "p");
       criteria.setProjection(Projections.projectionList()
               .add(Projections.groupProperty("p.nombreProducto").as("nombreProducto"))
               .add(Projections.groupProperty("p.descripcionProducto").as("descripcionProducto")) 
               .add(Projections.groupProperty("cantidad").as("cantidad"))
               .add(Projections.groupProperty("precio").as("precio"))
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
       
       public static boolean insert(Integer codigoProducto, Integer cantidad, BigDecimal precio){
        boolean bandera=false;
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(PedidoDetalle.class);
        
        //por cada numero de factura puede aparecer nada ma sun producto
        criteria.add(Restrictions.eq("id.noFactura",2));
         criteria.add(Restrictions.eq("id.codigoProducto",codigoProducto));
        PedidoDetalle insert=(PedidoDetalle)criteria.uniqueResult();
        Transaction transaccion=null;
        try{
            transaccion=session.beginTransaction();
            if(insert==null){
               insert=new PedidoDetalle();
               PedidoDetalleId id = new PedidoDetalleId();
               id.setCodigoProducto(codigoProducto);
               id.setNoFactura(2);
               
               insert.setId(id);
               
               insert.setCantidad(cantidad);
               insert.setPrecio(precio);
        
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
    
        public static List<PedidoDetalle>reporteProductoMejor(){
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query=session.createSQLQuery("call reporteProductoMejor();");
        List<PedidoDetalle>listaDatos=query.list();
        session.getTransaction().commit();
        return listaDatos;
    }

}
