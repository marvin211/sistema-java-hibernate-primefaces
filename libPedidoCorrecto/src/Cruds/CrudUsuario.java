/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Pojos.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Usuario
 */
public class CrudUsuario {
    public static Usuario select(String nombreUsuario){
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.add(Restrictions.eq("usuario", nombreUsuario));
        criteria.setMaxResults(1);
        Usuario select=(Usuario)criteria.uniqueResult();
        if(select==null){
            select=new Usuario();
            select.setContrasenia(null);
        }
        session.close();
        return select;
    }
}
