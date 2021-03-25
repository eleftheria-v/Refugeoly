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
public class StayTurnAction implements Action,Serializable{

    public static boolean k;
  
    @Override
        public void act(Refugee refugee){
        int n=refugee.getSquare().getNumber();
        if((n==8)||(n==11)||(n==14)||(n==19)||(n==24)||(n==27)||(n==32)||(n==34)){
            System.out.println("You have to stay here for one turn!");
            k=false;
        }
        else{
            
            k=true;
        }
    }
    public static boolean play(){
        return k;
    }     
}
