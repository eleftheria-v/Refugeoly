/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import refugeoly.MoneyReceiver;
import java.io.Serializable;

/**
 *
 * @author categ
 */
public class ReceiverEntity implements MoneyReceiver ,Serializable{
 private String name;
    private double money;
    public ReceiverEntity(String name,double money){
    this.name=name;
    this.money=money;
    }
    public double getMoney(){
        return money;
    }
   /* public void setMoney(double sm){
        this.money=sm;
    }*/
    public String toString(){
        return "..."+name+" your expenses are "+money;
    }
    


    @Override
    public void receiveMoney(double receiveMoney) {
       this.money=this.money+receiveMoney;
       
    }

    public void yourExpences(double giveMoney) {
       this.money=this.money+giveMoney;
    }
    

    
    
}
