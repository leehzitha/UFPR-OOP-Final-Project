package pizzaria.model.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import java.util.List;

/**
 *
 * @author dias
 * @param <T>
 */
public interface Dao<T> {
    public void add(T object) throws Exception; 
    public List<T> getAll() throws Exception;
    public T getById(long id) throws Exception;
    public void update(T object) throws Exception;
    public void delete(T object) throws Exception;
    public void deleteAll() throws Exception;
}
