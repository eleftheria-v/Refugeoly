package refugeoly;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author categ
 */
public class GiverEntity implements MoneyGiver ,Serializable{
    private String name;
    private double money;
    
    public GiverEntity(String name,double money){
    this.name=name;
    this.money=money;
    }

  
    @Override
    public void giveMoney(double giveMoney) throws NoMoneyException {
        
        if (this.money>giveMoney) {
           this.money=this.money-giveMoney;
           
        }
        else {
            NoMoneyException exc = new NoMoneyException("Player does not have enough money.");
            throw exc;
        }
    }
    
    public String toString(){
        return name+" has "+money;
    }
    
}
