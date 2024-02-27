/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rafael.contadorhoras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por fazer o mecanismo de persistência de dados, implementando a interface
 * Repository (utilizando a instância da classe ContarHoras como argumento).
 * @author Rafael Fleming
 */
public class ContarHorasRepository implements Repository<ContarHoras> {
    
    private Connection connection;
    
    /**
     * Abre a conexão com o banco de dados. É utilizada antes de qualquer consulta
     * com o banco de dados.
     * @throws SQLException Se ocorrer uma falha ao abrir a conexão com o banco de dados.
     */
    @Override
    public void connect() throws SQLException {
        try{
            String url = "jdbc:mysql://localhost:3306/db_contador_horas";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException ex){
            System.out.println("Não foi possível se conectar ao banco de dados: " + ex.getMessage());
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados. É utilizada após qualquer consulta
     * com o banco de dados.
     * @throws SQLException Se ocorrer uma falha ao fechar a conexão com o banco de dados.
     */
    @Override
    public void disconnect() throws SQLException {
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }catch(SQLException ex){
            System.out.println("Não foi possível fechar a conexão com o banco de dados: " + ex.getMessage());
        }
    }

    /**
     * Método responsável por armazenar a sessão (instância de ContarHoras) no banco de dados.
     * @param sessao Instância de ContarHoras, responsável por fazer o contador de horas.
     * @return A instância da classe ContarHoras.
     * @throws SQLException Caso ocorra algum erro durante a criação da sessão no banco de dados.
     */
    @Override
    public ContarHoras create(ContarHoras sessao) throws SQLException{
        try{
            connect();
            String sql = "INSERT INTO dados_sessao (data_inicio_sessao, hora_inicio_sessao, data_saida_sessao, hora_saida_sessao, horas_trabalhadas_sessao, valor_hora, valor_recebido_sessao) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, sessao.getDataInicio());
            statement.setString(2, sessao.getHoraInicio());
            statement.setString(3, sessao.getDataFim());
            statement.setString(4, sessao.getHoraFim());
            statement.setString(5, sessao.getHorasTrabalhadas());
            statement.setDouble(6, sessao.getValorHora());
            statement.setDouble(7, sessao.getValorTotal());
            statement.executeUpdate();
            disconnect();
        }catch(SQLException ex){
            System.out.println("Não foi possível criar a sessão atual: " + ex.getMessage());
        }
        return sessao;
    }

    /**
     * Método responsável por ler todos as sessões armazenadas no banco de dados.
     * @throws SQLException Caso ocorra algum erro ao tentar obter as sessões do banco de dados.
     */
    @Override
    public void read() throws SQLException{
        try{
            connect();
            String sql = "SELECT * FROM dados_sessao";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) { // Move o cursor para a primeira linha (se houver)
                System.out.println(
                    """
                    ----------------------------------------
                        DADOS DA SESSÃO:
                        ID: %d.
                        Data Início: %s as %s.
                        Data Saída: %s as %s.
                        Horas Trabalhadas: %s.
                        Valor por Hora: %.2f.
                        Valor ganho na sessão: %.2f.
                    ----------------------------------------
                    """.formatted(resultSet.getInt("id_contador_horas"), resultSet.getString("data_inicio_sessao"), resultSet.getString("hora_inicio_sessao"), resultSet.getString("data_saida_sessao"),
                    resultSet.getString("hora_saida_sessao"), resultSet.getString("horas_trabalhadas_sessao"), resultSet.getDouble("valor_hora"),
                    resultSet.getDouble("valor_recebido_sessao")));
            }
            
            disconnect();
        }catch(SQLException ex){
            System.out.println("Não foi possível obter os dados do banco de dados: " + ex.getMessage());
        }
    }

    /**
     * Método responsável por deletar dados a partir do id. É realizado uma varredura (read)
     * antes de deletar algum item, para o usuário digitar o id da sessão que deseja deletar.
     * @param id Utilizado para poder buscar a sessão desejada e excluí-la.
     * @throws SQLException Caso ocorra algum erro ao deletar a sessão desejada.
     */
    @Override
    public void delete(long id) throws SQLException{
        try{
            connect();
            String sql = "DELETE FROM dados_sessao WHERE id_contador_horas = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            disconnect();
        }catch(SQLException ex){
            System.out.println("Não foi possível excluir a sessão desejada: " + ex.getMessage());
        }
    }
}
