/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.Action;
import java.io.Serializable;

/**
 *
 * @author categ
 */
public class WinAction implements Action,Serializable{

    @Override
    public void act(Refugee refugee) {
       System.out.println(refugee.getName()+"   Congratulations you won!");
    }
    
}
