/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum.Inspections;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Addict
 */
public class SteelInspection extends Inspection{
    public String desc;
    public String trussType;
    public String bedType;
    public String span;
    public String datePaint;
    public String section;
    public String nameOfficial;
    public String condStructure;
    public String actionTaken;
    public String initialInspector;
    public String detailRepair;
    
    SteelInspection(String desc, String trussType, String bedType, String span, String datePaint, String section, String nameOfficial, String condStructure, String actionTaken, String initialInspector, String detailRepair, String date, String freqType){
        super(date, freqType);
        this.desc = desc;
        this.trussType = trussType;
        this.bedType = bedType;
        this.span = span;
        this.datePaint  = datePaint;
        this.section = section;
        this.nameOfficial = nameOfficial;
        this.condStructure = condStructure;
        this.actionTaken = actionTaken;
        this.initialInspector = initialInspector;
        this.detailRepair = detailRepair;       
    }
    String getInspectionType(){
        return "SteelStructure";
    }
    
    static void parseResultSet(ResultSet rs){
        try{
            while(rs.next()){
                match.add(new SteelInspection(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public String toString(){
        return "SteelInspection - "+date+" - "+freqtype;
    }
}
