package enemy;

import utils.BattleLog;
import player.Player;

import java.util.Random;

public class EnemyAction {
  BattleLog battleLog = new BattleLog();
  Random random = new Random();
  int healingAmount = 7;

  public void enemyInit(Enemy enemy, int len){
    enemy.init(len);
  }

  private void basicAttack(Player player, Enemy enemy, int idx){
    int damage = enemy.ad;
    player.decreaseHp(damage);
    battleLog.printRemainingHp(idx, damage, player.hp);
  }

  private void heal(Enemy enemy){
    if(enemy.hp + healingAmount > enemy.maxHp){
      enemy.increaseHp(enemy.maxHp - enemy.hp);
      battleLog.printEnemyHpFull();
    }
    else{
      enemy.increaseHp(healingAmount);
      battleLog.printEnemyHp(enemy.hp);
    }
  }

  public void attack(Player player, Enemy enemy, int idx){
    battleLog.dividingLine();
    battleLog.printEnemyTurn();
    int randomInt = random.nextInt(10) + 1;

    if(randomInt == 1){
      battleLog.printEnemyDoNothing();
    }
    else if(randomInt <= 8){
      basicAttack(player, enemy, idx);
    }
    else{
      heal(enemy);
    }
  }
}