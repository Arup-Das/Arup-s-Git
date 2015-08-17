package com.example.demo;

public class Place {
    
    //private variables
    String _place;
     
    // Empty constructor
    public Place(){
         
    }
     
 // constructor
    public Place(String place){
        this._place = place;
    }
    // getting place
    public String getPlace(){
        return this._place;
    }
     
    // setting place
    public void setPlace(String place){
        this._place = place;
    }
}