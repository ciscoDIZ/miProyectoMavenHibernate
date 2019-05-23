/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciscodiz.miproyectomavenhibernate.modelo;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author carlos
 */
public class ManejarDB {

    static EntityManagerFactory emf;

    public static List<Employees> obtenerEmpleadosPorFecha(String fechaInicio, String fechaFin){
        List<Employees> resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et =em.getTransaction();
        try {
            et.begin();
            Date date1 = Date.valueOf(fechaInicio);
            Date date2 = Date.valueOf(fechaFin);
            resultado = em.createNamedQuery("Employees.buscarEmpleadoPorFecha")
                    .setParameter("fechaInicio", date1)
                    .setParameter("fechaFin", date2)
                    .getResultList();
            et.commit();
            
        } catch (Exception e) {
            et.rollback();
        }
        
        return resultado;
    }
    
    public static List<Customers> obtenerClientes(){
        List<Customers> resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            resultado = em.createNamedQuery("Customers.findAll", Customers.class)
                    .getResultList();
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
        return resultado;
    } 
    
    public static EntityManager crearEntityManager() {
        if (emf == null || (emf != null && !emf.isOpen())) {
            emf = Persistence.createEntityManagerFactory("unidadPersistencia");
        }
        return emf.createEntityManager();
    }

    public static List<Categories> obtenerCategories() {
        List<Categories> resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            resultado = em.createNamedQuery("Categories.findAll", Categories.class)
                    .getResultList();
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();
        return resultado;
    }



    public static List<Employees> buscarEmpleadosPorNombre(String nombre) {
        List<Employees> resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            resultado = em.createNamedQuery("Employees.findAllByName", Employees.class)
                    .setParameter("firstName", "%"+nombre+"%")
                    .getResultList();
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();
        return resultado;
    }

    
    public static Employees buscarEmpleado(int id) {
        Employees resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            resultado = em.createNamedQuery("Employees.findByEmployeeID", Employees.class)
                    .setParameter("employeeID", id)
                    .getSingleResult();
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();
        return resultado;
    }
    
    
    public static void agregarShipper(Shippers s){
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(s);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();        
        
    }


        
    public static void modificarShipper(Shippers s){
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(s);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();        
        
    }
    
    
    public static Shippers buscarShipper(int id) {
        Shippers resultado = null;
        EntityManager em = crearEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            resultado = em.createNamedQuery("Shippers.findByShipperID", Shippers.class)
                    .setParameter("shipperID", id)
                    .getSingleResult();
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
        em.close();
        return resultado;
    }

}
