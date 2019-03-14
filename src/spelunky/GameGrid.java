/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelunky;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Inglor
 */
public class GameGrid implements Runnable {
    private static int WIDTH = 320;
    private static int HEIGHT = 320;
    private JFrame frame;
    private ImageIcon icon;
    
    public GameGrid(){
        //this.icon = new ImageIcon("resources/red.png");
    }
    
    @Override 
    public void run(){
        frame = new JFrame("Spelunky");
        frame.setPreferredSize(new Dimension(1280, 1280));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container){
        JPanel panel = new JPanel(new GridLayout(4,4));
        frame.setContentPane(panel);
        
        //Create RoomList object containing all rooms for a level
        RoomList list = new RoomList();
        //Generate the critical path
        list.generatePath();   
        list.assignEmptyRooms();
        list.spawnEnemies();
        list.spawnObstacles();
        list.spawnDamsel();
        
        for(Room rm: list.getRooms()){
            //Create new panel for each section of the grid with BorderLayout to display text
            JPanel p = new JPanel(new BorderLayout());
            p.setBorder(LineBorder.createBlackLineBorder());

            //p.setBorder(EmptyBorder);
            if(rm.isCriticalPath()){
                p.setBackground(Color.red);
                if(rm.hasText()){
                    JLabel title = new JLabel(rm.getText(), SwingConstants.CENTER);
                    title.setBorder(LineBorder.createBlackLineBorder());
                    p.add(title, BorderLayout.NORTH);
                }
            }
            else{
                p.setBackground(Color.PINK);
                if(rm.hasText()){
                    JLabel title = new JLabel(rm.getText(), SwingConstants.CENTER);
                    title.setBorder(LineBorder.createBlackLineBorder());
                    p.add(title, BorderLayout.PAGE_START);  
                }
            }
            
            if(!rm.getEnemies().isEmpty()){
                JLabel enemies = new JLabel("<html>Enemies:<br/>"+rm.listEnemies()+"</html>", SwingConstants.CENTER);
                p.add(enemies, BorderLayout.CENTER);
            }
            if(!rm.getObstacles().isEmpty()){
                JLabel obstacles = new JLabel("<html>Environmental features:<br/>"+rm.listObstacles()+"</html>", SwingConstants.CENTER);
                p.add(obstacles, BorderLayout.WEST);
            }
            if(rm.containsDamsel()){
                JLabel damsel = new JLabel("Damsel Location", SwingConstants.CENTER);
                p.add(damsel, BorderLayout.EAST);
            }
            
            panel.add(p);
            }
    }  

    public JFrame getFrame(){
        return frame;
    }
}
