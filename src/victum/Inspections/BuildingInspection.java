/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum.Inspections;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Addict
 */
public class BuildingInspection extends Inspection{
    BuildingInspection(String date, String freqType){
        super(date, freqType);
    }
    String getInspectionType(){
        return "Building";
    }
}
