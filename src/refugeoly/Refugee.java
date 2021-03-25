/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.Board;
import java.io.Serializable;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.ArrayList;

/**
 *
 * @author categ
 */
public class Refugee implements Serializable {
//public static final long serialVersionUID = -1756567893402234452L;
  private String name;
  private double money;
  private Board board;
  private Square square;
  private ReceiverEntity expences;
  
  public Refugee(String name,Board board,Square square){
      this.name=name;
      money=10000;
      this.board=board;
      this.square=square;
      this.expences=new ReceiverEntity(name,0);
  }
  
    /**
     *
     * @param money
     */
   public double getMoney(){
       return money;
   } 
  public void setMoney(double money){
     this.money=money;
    
  }
  public ReceiverEntity getExpences(){
      return expences;
  }
  public void receiveMoney(double giveMoney){
      double gm=money+giveMoney;
      
      this.money=gm;
  }
  //giveMoney()
  public void giveMoney(double giveMoney){
      double gm=money-giveMoney;
      expences.yourExpences(giveMoney);
      setMoney(gm);
      
  }
  public Square getSquare(){
    return square;  
  }
  public void moveTo(int dice){
      int n=this.square.getNumber();
      int number=n+dice;
      if(number<=39){
      this.square=board.getSquare(n+dice);    
      }
      else if(number>39){
        int  x=number-39;
      this.square=board.getSquare(39-x);    
      }
      if((n==36)||(n==39)){
        this.square=board.getSquare(n);  
      }
      
      
  }
  public void moveToForAction(int n){
      if(n==10){
          ArrayList <Action> NA=new ArrayList <Action>();
            NoActionAction na=new NoActionAction();
            NA.add(na);
            Square hasLife=new Square("Dead at Sea. You are dead and out of the game. ",10,board,NA);
            this.square=hasLife;
      }
      else{
      this.square=board.getSquare(n);
      }
  }

  public String toString(){
      return name+".."+square+"...."+money+" "+expences.toString();
  }
public Board getBoard(){
    return board;
} 

public String getName() {
     return name;
}



}
