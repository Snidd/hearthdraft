/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.hearthdraft;

import java.util.ArrayList;
import java.util.List;


public class Player {
    
    private List<String> cardPicked = new ArrayList<>();
    private String playername;
    
    public Player(){
        playername = "";
    }
    
    public Player(String name){
        playername = name;
    }
    
    public List<String> getCardPicked() {
        return cardPicked;
    }
    
    public String getPlayername() {
        return playername;
    }
    
    public void setCardPicked(List<String> list){
        this.cardPicked = list;
    }
    
    public void setPlayername(String playername){
        this.playername = playername;
    }
    
}
