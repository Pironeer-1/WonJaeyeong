package enemy;

import utils.NumberEnum;

public class Enemy {
  private int hp = NumberEnum.ENEMY_HP.getAmount();
  private int attackPower = NumberEnum.ENEMY_ATTACK_POWER.getAmount();
  private int defense = NumberEnum.ENEMY_DEFENSE_POWER.getAmount();
  private int magicDefense = NumberEnum.ENEMY_MAGIC_DEFENSE.getAmount();
  private int maxHp = NumberEnum.ENEMY_HP.getAmount();

  public void init(int numPlayers) {
    this.hp *= numPlayers;
    this.maxHp *= numPlayers;
  }

  public void decreaseHp(int damage) {
    this.hp = Math.max(this.hp - damage, 0);
  }

  public void increaseHp(int hp) {
    this.hp = Math.min(this.hp + hp, maxHp);
  }

  public int getHp() {
    return hp;
  }

  public int getAttackPower() {
    return attackPower;
  }

  public int getDefense() {
    return defense;
  }

  public int getMagicDefense() {
    return magicDefense;
  }
}