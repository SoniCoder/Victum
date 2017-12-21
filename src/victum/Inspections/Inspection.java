/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum.Inspections;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import victum.DataSet;
import victum.Filter;

/**
 *
 * @author Addict
 */
abstract public class Inspection {
    static ArrayList<Inspection> match;
    static{
        match = new ArrayList<Inspection>();
    }
    String freqtype;
    String date;
    Inspection(String date, String freqType){
        this.freqtype = freqType;
        this.date = date;
    }
    
    static public String getDateConstraint(){
        switch(Filter.datewise){
            case "Datewise":
                return " where date="+Filter.date;
            case "Monthwise":
                return " where month(date)="+Filter.monthMonthwise+" and year(date)="+Filter.yearMonthwise;
            default:
                return " where year(date)="+Filter.yearYearwise;
        }
    }
    
    static public ArrayList<Inspection> parseInspection(){
        String dateConstraint = getDateConstraint();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            if(Filter.station=="All Stations"){
            for(int i=1; i<DataSet.Stations.size();i++){
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mansarovar","root","dbz123"); 
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from steelstructure"+dateConstraint);
                con.close();
                }
            }else{
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mansarovar","root","dbz123"); 
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(String.format("select * from %s"+dateConstraint,"steelstructure"));
                SteelInspection.parseResultSet(rs);
                con.close();   
            }
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            }
        return match;
    }
    
    abstract String getInspectionType();   
      
}
