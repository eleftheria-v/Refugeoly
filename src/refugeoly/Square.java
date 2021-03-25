/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.Action;
import refugeoly.Board;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author categ
 */
public class Square implements Serializable {
    private String text;
    private int number;
    private Board board;
    private ArrayList<Action> actions;
    
    public Square(String text,int number,Board board,ArrayList<Action> actions){
        this.text=text;
        this.number=number;
        this.board=board;
        this.actions=actions;
    }
    public int getNumber(){
        return number;
    }
    public String toString(){
        return text+ " square " +number;
    }
     public ArrayList<Action> getActions(){
        return actions;
    }
     public void doActions(Refugee r){
      ArrayList <Action> actions=new ArrayList <Action> ();
      
      actions=r.getSquare().getActions();
        for(Action i: actions){
            i.act(r);
        }
    }
   
    
}
