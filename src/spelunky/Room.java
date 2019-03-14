/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelunky;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Inglor
 */
public class Room {
    //Text indicating type of room
    private String roomText;
    //List of enemies
    private ArrayList<String> enemies;
    //List of obstacles
    private ArrayList<String> obstacles;
    //Room is part of criticalPath
    private boolean criticalPath;
    //Indicates if text is present or not
    private boolean hasText;
    //Indicate if room is empty or not
    private boolean isEmpty;
    //Room contains damsel
    private boolean containsDamsel;
    
    public Room(){
        this.enemies = new ArrayList();
        this.obstacles = new ArrayList();
        this.hasText = false;
        this.containsDamsel = false;
        this.criticalPath = false;
        this.isEmpty = true;
    }
    
    public Room(String roomText){
        this.roomText = roomText;
        this.enemies = new ArrayList();
        this.obstacles = new ArrayList();
        this.hasText = true;
        this.containsDamsel = false;
        this.criticalPath = false;
        this.isEmpty = false;
    }
    
    //Overloaded
    //Allows us to create rooms in the generate path function of RoomList and set as occupied
    public Room(String roomText, boolean isEmpty){
        this.roomText = roomText;
        this.enemies = new ArrayList();
        this.obstacles = new ArrayList();
        this.isEmpty = isEmpty;
        this.containsDamsel = false;
        this.criticalPath = false;
        this.hasText = true;
    }
    
    public void addEnemies(String enemy){
        this.enemies.add(enemy);
    }
    
    public void addObstacles(String obstacle){
        this.obstacles.add(obstacle);
    }
    
    public ArrayList getEnemies(){
        return this.enemies;
    }
    
    public ArrayList getObstacles(){
        return this.obstacles;
    }
    
    public void setDamsel(){
        this.containsDamsel = true;
    }

    public boolean containsDamsel(){
        return this.containsDamsel;
    }
    
    public String listEnemies(){
        String builder = "";
        for(String str:this.enemies){
            builder+=str+"<br/>";
        }
        return builder;
    }
    
    public String listObstacles(){
        String builder = "";
        for(String str:this.obstacles){
            builder+=str+"<br/>";
        }
        return builder;
    }
    
    public boolean hasText(){
        if (this.hasText){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Set isEmpty to false when it receives room assignment
    public void setOccupied(){
        this.isEmpty = false;
    }
    
    //Returns true if room is empty, false if occupied
    public boolean roomIsEmpty(){
        return this.isEmpty;
    }
    
    //Returns text indicating type of room
    public String getText(){
        return this.roomText;
    }
    
    //Set as part of critical path
    public void setCriticalPath(){
        this.criticalPath = true;
    }
    
    //Returns true if part of criticalPath
    public boolean isCriticalPath(){
        return this.criticalPath;
    }
    
    //Set text for type of room
    public void setText(String text){
        this.roomText = text;
        this.hasText = true;
    }
}
