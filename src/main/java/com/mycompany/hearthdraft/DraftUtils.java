/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.hearthdraft;

import java.util.Random;

/**
 *
 * @author MrFraction
 */
public class DraftUtils {

    public static Booster generateBooster(Settings s, String player){
        Booster ret = new Booster();
        int neutralcommon = 95;
        int neutralrare = 38;
        int neutralepic = 13;
        int neutrallegendary = 32;
        boolean hasLegendary = false;
        Random rand = new Random();
        for(int i=0;i<s.getNeutrals();++i){
        int random = rand.nextInt(100) + 1;
            String card;
            if(random <= 4 && hasLegendary == false){
                card = createCard("images/neutral/legendary/", neutrallegendary);
                hasLegendary = true;
            }
            else if(random <= 15){
                card = createCard("images/neutral/epic/", neutralepic);
            }
            else if(random <= 25){
                card = createCard("images/neutral/rare/", neutralrare);
            }
            else{
                card = createCard("images/neutral/common/", neutralcommon);
            }
            
            if(ret.getCardUrl().contains(card)){
                i--;
            }
            else{
                ret.getCardUrl().add(card);
            }
        }
        
        for(int i=0;i<s.getClasscards();++i){
            int commons = 17;
            int rares = 5;
            int epics = 3;
            int legendary = 1;   
            int whatClass = rand.nextInt(s.getClasses().size());
                int random = rand.nextInt(100) + 1;
                String card;
                if(random <= 4){
                    card = createCard("images/"+s.getClasses().get(whatClass)+"/legendary/", legendary);
                }
                else if(random <= 15){
                    card = createCard("images/"+s.getClasses().get(whatClass)+"/epic/", epics);
                }
                else if(random <= 25){
                    card = createCard("images/"+s.getClasses().get(whatClass)+"/rare/", rares);
                }
                else{
                    card = createCard("images/"+s.getClasses().get(whatClass)+"/common/", commons);
                }
                
                if(ret.getCardUrl().contains(card)){
                    i--;
                }
                else{
                    ret.getCardUrl().add(card);
                }
        }
        ret.setPicker(player);
        return ret;
    }
    
    private static String createCard(String path, int number){
        Random rand = new Random();
        int cardnr = rand.nextInt(number) + 1; 
        String ret = path + cardnr + ".png";
        return ret;
    }
    
}
