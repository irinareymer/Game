package Model.Creatures;

import Controller.States.PlayState;

import java.util.Random;

public class Monster extends Creatures {
    protected PlayState em;
    int incPower;

    public Monster(PlayState em) {
        this.em = em;
    }

    Random rndm = new Random();
    int monsterPositionByDice = rndm.nextInt(5) + 1;

    public void setPowerOfMonster(int power) {
        this.power = power;
    }
    public int getPowerOfMonster() {
        return power;
    }

    public void setIncreasedPowerOfMonster(int incPower) {
        this.incPower = incPower;
    }
    public int getIncreasedPowerOfMonster() {
        return incPower;
    }

    public boolean isMonsterHere(Position pose) {
        int first = 3 + monsterPositionByDice;
        int second = 11 + monsterPositionByDice;
        int third = 17 + monsterPositionByDice;
        if (first == 9 || first ==14 || first == 23) first++;
        if (second == 9 || second ==14 || second == 23) second++;
        if (third == 9 || third ==14 || third == 23) third++;
        Position monster1 = em.getField().getFieldPosition(first);
        Position monster2 = em.getField().getFieldPosition(second);
        Position monster3 = em.getField().getFieldPosition(third);
        return (pose == monster1 || pose == monster2 || pose == monster3);
    }
}