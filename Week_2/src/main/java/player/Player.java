package player;

import utils.NumberEnum;

public class Player {
  private int hp = NumberEnum.PLAYER_HP.getAmount();
  private int attackPower = NumberEnum.PLAYER_ATTACK_POWER.getAmount();
  private int magicPower = NumberEnum.PLAYER_MAGIC_POWER.getAmount();

  public void addPoint(int hpPoints, int attackPoints, int magicPoints) {
    this.hp += 3 * hpPoints;
    this.attackPower += attackPoints;
    this.magicPower += magicPoints;
  }

  public void decreaseHp(int damage) {
    this.hp = Math.max(this.hp - damage, 0);
  }

  public void increaseHp(int hp) {
    this.hp += hp;
  }

  public int getHp() {
    return hp;
  }

  public int getAttackPower() {
    return attackPower;
  }

  public int getMagicPower() {
    return magicPower;
  }
}