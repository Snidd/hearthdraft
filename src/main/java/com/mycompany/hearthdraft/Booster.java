/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.hearthdraft;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author MrFraction
 */
public class Booster {

    private List<String> cardUrl = new ArrayList<>();
    private String picker;
    boolean hasPicked = false;
    private String uuid = UUID.randomUUID().toString();

    public Booster(){
        
    }
     
     
    public void setCardUrl(List<String> cardUrl) {
        this.cardUrl = cardUrl;
    }

    public List<String> getCardUrl() {
        if(cardUrl == null){
            cardUrl = new ArrayList<>();
        }
        return cardUrl;
    }
    
    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getPicker() {
        return picker;
    }
    
    public void setHasPicked(boolean hasPicked) {
        this.hasPicked = hasPicked;
    }

    public boolean isHasPicked() {
        return hasPicked;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    
}
