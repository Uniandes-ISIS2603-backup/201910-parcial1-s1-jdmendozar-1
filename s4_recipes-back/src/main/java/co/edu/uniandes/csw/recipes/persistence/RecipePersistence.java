/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.persistence;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CesarF
 */

@Stateless
public class RecipePersistence {
    private static final Logger LOGGER = Logger.getLogger(RecipePersistence.class.getName());
    
    @PersistenceContext(unitName = "recipesPU")
    protected EntityManager em;
    
    
  
    public RecipeEntity find(Long id) {
        return em.find(RecipeEntity.class, id);
    }
    
     /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param name: Nombre de la editorial que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public RecipeEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando receta por nombre ", name);
        // Se crea un query para buscar recetas con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From RecetaEntity e where e.name = :name", RecipeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<RecipeEntity> sameName = query.getResultList();
        RecipeEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar receta por nombre ", name);
        return result;
    }
    
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param recipeEntity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public RecipeEntity createRecipe(RecipeEntity recipeEntity) {
        LOGGER.log(Level.INFO, "Creando una editorial nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la receta en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(recipeEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una receta nueva");
        return recipeEntity;
    }
    
    

}
