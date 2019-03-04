/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */

public class IngredientDTO {
    String name;
    
    Long calories;
    
    Long id;

    public IngredientDTO() {
    }
    
    public IngredientDTO(IngredientEntity ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.calories = ingredient.getCalories();
    }
    
    public IngredientEntity toEntity() {
    IngredientEntity entity = new IngredientEntity();
    entity.setId(this.id);
    entity.setName(this.name);   
    entity.setCalories(this.calories);
    return entity;
    }
}
