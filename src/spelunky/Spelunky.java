/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spelunky;

import javax.swing.SwingUtilities;

/**
 *
 * @author Inglor
 */
public class Spelunky {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameGrid grid = new GameGrid();
        SwingUtilities.invokeLater(grid);
    }
    
}
