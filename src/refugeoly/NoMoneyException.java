package refugeoly;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author categ
 */
public class NoMoneyException extends Exception{
     public NoMoneyException(){

    }
    
    public NoMoneyException(String msg){
        
        super(msg);
    }
}
