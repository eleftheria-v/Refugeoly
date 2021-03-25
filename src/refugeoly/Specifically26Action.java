/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.Action;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author categ
 */
public class Specifically26Action implements Action ,Serializable{
    public static boolean k=true;
    @Override
    public void act(Refugee refugee) {
       refugee.giveMoney(1000);
       
       System.out.println("Option A: Pay $1500 to MafiaBank and roll dice. Option B: Don’t pay and stay 2 turns.");
       Scanner input=new Scanner(System.in);
       String option;
      do{
        System.out.println("Please press 'A' or 'B' .");
        option=input.next();
      }while(!option.equals("A")&&!option.equals("B"));
        if(option.equals("A")){
            refugee.giveMoney(1500);
            RollDiceAction rda=new RollDiceAction();
            rda.act(refugee);

        }
        else if(option.equals("B")){
            System.out.println("Stay here for 2 turns");
            k=false;            
        }
     
    }
    public static boolean stay(){
            return k;
    }
}
