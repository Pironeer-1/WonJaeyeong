package player;

import enemy.Enemy;
import utils.BattleLog;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PlayerAction {
  private final BattleLog logger = new BattleLog();
  private final Random random = new Random();
  private final Scanner sc = new Scanner(System.in);
  private static final int POINT = 13;

  public void setPlayerStatus(Player player) {
    logger.dividingLine();
    logger.printSetStatus(POINT);

    while (true) {
      try {
        int hp = sc.nextInt();
        int ad = sc.nextInt();
        int ap = sc.nextInt();
        sc.nextLine();

        if (hp < 0 || ad < 0 || ap < 0) {
          logger.printSetStatusNegativeError();
          continue;
        }

        if (hp + ad + ap != POINT) {
          logger.printSetStatusSumError(POINT);
          continue;
        }

        player.addPoint(hp, ad, ap);
        break;
      } catch (InputMismatchException e) {
        logger.printSetStatusTypeError();
        sc.nextLine();
      }
    }
  }

  public void attack(Player player, Enemy enemy, int idx) {
    int inputKey;
    while (true) {
      try {
        logger.dividingLine();
        logger.printPlayerChoice(idx);
        String input = sc.nextLine();

        if (input.equals("exit")) {
          logger.printEnd();
          System.exit(0);
        }

        inputKey = Integer.parseInt(input);
        if (inputKey < 1 || inputKey > 4) {
          logger.printPlayerChoiceOutOfRange();
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        logger.printTypeError();
      }
    }

    switch (inputKey) {
      case 1 -> checkStatus(player, enemy);
      case 2 -> basicAttack(player, enemy);
      case 3 -> magicAttack(player, enemy);
      case 4 -> heal(player);
    }
  }

  private void checkStatus(Player player, Enemy enemy) {
    logger.printPlayerInfo(player);
    logger.printEnemyInfo(enemy);

    int damage = player.getAttackPower() - enemy.getDefense();
    enemy.decreaseHp(damage);

    logger.printNormalAttackDamage(damage);
  }

  private void basicAttack(Player player, Enemy enemy) {
    int damage = player.getAttackPower() - enemy.getDefense();
    if (random.nextInt(10) + 1 > 2) {
      logger.printNormalAttackDamage(damage);
    } else {
      damage *= 2;
      logger.printCriticalAttackDamage(damage);
    }
    enemy.decreaseHp(damage);
  }

  private void magicAttack(Player player, Enemy enemy) {
    int damage = player.getMagicPower() * 2 - enemy.getMagicDefense();
    logger.printMagicAttackDamage(damage);
    enemy.decreaseHp(damage);
  }

  private void heal(Player player) {
    int healAmount = random.nextInt(6) + 5;
    player.increaseHp(healAmount);
    logger.printPlayerHp(player.getHp());
  }
}