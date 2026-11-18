package pizzaria.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *
 * @author Vinicius Dias
 */
public class Client {

    private Long id;
    private String name;
    private String lastname;
    private String telephone;
    private ArrayList<Order> orders = new ArrayList<>();

    public Client (String name, String lastname, String telephone) {
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
    }
    
    public Client(Long id, String name, String lastname, String telephone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
    }
 

    //getters e setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() { return name;}
    public void setName(String name) {this.name = name;}

    public String getLastname() { return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getTelephone() { return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}

    //public void newOrder(Order order){
    //    orders.add(order);
    //}
    
    public void addOrder(Order order) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return name + " " + lastname + " - " + telephone;
    }
}
