package com.cooksys.actors;

public abstract class Actor {
    private int health;
    private int damage;

    public Actor(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public void attack(Actor target) {
        target.setHealth(target.getHealth() - this.getDamage());
    }

    public boolean isAlive() {
        return (health > 0);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
