/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author k_bar
 */

    public class DataSourceDB {
        private final String conn_url = "jdbc:mysql://localhost:3306/employees?zeroDateTimeBehavior=CONVERT_TO_NULL";
        private ArrayList<String> colNames = new ArrayList<String>();
        private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        
    public ObservableList<ObservableList<String>> getData() {
        return data;
    }

    public void setData(ObservableList<ObservableList<String>> data) {
        this.data = data;
    }


    public void setColNames(ArrayList<String> colNames) {
        this.colNames = colNames;
    }

    public ArrayList<String> getColNames() {
        return colNames;
    }
    
    public ObservableList<String> getRow(int i){
        if (i >= 0 && i < data.size())
            return data.get(i);
        else
            return null;
    }
    
    public int NumberOfRows(){
        return data.size();
    }
    public void DBCreate(String cmd){
        
        Connection conn = null;
        try {
            
           
            conn = DriverManager.getConnection(conn_url, "root", "4akqb7Ua");
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);
            ResultSetMetaData rsm = rs.getMetaData();
            int col = rsm.getColumnCount();
            
            colNames = new ArrayList<String>();
                for(int i = 1; i <= col; i++){
                    colNames.add(rsm.getColumnName(i));
            
            }
            
            data.clear();
            
            
            
            while (rs.next()){
                ObservableList row = FXCollections.observableArrayList();
                for(int i = 1; i <= col; i++){
                row.add(rs.getString(i));
                }
                
                data.add(row);
            }

             //stmt.setString();

            //stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
    public void printDB(){
        for (ObservableList<String> obl : data){
            for(String s : obl)
            {
                System.out.print(s + " ,");
            }
            System.out.print("\n");
        }
    }

    
}

