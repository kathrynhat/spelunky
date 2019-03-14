/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelunky;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Inglor
 */
public class RoomList {
    //Grid of rooms
    private ArrayList<Room> rooms;
    //Prevents more than one of these from spawning
    private boolean hasShop;
    private boolean hasVault;
    private boolean hasAltar;
    private boolean hasGoldenIdol;
    private boolean hasKissingBooth;
    private boolean hasWheelOfFortune;
    private boolean hasHandForHire;
    
    public RoomList(){
        this.rooms = new ArrayList();
        this.hasShop = false;
        this.hasVault = false;
        this.hasAltar = false;
        this.hasGoldenIdol = false;
        this.hasKissingBooth = false;
        this.hasWheelOfFortune = false;
        this.hasHandForHire = false;
        
        //populate list with "empty" rooms
        for(int i=0;i<16;i++){
            rooms.add(new Room());
        }
    }
    
    //Function that sets the critical path for the map: the path that the player
    //can use to reach the exit without requiring ropes or bombs to advance
    public void generatePath(){
        //Set up random generator and place entrance
        Random rand = new Random();
        int entrance = rand.nextInt(3);
        this.rooms.set(entrance, new Room("Entrance"));
        this.rooms.get(entrance).setCriticalPath();
        
        boolean exitMade = false;
        int currentRoom = entrance;
        
        //*********************************************************
        System.out.println("entrance set at " + currentRoom);
        //*********************************************************
        while(!exitMade){
            int num = rand.nextInt(3);
            
            if(num==0){
                //*************************************************
                System.out.println("0 was chosen");
                //*************************************************
                //Legal move check
                if((currentRoom+1)>0&&(currentRoom+1)<=15){
                    //Set right edge to exit if it tries to go off game board to the right
                    if(currentRoom==15){
                        this.rooms.set(currentRoom, new Room("Exit"));
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Exit set to " + currentRoom);
                        //*************************************************
                        exitMade = true;   
                    }
                    //Check to see if an edge is hit
                    else if(currentRoom==3||currentRoom==7||currentRoom==11){
                            //Move down, set currentRoom
                            this.rooms.set(currentRoom+4, new Room());
                            currentRoom+=4;
                            this.rooms.get(currentRoom).setCriticalPath();
                            //*************************************************
                            System.out.println("Moved down. Path set at " + currentRoom);
                            //*************************************************
                    }
                    //Place a room to the right (if it is not the entrance)
                    else{
                        this.rooms.set(currentRoom+1, new Room());
                        currentRoom++;
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Moved right. Path set at " + currentRoom);
                        //*************************************************
                    }
                }

            }
            if(num==1){
                //*************************************************
                System.out.println("1 was chosen");
                //*************************************************
                //Legal move check
                if((currentRoom-1)>0&&(currentRoom-1)<=15){
                    //Set left edge to exit if it tries to go off game board to the left
                    //Only set this room if the room is not the entrance
                    if(currentRoom==12){
                        this.rooms.set(currentRoom, new Room("Exit"));
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Exit set at " + currentRoom);
                        //*************************************************
                        exitMade = true;
                    }
                    //Check to see if an edge is hit
                    else if(currentRoom==0||currentRoom==4||currentRoom==8){
                            //Move down, set currentRoom
                            this.rooms.set(currentRoom+4, new Room());
                            currentRoom+=4;
                            this.rooms.get(currentRoom).setCriticalPath();
                            //*************************************************
                            System.out.println("Moved down. Path set at " + currentRoom);
                            //*************************************************
                    }
                    //Place a room to the left if not the entrance
                    else{
                        this.rooms.set(currentRoom-1, new Room());
                        currentRoom--;
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Moved left. Path set at " + currentRoom);
                        //*************************************************
                    }
                }
            }
            if(num==2){
                //*************************************************
                System.out.println("2 was chosen");
                //*************************************************
                //Legal Move check
                if((currentRoom+4)>0&&(currentRoom+4)<=15){
                    //If we're at the bottom row, set exit to currentRoom
                    if(currentRoom>=12){
                        this.rooms.set(currentRoom, new Room("Exit"));
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Exit set to " + currentRoom);
                        //*************************************************
                        exitMade = true; 
                    }
                    //Otherwise, move down
                    else{
                        this.rooms.set(currentRoom+4, new Room());
                        currentRoom+=4;
                        this.rooms.get(currentRoom).setCriticalPath();
                        //*************************************************
                        System.out.println("Moved down. Path set at " + currentRoom);
                        //*************************************************
                    }
                }
            }
        }
    } 
    
    //Assign a room type to rooms that are off the critical path:
    //Shop, Altar, Vault, or nothing
    public void assignEmptyRooms(){
        //All rooms on the critical path are set as occupied
        for(Room rm: this.rooms){
            if(rm.isCriticalPath()){
                rm.setOccupied();
            }
        }
        
        //If the room is empty, there is a chance that it could be a shop, altar or vault
        for(Room rm: this.rooms){
            if(rm.roomIsEmpty()){
                double percent = Math.random();
                if(percent>=0&&percent<=.1&&!this.hasShop){
                    rm.setText("Shop");
                    this.hasShop = true;
                }
                else if(percent>.3&&percent<=.35&&!this.hasAltar){
                    rm.setText("Altar");
                    this.hasAltar = true;
                }
                else if(percent>.5&&percent<=.52&&!this.hasVault){
                    rm.setText("Vault");
                    this.hasVault = true;
                }
                else if(percent>.53&&percent<=.65&&!this.hasGoldenIdol){
                    rm.setText("Golden Idol");
                    this.hasGoldenIdol = true;
                }
                else if(percent>.63&&percent<=.64&&!this.hasKissingBooth){
                    rm.setText("Kissing Booth");
                    this.hasKissingBooth = true;
                }
                else if(percent>.64&&percent<=.67&&!this.hasWheelOfFortune){
                    rm.setText("Wheel of Fortune");
                    this.hasWheelOfFortune = true;
                }
                else if(percent>.67&&percent<=.69&&!this.hasHandForHire){
                    rm.setText("Hand for Hire");
                    this.hasHandForHire = true;
                }
            }        
        }
    }
    
    //Damsel, Coffin
    public void spawnDamsel(){
        Random rand = new Random();
        int damselLocation = rand.nextInt(15);
        this.rooms.get(damselLocation).setDamsel();
    }
    
    //Bat, snake, spider, python
    public void spawnEnemies(){
        //For each room, assign num 0-4 for how many enemies will be in the room
        //then add in corresponding number of enemies
        for(Room rm: this.rooms){
            Random rand = new Random();
            int numOfEnemies = rand.nextInt(5);
            if(numOfEnemies>0){
                for(int i=0;i<=numOfEnemies;i++){
                    double percent = Math.random();
                    if(percent>=0&&percent<=.1){
                        rm.addEnemies("Snake");
                    }
                    else if(percent>.1&&percent<=.13){
                        rm.addEnemies("Python");
                    }
                    else if(percent>.2&&percent<=.3){
                        rm.addEnemies("Spider");
                    }
                    else if(percent>.3&&percent<=.34){
                        rm.addEnemies("Purple Spider");
                    }
                    else if(percent>.35&&percent<=.37){
                        rm.addEnemies("Giant Spider");
                    }
                    else if(percent>.37&&percent<=.5){
                        rm.addEnemies("Bat");
                    }
                    else if(percent>.5&&percent<=.65){
                        rm.addEnemies("Skeleton");
                    }
                    else if(percent>.7&&percent<=.72){
                        rm.addEnemies("Scorpion");
                    }
                    else if(percent>.75&&percent<=.80){
                        rm.addEnemies("Caveman");
                    }
                }
            }
        }
    }
    
    //Spawns environmental obstacles (arrow traps, spikes, explosive crates) and item crates
    public void spawnObstacles(){
        for (Room rm: this.rooms){
            Random rand = new Random();
            int numOfObstacles = rand.nextInt(5);
            if(numOfObstacles>0){
                for(int i=0;i<=numOfObstacles;i++){
                    double percent = Math.random();
                    if(percent>=0&&percent<=.10){
                        rm.addObstacles("Arrow Trap");
                    }
                    else if(percent>.10&&percent<=.20){
                        rm.addObstacles("Spikes");
                    }
                    else if(percent>.20&&percent<=.30){
                        rm.addObstacles("Explosive Crate");
                    }
                    else if(percent>.30&&percent<=.40){
                        rm.addObstacles("Item Crate");
                    }
                }
            }
        }
    }
    
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
}