package enemy;

import utils.BattleLog;
import player.Player;

import java.util.Random;

public class EnemyAction {
  private final BattleLog battleLog = new BattleLog();
  private final Random random = new Random();
  private static final int HEALING_AMOUNT = 7;

  public void enemyInit(Enemy enemy, int numPlayers) {
    enemy.init(numPlayers);
  }

  public void attack(Player player, Enemy enemy, int idx) {
    battleLog.dividingLine();
    battleLog.printEnemyTurn();

    int action = random.nextInt(10) + 1;

    if (action == 1) {
      battleLog.printEnemyDoNothing();
    } else if (action <= 8) {
      basicAttack(player, enemy, idx);
    } else {
      heal(enemy);
    }
  }

  private void basicAttack(Player player, Enemy enemy, int idx) {
    int damage = enemy.getAttackPower();
    player.decreaseHp(damage);
    battleLog.printRemainingHp(idx, damage, player.getHp());
  }

  private void heal(Enemy enemy) {
    if (enemy.getHp() + HEALING_AMOUNT > enemy.getHp()) {
      enemy.increaseHp(enemy.getHp() - enemy.getHp());
      battleLog.printEnemyHpFull();
    } else {
      enemy.increaseHp(HEALING_AMOUNT);
      battleLog.printEnemyHp(enemy.getHp());
    }
  }
}