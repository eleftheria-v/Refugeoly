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
public class PayMoneyAction implements Action,Serializable{

    @Override
    public void act(Refugee refugee) {
       int n=refugee.getSquare().getNumber();
        if(n==1){
          refugee.giveMoney(100);
        }
        else if(n==3){
          refugee.giveMoney(300);
        }
        else if((n==6)||(n==37)){
          refugee.giveMoney(1000); 
        }
        else if(n==13){
          refugee.giveMoney(200); 
        }
        else if (n==21){
           refugee.giveMoney(1000);
        }
    }
    //n χρειαʆ ζεται το ποσοʆ που θα πληρωʆ σειο παιʆκτης και αυτοʆ ν στον οποιʆο θα το πληρωʆ σει
}
