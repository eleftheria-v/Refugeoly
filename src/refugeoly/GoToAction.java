/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import java.io.Serializable;

/**
 *
 * @author categ
 */
public class GoToAction implements Action ,Serializable{

    @Override
    public void act(Refugee refugee) {
        int n=refugee.getSquare().getNumber();
        
        if((n==4)||(n==5)||(n==38)){
        refugee.moveToForAction(0);
        }
        else if(n==7){
        refugee.moveToForAction(10);
        }
        else if(n==15){
        refugee.moveToForAction(5);
        }
        else if(n==18){
        refugee.moveToForAction(22);
        }
        else if(n==23){
        refugee.moveToForAction(29);
        }
        else if(n==25){
        refugee.moveToForAction(15);
        }
        else if(n==30){
        refugee.moveToForAction(24);
        }
        else if(n==35){
        refugee.moveToForAction(25);
        }
        else if(n==33){
        refugee.moveToForAction(17);
        }
      
    }
}
