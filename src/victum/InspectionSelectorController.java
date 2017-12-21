/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum;

import java.awt.Event;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import victum.Inspections.*;


/**
 * FXML Controller class
 *
 * @author Addict
 */
public class InspectionSelectorController implements Initializable {
    //Annotations
    @FXML
    private SubScene form_scene;
    @FXML
    private ComboBox station_cb;
    @FXML
    private ComboBox inspection_cb;
    @FXML
    private ComboBox datewise_cb;
    @FXML
    private ComboBox monthMonthwise_cb;
    @FXML
    private ComboBox yearMonthwise_cb;
    @FXML
    private ComboBox yearYearwise_cb;
    @FXML
    private DatePicker main_dp;
    @FXML
    private ListView match_lv;
    @FXML
    private ListView notify_lv;
    @FXML
    private ComboBox frequency_cb;
    
    //Non Annotations
    Inspection selection;
    static ArrayList<Inspection> matchIns;
    static{
        matchIns = new ArrayList<Inspection>();
    }
    public void quit(ActionEvent event){
        System.exit(0);
    }
    
    void refreshFilter(){
        Filter.date = main_dp.getValue();
        Filter.insCategory = inspection_cb.getSelectionModel().getSelectedItem().toString();
        Filter.insFreq = frequency_cb.getSelectionModel().getSelectedItem().toString();
        Filter.station = station_cb.getSelectionModel().getSelectedItem().toString();     
        Filter.datewise = datewise_cb.getSelectionModel().getSelectedItem().toString();
        Filter.monthMonthwise = Integer.toString(monthMonthwise_cb.getSelectionModel().getSelectedIndex()+1);
        Filter.yearMonthwise = yearMonthwise_cb.getSelectionModel().getSelectedItem().toString();
        Filter.yearYearwise = yearYearwise_cb.getSelectionModel().getSelectedItem().toString();
        
    }
    
    public void saveHandler(ActionEvent event){
       SteelInspectionController.inst.save(false);
       datewise_cb.fireEvent(new ActionEvent());
    }
    public void updateHandler(ActionEvent event){
       // SteelInspectionController.sicInst.save(true);
    }
    public void update(ActionEvent event){
        refreshFilter();
        System.out.println("Update fired");
        //String datewise = datewise_cb.getSelectionModel().getSelectedItem().toString();
        switch(Filter.datewise){
            case "Datewise":
                main_dp.setVisible(true);
                monthMonthwise_cb.setVisible(false);
                yearMonthwise_cb.setVisible(false);
                yearYearwise_cb.setVisible(false);
                break;
            case "Monthwise":
                main_dp.setVisible(false);
                monthMonthwise_cb.setVisible(true);
                yearMonthwise_cb.setVisible(true);
                yearYearwise_cb.setVisible(false);
                break;
            default:
                main_dp.setVisible(false);
                monthMonthwise_cb.setVisible(false);
                yearMonthwise_cb.setVisible(false);
                yearYearwise_cb.setVisible(true);
                break;
        }
        matchIns = Inspection.parseInspection();
        if(matchIns.size()==0)
        match_lv.setItems(FXCollections.observableArrayList("No Matches"));
        else{
            match_lv.setItems(FXCollections.observableArrayList(matchIns));
        }
        matchIns.clear();
        String insSelection = inspection_cb.getSelectionModel().getSelectedItem().toString();
        String res = "BuildingInspection.fxml";
        switch(insSelection){
            case "Building" : res = "BuildingInspection.fxml";
                                break;
            case "Structural Steel" : res = "SteelInspection.fxml";
        }
        try{
            form_scene.setRoot(FXMLLoader.load(getClass().getResource(res)));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //Non UI Initializations
        
        //UI Initializations

        station_cb.setItems(FXCollections.observableArrayList(DataSet.Stations));
        
        inspection_cb.setItems(FXCollections.observableArrayList("Building", "Structural Steel"));
        datewise_cb.setItems(FXCollections.observableArrayList("Datewise","Monthwise","Yearwise"));
        frequency_cb.setItems(FXCollections.observableArrayList("Routine","Regular", "Detailed", "All Frequencies"));
        
        monthMonthwise_cb.setItems(FXCollections.observableArrayList("Jan","Feb","March","April","May","June","July","Aug","Sept","Oct","Nov","Dec"));
        yearMonthwise_cb.setItems(FXCollections.observableArrayList("2014","2015","2016","2017","2018","2019","2020"));
        yearYearwise_cb.setItems(FXCollections.observableArrayList("2014","2015","2016","2017","2018","2019","2020"));

        notify_lv.setItems(FXCollections.observableArrayList("No Notifications"));
        
        match_lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Inspection>() { 
            @Override
            public void changed(ObservableValue<? extends Inspection> observable, Inspection oldValue, Inspection newValue) {
                if(match_lv.getItems().get(0)!="No Notifications")
                SteelInspectionController.inst.show((SteelInspection)newValue);
                }
            });
        
        inspection_cb.getSelectionModel().clearAndSelect(1);
        datewise_cb.getSelectionModel().clearAndSelect(0);
        frequency_cb.getSelectionModel().clearAndSelect(1);
        station_cb.getSelectionModel().clearAndSelect(1);
        main_dp.setValue(LocalDate.now());
        
        monthMonthwise_cb.getSelectionModel().select(LocalDate.now().getMonthValue());
        yearMonthwise_cb.getSelectionModel().select(Integer.toString(LocalDate.now().getYear()));
        yearYearwise_cb.getSelectionModel().select(Integer.toString(LocalDate.now().getYear()));
        
        datewise_cb.fireEvent(new ActionEvent());
                        
    }    
    
}
