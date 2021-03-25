package refugeoly;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author categ
 */
public class GoBackAction implements Action,Serializable{

    @Override
    public void act(Refugee refugee) {
        Random rand=new Random();
        int dice=rand.nextInt(7)+1;
        System.out.println("Dice:"+dice);
        refugee.moveTo(-dice);
    }
    
}