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
public class ReceiveMoneyAction implements Action,Serializable{

    @Override
    public void act(Refugee refugee) {
       int n=refugee.getSquare().getNumber();
        if(n==20){
          refugee.receiveMoney(1000);
        }
       
    }
    //n χρειαʆ ζεται το ποσοʆ που θα πληρωʆ σειο παιʆκτης και αυτοʆ ν στον οποιʆο θα το πληρωʆ σει
}

