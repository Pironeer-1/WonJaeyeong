package player;

import enemy.Enemy;
import utils.BattleLog;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PlayerAction {
  BattleLog logger = new BattleLog();
  Random random = new Random();
  Scanner sc = new Scanner(System.in);
  private final int point = 13;

  public void setPlayerStatus(Player player){
    logger.dividingLine();
    logger.printSetStatus(this.point);

    while(true){
      int hp, ad, ap;
      while(true){
        try{
          hp = sc.nextInt();
          ad = sc.nextInt();
          ap = sc.nextInt();
          sc.nextLine();
          if(hp < 0 || ad < 0 || ap < 0){
            logger.printSetStatusNegativeError();
            continue;
          }
          break;
        }
        catch(InputMismatchException err){
          logger.printSetStatusTypeError();
          sc.nextLine();
          continue;
        }
      }

      int sum = hp + ad + ap;
      if(sum != point){
        logger.printSetStatusSumError(this.point);
        continue;
      }
      else{
        player.addPoint(hp, ad, ap);
        break;
      }
    }

  }

  private void checkStatus(Player player, Enemy enemy){
    logger.printPlayerInfo(player);
    logger.printEnemyInfo(enemy);

    int damage = player.attackPower - enemy.adDefence;
    enemy.decreaseHp(damage);

    logger.printNormalAttackDamage(damage);
  }

  private void basicAttack(Player player, Enemy enemy){
    int randomInt = random.nextInt(10) + 1;

    if(randomInt > 2){
      int damage = player.attackPower - enemy.adDefence;
      enemy.decreaseHp(damage);
      logger.printNormalAttackDamage(damage);
    }
    else{
      int damage = (player.attackPower - enemy.adDefence) * 2;
      enemy.decreaseHp(damage);
      logger.printCriticalAttackDamage(damage);
    }
  }

  private void magicAttack(Player player, Enemy enemy){
    int damage = player.magicPower * 2 - enemy.apDefence;
    enemy.decreaseHp(damage);
    logger.printMagicAttackDamage(damage);
  }

  private void heal(Player player){
    int randomInt = random.nextInt(6) + 5;
    player.increaseHp(randomInt);
    logger.printPlayerHp(player.hp);
  }

  public void attack(Player player, Enemy enemy, int idx){
    int inputKey;
    while(true){
      try{
        logger.dividingLine();
        logger.printPlayerChoice(idx);
        String input = sc.nextLine();

        if(input.equals("exit")){
          logger.printEnd();
          System.exit(0);
        }
        else{
          inputKey = Integer.parseInt(input);

          if(inputKey < 1 || inputKey > 4){
            logger.printPlayerChoiceOutOfRange();
            continue;
          }
          break;
        }
      }
      catch(NumberFormatException err){
        logger.printTypeError();
        continue;
      }
    }

    if(inputKey == 1){
      checkStatus(player, enemy);
    }
    else if(inputKey == 2){
      basicAttack(player, enemy);
    }
    else if(inputKey == 3){
      magicAttack(player, enemy);
    }
    else {
      heal(player);
    }
  }
}