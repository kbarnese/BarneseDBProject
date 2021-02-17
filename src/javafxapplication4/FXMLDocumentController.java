/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 *
 * @author k_bar
 */
public class FXMLDocumentController implements Initializable {
    
    DataSourceDB DB = new DataSourceDB();
    
    @FXML
    private Button button;
    @FXML
    private TextArea textArea;
    @FXML
    TableView<ObservableList<String>> tableView;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        DB.DBCreate(textArea.getText());
        tableView.getColumns().clear();
        tableView.getItems().clear();
        
        this.setTable();
        DB.printDB();
        
    }
    
    private void setTable(){
        //tableView.getColumns().clear();
        ArrayList<String> cols = DB.getColNames();
        System.out.println(DB.NumberOfRows() + " is num of rows1");
        for(int i = 0; i < cols.size(); i++){
            TableColumn<ObservableList<String>, String> col = new TableColumn(cols.get(i));
            final int j = i;
            col.setCellValueFactory(param -> 
                    new ReadOnlyObjectWrapper<>(param.getValue().get(j))
            );
            tableView.getColumns().addAll(col);  
            System.out.println(i);
        }
        System.out.println(DB.NumberOfRows() + " is num of rows2");
     
        for(int i = 0; i < DB.NumberOfRows(); i++){
            tableView.getItems().add(DB.getRow(i));
            System.out.println("HI" + i);
            
            }
            
        }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
