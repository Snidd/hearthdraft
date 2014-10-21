/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.hearthdraft;

import java.util.ArrayList;
import java.util.List;

public class Settings {
  
    public Settings(){
        this.players = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.boosters = 3;
        this.neutrals = 8;
        this.classcards = 7;
    }
    
    public void initClasses(){
        classes.add("druid");
        classes.add("hunter");
        classes.add("mage");
        classes.add("paladin");
        classes.add("priest");
        classes.add("rogue");
        classes.add("shaman");
        classes.add("warlock");
        classes.add("warrior");
    }
    
    public Settings(List<String> players, List<String> classes, int boosters, int neutrals, int classcards){
        this.players = players;
        this.classes = classes;
        this.boosters = boosters;
        this.neutrals = neutrals;
        this.classcards = classcards;
    }
    private List<String> classes;

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
    private List<String> players;

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<String> getPlayers() {
        return players;
    }
    private int boosters = 3;

    public void setBoosters(int boosters) {
        this.boosters = boosters;
    }

    public int getBoosters() {
        return boosters;
    }
    private int neutrals = 9;

    public void setNeutrals(int neutrals) {
        this.neutrals = neutrals;
    }

    public int getNeutrals() {
        return neutrals;
    }
    private int classcards = 6;

    public void setClasscards(int classcards) {
        this.classcards = classcards;
    }

    public int getClasscards() {
        return classcards;
    }
}
