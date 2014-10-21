/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.hearthdraft;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author MrFraction
 */



@Path("draft")
@Singleton
public class RestResource {
    Settings settings = new Settings();
    List<Booster> boosters = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    
    @Context
    private UriInfo context;

    
    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
        settings.initClasses();
    }
    
    @POST
    @Path("/pickcard")
    @Produces("application/json")
    public void pickCard(JsonObject content){
        String name = content.getString("name");
        String url = content.getString("url");
        
        for(Booster b : boosters){
            if(b.getPicker().equals(name) && b.hasPicked == false){
                b.hasPicked = true;
                b.getCardUrl().remove(url);
                for(Player p : players){
                    if(p.getPlayername().equals(name)){
                        p.getCardPicked().add(url);
                    }
                }
            }
        }
        
        timeToSend();
    }
    
    private void timeToSend(){
        boolean waiting = false;
        for(Booster b : boosters){
            if(b.hasPicked == false){
                waiting = true;
            }
        }
        
        if(waiting){
            return;
        }
        // all have picked, swap booster
        List<String> currentPlayers = new ArrayList<>(settings.getPlayers());
        String first = currentPlayers.get(0);
        currentPlayers.remove(0);
        currentPlayers.add(first);
        
        settings.setPlayers(currentPlayers);
        
        for(int i=0;i<boosters.size();++i){
            boosters.get(i).setPicker(settings.getPlayers().get(i));
            boosters.get(i).setHasPicked(false);
        }
        
        if(boosters.get(0).getCardUrl().isEmpty() && settings.getBoosters() > 1){
            settings.setBoosters(settings.getBoosters() - 1);
            for(Booster b : boosters){
                Booster tmp = DraftUtils.generateBooster(settings, b.getPicker());
                b.setCardUrl(new ArrayList<>(tmp.getCardUrl()));
            }
        }
    }
    
    @GET
    @Path("/mycards/{name}")
    @Produces("application/json")
    public Player getMyCards(@PathParam("name") String name) {
        for(Player p : players){
            if(p.getPlayername().equals(name)){
                return p;
            }
        }
        
        System.out.println("found no player with playername: " + name);
        return null;
    }
    
    @GET
    @Path("/start")
    @Produces("application/json")
    public String startDraft() {
       for(String s : settings.getPlayers()){
           Booster booster = DraftUtils.generateBooster(settings, s);
           boosters.add(booster);
       } 
       return "ready";
    }
    
    @GET
    @Path("/getbooster/{name}/{unique}")
    @Produces("application/json")
    public Booster getBooster(@PathParam("name") String name) {
        for(Booster b : boosters){
            if(b.getPicker().equals(name)){
                return b;
            }
        }
        return new Booster();
    }
    
    @GET
    @Path("/clear")
    @Produces("application/json")
    public String clearAll() {
       settings = new Settings();
       settings.initClasses();
       players.clear();
       boosters.clear();
       return "cleared";
    }
    
    
    
    @GET
    @Path("/settings")
    @Produces("application/json")
    public Settings getSettings() {
       return settings;
    }
    
    @POST
    @Path("/updatesettings")
    @Produces("application/json")
    public String updatesettings(Settings s){
        this.settings = s;
        return "ok";
    }
    
    @POST
    @Path("/join")
    @Consumes("application/json")
    public void joinDraft(JsonObject content) {
        settings.getPlayers().add(content.getString("msg"));
        players.add(new Player(content.getString("msg")));
        System.out.println(content.getString("msg") + " joined the draft.");
    }
}
