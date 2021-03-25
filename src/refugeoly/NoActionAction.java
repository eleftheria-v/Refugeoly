/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.Action;
import refugeoly.Refugee;
import java.io.Serializable;

/**
 *
 * @author categ
 */
public class NoActionAction implements Action,Serializable{

    @Override
    public void act(Refugee refugee) {
        System.out.println("Starting the game!");
    }
    
}
