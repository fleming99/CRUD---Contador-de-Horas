/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rafael.contadorhoras;

import java.sql.SQLException;

/**
 * Interface responsável pelos mecanismos de persistência de dados..
 * @author Rafael Fleming
 * @param <T> Objeto que será utilizado para inserção manipulação de dados.
 */
public interface Repository<T> {
    
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    T create(T entity)  throws SQLException;
    void read() throws SQLException;
    void delete(long id) throws SQLException;
}
