/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rafael.contadorhoras;

import java.sql.SQLException;

/**
 * Classe Program, responsável por conter o método main.
 * @author Rafael Fleming
 */
public class Program {
    
    public static void main(String[] args) throws SQLException {
        
        //chama o menu em loop para poder haver a interatividade até o usuário decidir sair do programa.
        while(true){
            Menu.menu();
        }
    }
}

