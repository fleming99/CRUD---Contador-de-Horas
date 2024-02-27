/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rafael.contadorhoras;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe responsável por realizar o mecanismo de contador de horas.
 * @author Rafael Fleming
 */
public class ContarHoras {
    
    private final String dataInicio;
    private final String horaInicio;
    private String dataFim;
    private String horaFim;
    private String horasTrabalhadas;
    private double valorHora;
    private double valorTotal;

    /**
     * Construtor da classe ContarHoras.
     * 
     * Inicializa a data e horário em que se inicia o contador.
     * @param valorHora valor cobrado por hora na sessão atual.
     */
    public ContarHoras(double valorHora) {
        this.valorHora = valorHora;
        this.dataInicio = String.valueOf(LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()+ "-" + LocalDate.now().getYear());
        this.horaInicio = String.valueOf(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
    }
    
     /**
      * Método que inicializa o contador de horas.
      * @param qtdHoras Quantidade de horas que serão definidas no contador.
      */
    public void start(int qtdHoras){

        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        try {
            while (true) {
                // Aguarda 1 segundo.
                Thread.sleep(1000);

                // Incrementa os segundos.
                segundos++;

                System.out.println("%d:%d:%d".formatted(horas, minutos, segundos));

                // Atualiza os minutos e segundos se necessário
                if (segundos == 60) {
                    segundos = 00;
                    minutos++;
                }
                if (minutos == 60) {
                    minutos = 00;
                    horas++;
                }

                // Encerra o contador se as horas passadas foram iguais ao horário definido no contador.
                if(horas == qtdHoras){
                    horasTrabalhadas = "%d:%d:%d".formatted(horas, minutos, segundos);

                    valorTotal = valorHora * (double)(horas + (minutos >= 10 ? 1 : 0)); 

                    dataFim = String.valueOf(LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()+ "-" + LocalDate.now().getYear());
                    horaFim = String.valueOf(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());

                    System.out.println("Contador de horas interrompido.");
                    break;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Contador de horas interrompido devido a algum erro: " + ex.getMessage());
        }
    }
    
    public String getDataInicio() {
        return dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
