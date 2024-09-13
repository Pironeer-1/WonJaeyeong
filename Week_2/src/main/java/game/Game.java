package game;

import enemy.*;
import player.*;
import utils.BattleLog;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
  private final ArrayList<Player> playerList = new ArrayList<>();
  private final Enemy enemy = new Enemy();
  BattleLog logger = new BattleLog();
  Scanner sc = new Scanner(System.in);
  PlayerAction playerAction = new PlayerAction();
  EnemyAction enemyAction = new EnemyAction();
  Random random = new Random();

  private void setPlayers(){
    int numPlayers;
    while(true){
      try{
        logger.printSetPlayerAmount();
        numPlayers = sc.nextInt();
        sc.nextLine();
        if(numPlayers <= 0){
          logger.printGameInputRangeError();
          continue;
        }
        break;
      }
      catch(InputMismatchException err){
        logger.printTypeError();
        continue;
      }
    }

    for(int i = 0; i < numPlayers; i++){
      Player player = new Player();
      playerAction.setPlayerStatus(player);
      playerList.add(player);
    }
  }

  private void setEnemy(){
    enemyAction.enemyInit(this.enemy, playerList.size());
  }

  private boolean turnCheck(){
    for(int i = 0; i < playerList.size(); i++){
      if(playerList.get(i).hp <= 0) playerList.remove(i);
    }

    return !playerList.isEmpty() && enemy.hp != 0;
  }

  private Player selectTargetPlayer(){
    int randomInt = random.nextInt(playerList.size());
    return playerList.get(randomInt);
  }

  public void start(){
    setPlayers();
    setEnemy();

    while(turnCheck()){
      for(int i = 0; i < playerList.size(); i++){
        Player player = playerList.get(i);
        playerAction.attack(player, enemy, i);

        if(enemy.hp <= 0) break;
      }
      if(enemy.hp <= 0) break;

      Player targetPlayer = selectTargetPlayer();
      int targetIdx = playerList.indexOf(targetPlayer);
      enemyAction.attack(targetPlayer, enemy, targetIdx);
    }

    if(enemy.hp <= 0){
      logger.printPlayerWin();
    }
    else{
      logger.printPlayerLose();
    }
  }
}