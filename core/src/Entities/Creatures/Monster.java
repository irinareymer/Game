package Entities.Creatures;

import Managers.EntitiesManager;

import java.util.Random;

public class Monster extends Creatures {
    protected EntitiesManager em;

    public Monster(EntitiesManager em) {
        this.em = em;
    }

    Random rndm = new Random();
    int monsterPositionByDice = rndm.nextInt(5) + 1;


    public void setPowerOfMonster(int power) {
        this.power = power;
    }
    public int gePowerOfMonster() {
        return power;
    }


    public boolean isMonsterHere(Position pose) {
        Position monster1 = em.getPlayState().getField().getFieldPosition(3 + monsterPositionByDice);
        Position monster2 = em.getPlayState().getField().getFieldPosition(11 + monsterPositionByDice);
        Position monster3 = em.getPlayState().getField().getFieldPosition(17 + monsterPositionByDice);
        Position monster4 = em.getPlayState().getField().getFieldPosition(25 + monsterPositionByDice);
        return (pose == monster1 || pose == monster2 || pose == monster3 || pose == monster4);
    }


}
