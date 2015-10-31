package nl.m4jit.framework.sqlaccess;

import java.util.*;
import javax.persistence.*;

public abstract class AbstractTable<T extends AbstractGateway>{
 
    private EntityManager em;
    
    protected AbstractTable(){
        
        em = TableManager.getInstance().getEntityManager();
    }
    
    public void insert(T gateway){
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(gateway);
        transaction.commit();
    }
    
    public void update(T gateway){
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(gateway);
        transaction.commit();
    }
    
    protected List executeQuery(String select, String where){
     
        Query query = em.createQuery(select + " " + where);
        return query.getResultList();
    }
    
    protected List executeQuery(String select, String where, Object[]... parameters) {

        Query query = em.createQuery(select +" "+ where);
        
        for(Object[] parameter : parameters){
            
            query.setParameter((String) parameter[0], parameter[1]);
        }
        
        return query.getResultList();
    }
    
    protected List executeNativeQuery(String sql, Object... parameters) {

        Query query = em.createNativeQuery(sql);
        
        int i = 0;
        
        for(Object parameter : parameters){
            
            query.setParameter(i, parameter);
            i++;
        }
        
        return query.getResultList();
    }
}
