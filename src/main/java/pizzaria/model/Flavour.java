package pizzaria.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class Flavour {
    private Long id;

    private PizzaType pizzaType;
    private String name;
    
    public Flavour(PizzaType pizzaType, String name){
        this.pizzaType = pizzaType;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public PizzaType getType(){
        return this.pizzaType;
    }
    
    public String getName(){
        return this.name;
    }
}
