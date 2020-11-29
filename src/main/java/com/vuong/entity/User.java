/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.entity;

/**
 *
 * @author ducvuong25
 */
public class User {

    private int id;
    private String name;
    private boolean smoking;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User(int id, String name, boolean smoking) {
        this.id = id;
        this.name = name;
        this.smoking = smoking;
    }
    

    public boolean isSmoking() {
        return smoking;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
    
    
}
