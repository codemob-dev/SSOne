package com.codemob.ssone.role.system;

public class MagicAbilities {
    private double earth = 0.75;
    private double air = 0.75;
    private double fire = 0.75;
    private double water = 0.75;
    private double dark = 0.5;

    public double earth() {
        return earth;
    }

    public double air() {
        return air;
    }

    public double fire() {
        return fire;
    }

    public double water() {
        return water;
    }

    public double dark() {
        return dark;
    }

    public MagicAbilities earth(double earth) {
        this.earth = earth;
        return this;
    }

    public MagicAbilities air(double air) {
        this.air = air;
        return this;
    }

    public MagicAbilities fire(double fire) {
        this.fire = fire;
        return this;
    }

    public MagicAbilities water(double water) {
        this.water = water;
        return this;
    }

    public MagicAbilities dark(double dark) {
        this.dark = dark;
        return this;
    }
}
