/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    /**
     * Crea una receta en la persistencia.
     *
     * @param recipeEntity La entidad que representa la receta a
     * persistir.
     * @return La entiddad de la receta luego de persistirla.
     * @throws BusinessLogicException Si la receta a persistir ya existe.
     */
    public RecipeEntity createRecipe(RecipeEntity recipeEntity) throws BusinessLogicException {
        //LOGGER.log(Level.INFO, "Inicia proceso de creación de la receta");
        // Verifica la regla de negocio que dice que no puede haber dos recetas con el mismo nombre
        if (persistence.findByName(recipeEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Receta con el nombre \"" + recipeEntity.getName() + "\"");
        }
        
        if(recipeEntity.getName()==null|| recipeEntity.getName().equals("")|| recipeEntity.getName().length()>30)
        {
            throw new BusinessLogicException("La receta contiene un nombre no valido");
        }
        
         if(recipeEntity.getDescription()==null|| recipeEntity.getDescription().equals("")|| recipeEntity.getName().length()>150)
        {
            throw new BusinessLogicException("La receta contiene una descripción no valida");
        }
        
        // Invoca la persistencia para crear la receta
        persistence.createRecipe(recipeEntity);
        return recipeEntity;
    }
   


}
