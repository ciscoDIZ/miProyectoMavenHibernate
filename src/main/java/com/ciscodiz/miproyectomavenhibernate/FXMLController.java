package com.ciscodiz.miproyectomavenhibernate;
import com.ciscodiz.miproyectomavenhibernate.modelo.Employees;
import com.ciscodiz.miproyectomavenhibernate.modelo.ManejarDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField txtFechaInicio;
    @FXML
    private TextField txtFechaFin;
    @FXML
    private ListView<Employees> lvResultado;
    private ObservableList<Employees> itemList;
    @FXML
    private Button btnBuscar;
    private TextArea txaResultado;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      itemList = FXCollections.observableArrayList();
      lvResultado.setItems(itemList);


    }    
/*
    @FXML
    private void btnBuscarOnAction(ActionEvent event) {
        
    }
*/

    @FXML
    private void btnBuscarOnAction(ActionEvent event) {
        ManejarDB.obtenerEmpleadosPorFecha(txtFechaInicio.getText(), txtFechaFin.getText()).forEach(e->{
            lvResultado.getItems().add(e);
        });
    }

    @FXML
    private void lvConsultaOnClick(MouseEvent event) {
        lvResultado.getItems().get(0);
    }
}
