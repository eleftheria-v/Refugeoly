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

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author categ
 */
public class Board implements Serializable{
    private ArrayList<Square> squares = new ArrayList<Square>();
    //addSquare()
    public void addSquare(Square s){
    squares.add(s);
    }
    //getSquare()
    public  Square getSquare(int number){
        for(Square s:squares){
            if(s.getNumber()==number){
                return s;
            }
        }
        return null;
    }
    

}
