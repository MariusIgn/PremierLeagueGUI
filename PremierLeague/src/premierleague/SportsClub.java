/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

/**
 *
 * @author ignat
 */
public abstract class SportsClub {
    String name;
    String location;
    
    SportsClub(String name, String location){
    this.name = name;
    this.location = location;
  
    }
   
    // Getters
    public String getName(){return name;}
    public String getLocation(){return location;}

    
    // Setters
    public void setName(String s){this.name = s;}
    public void setLocation(String s){this.location = s;}
}


