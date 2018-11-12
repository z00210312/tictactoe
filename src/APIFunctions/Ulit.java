/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIFunctions;

import java.util.List;

/**
 *
 * @author Tom
 */
public class Ulit {
    String player1;
    String player2;
    String defaultStr;
    public Ulit(String _player1, String _player2, String _default){
        player1 = _player1;
        player2 = _player2;
        defaultStr = _default;
    }
    public String getPlayer1(){
        return player1;
    }
    public String getPlayer2(){
        return player2;
    }
    public String getDefaultDesc(){
        return defaultStr;
    }
    public boolean StringsAreSame(List<String> listStr, String testStr){
        return listStr.stream().noneMatch((currStr) -> (!testStr.equals(currStr)));
    }
}
