package com.cooksys.actors;

public class Monster extends Actor {
    public Monster(int health, int damage) {
        super(health, damage);
    }

    @Override
    public String toString() {
        return "Monster";
    }
}
