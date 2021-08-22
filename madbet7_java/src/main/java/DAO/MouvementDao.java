/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Mouvement;

/**
 *
 * @author KEVIN ALLAIN
 */
public class MouvementDao {
    public void add(Mouvement mouvement) throws Exception {
        EntityManager em = new Connexion().getEntityManager();
        try{
           mouvement.setIdMouvement(null);
           em.persist(mouvement);
           em.flush();
           em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    
    public Mouvement getWithEm(int mouvementId) throws Exception{
        EntityManager em = new Connexion().getEntityManager();
        try {
            Mouvement mouvement = em.find(Mouvement.class, mouvementId);
            if(mouvement == null) throw new NotFoundException();
            em.detach(mouvement);

            return mouvement;
        } catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public Mouvement get(int mouvementId) throws Exception{
        EntityManager em = new Connexion().getEntityManager();
        try {
            Query query = em.createQuery("SELECT t FROM Mouvement t where t.idMouvement = :id");
            query.setParameter("id", mouvementId);
            Mouvement mouvement = (Mouvement) query.getSingleResult();
            return mouvement;
        } catch(NoResultException e) {
            em.getTransaction().rollback();
            throw new NotFoundException();
        } finally {
            em.close();
        }
    }
    
    
    public void merge(Mouvement mouvement) throws Exception {
        EntityManager em = new Connexion().getEntityManager();
        try {
            Mouvement mouvementEntity = this.get(mouvement.getIdMouvement());
            mouvementEntity.setAmount(mouvement.getAmount());
            mouvementEntity.setIdBet(mouvement.getIdBet());
            mouvementEntity.setIdUser(mouvement.getIdUser());
            mouvementEntity.setType(mouvement.getType());
            em.merge(mouvementEntity);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void remove(int idMouvement) throws Exception {
        EntityManager em = new Connexion().getEntityManager();
        try {
            Mouvement mouvement = this.get(idMouvement);
            em.remove(em.contains(mouvement) ? mouvement : em.merge(mouvement));
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }
    
    public CustomList list(int page, int nb) throws Exception {
        EntityManager em = new Connexion().getEntityManager();
        try {
            Query query = em.createQuery("From Mouvement m");
            int count = this.count(em);
            int nbPage = count / nb;
            if(page > nbPage && page != 1) {
                throw new NotFoundException();
            }
            query.setFirstResult((page - 1) * nb);
            query.setMaxResults(nb);
            List liste = query.getResultList();
            CustomList list = new CustomList();
            list.setCountTotal(count);
            list.setCuurentPage(page);
            list.setNext(page +1 <= nbPage);
            list.setPrevious(page > 1 && page - 1 <= nbPage);
            list.setPerPage(nbPage);
            list.setList(liste);
            return list;
        } catch(Exception e) {
            throw e;
        }finally {
            em.close();
        }
    }
    
    public int count(EntityManager em) throws Exception {
        try {
            Query query = em.createQuery("SELECT count(m) From Mouvement m");
            Long count = (Long) query.getSingleResult();
            return count.intValue();
        } catch(Exception e) {
            throw e;
        }
    }
    
    
    public MouvementDAO()  {
    }
}
