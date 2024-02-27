/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rafael.contadorhoras;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por disponibilizar o método menu, que dispõe do menu para o usuário
 * e suas funcionalidades.
 * @author Rafael Fleming
 */
public class Menu {
    
    /**
     * Método menu, que dispõe do menu para o usuário e suas funcionalidades.
     * @throws java.sql.SQLException Caso ocorra algum erro ao tentar fazer a persistência com o banco de dados.
     */
    public static void menu() throws SQLException{
        
        // Instância da classe responsável pelo CRUD
        ContarHorasRepository crud = new ContarHorasRepository();
        Scanner input = new Scanner(System.in);
        
        // Variável responsável por receber a entrada do usuário e participar do tratemento de exceções.
        int counter;
        double value;
        
        System.out.println("""
                           ----------------------------------------
                                    WELCOME TO JOB COUNTER
                           
                                (1) Start a new session.
                                (2) See all the previous sessions.
                                (3) Delete session.
                                (4) Exit.
                           ----------------------------------------
                           """);
        
        // Obtém a entrada do usuário e faz o tratamento de exceções para receber apenas números.
        while(true){
            try{
                counter = input.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Valor digitado é invalido. Por favor, digite apenas números.");
            }
        }
        
        switch(counter){
            // CRUD - CREATE
            case 1 -> {
                
                System.out.println("Quantas horas deseja trabalhar?");
                while(true){
                    try{
                        counter = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Valor digitado é invalido. Por favor, digite apenas números.");
                    }
                }
                
                System.out.println("Qual o valor/hora dessa sessão?");
                while(true){
                    try{
                        value = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Valor digitado é invalido. Por favor, digite apenas números.");
                    }
                }
                
                ContarHoras contador = new ContarHoras(value);
                contador.start(counter);

                crud.create(contador);
            }
            //CRUD - READ ONLY
            case 2 -> crud.read();
            
            //CRUD - READ TO DELETE
            case 3 -> {
                
                crud.read();
                System.out.println("Digite o número do ID que deseja excluir:");
                
                // Obtém a entrada do usuário e faz o tratamento de exceções para receber apenas números.
                while(true){
                    try{
                        counter = input.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Valor digitado é invalido. Por favor, digite apenas números.");
                    }
                }
                
                crud.delete(counter);
            }
            // Encerra o sistema.
            case 4 -> System.exit(0);
        }
    }
}