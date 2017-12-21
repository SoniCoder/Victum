/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Addict
 */
public class DataSet {
    static public ArrayList<String> Stations;
    static{
        Stations = new ArrayList<String>();
        Collections.addAll(Stations, "All Stations","Mansarovar", "NATM", "VivekVihar", "ShyamNagar", "RamNagar", "CivilLines", "RailwayStation", "SindhiCamp", "Chandpole");
    }
}
