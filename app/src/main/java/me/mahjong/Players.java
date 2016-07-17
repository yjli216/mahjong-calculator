package me.mahjong;

/**
 * Created by liyingjiao on 24/5/16.
 */
public class Players {
    private String name;
    private double money;

    Players (String name) {
        this.name = name;
        this.money = 0;
    }

    public double getMoney() {

        if (this.money < 0.00000001 && this.money > -0.0000001) {
            this.money = 0;
        }

        return this.money;
    }

    public void increase(double incr) {
        this.money = this.money + incr;
    }

    public void pay(double incr, Players p) {
        this.money = this.money - incr;
        p.increase(incr);
    }

    public void pay(double incr, int tai, Players p){
        double payment = incr * Math.pow(2, tai - 1);
        this.money = this.money - payment;
        p.increase(payment);
    }

    public String getName() {
        return this.name;
    }

}
