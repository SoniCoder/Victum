/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import victum.Inspections.*;


/**
 * FXML Controller class
 *
 * @author Addict
 */
public class SteelInspectionController implements Initializable {
    @FXML
    private TextField desc;
    @FXML
    private TextField trussType;
    @FXML
    private TextField bedType;
    @FXML
    private TextField span;
    @FXML
    private TextField datePaint;
    @FXML
    private TextField section;
    @FXML
    private TextArea nameOfficial;
    @FXML
    private TextArea condStructure;
    @FXML
    private TextArea actionTaken;
    @FXML
    private TextArea initialInspector;
    @FXML
    private TextArea detailRepair;
    
    //Non UI vars
    public static SteelInspectionController inst;
    /**
     * Initializes the controller class.
     */
    public void save(boolean update){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mansarovar","root","dbz123"); 
            Statement stmt = con.createStatement();
            stmt.execute(String.format("insert into steelstructure values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s','%s')",desc.getText(), trussType.getText(), bedType.getText(), span.getText(), datePaint.getText(), section.getText(), nameOfficial.getText(), condStructure.getText(), actionTaken.getText(), initialInspector.getText(), detailRepair.getText(), Filter.date.toString(), Filter.insFreq));
            con.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            }        
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inst = this;
// TODO
    }    
    public void show(SteelInspection ins){
        desc.setText(ins.desc);
                trussType.setText(ins.trussType);
                bedType.setText(ins.bedType);
                span.setText(ins.span);
                datePaint.setText(ins.datePaint);
                section.setText(ins.section);
                nameOfficial.setText(ins.nameOfficial);
                condStructure.setText(ins.condStructure);
                actionTaken.setText(ins.actionTaken);
                initialInspector.setText(ins.initialInspector);
                detailRepair.setText(ins.detailRepair);
    }
    
}
