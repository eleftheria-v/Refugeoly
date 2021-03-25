/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;


/**
 *
 * @author categ
 */
public interface MoneyGiver {
    public void giveMoney(double giveMoney) throws NoMoneyException;
}
