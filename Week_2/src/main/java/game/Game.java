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
  private final BattleLog logger = new BattleLog();
  private final Scanner sc = new Scanner(System.in);
  private final PlayerAction playerAction = new PlayerAction();
  private final EnemyAction enemyAction = new EnemyAction();
  private final Random random = new Random();

  public void start() {
    setPlayers();
    setEnemy();

    while (turnCheck()) {
      for (int i = 0; i < playerList.size(); i++) {
        Player player = playerList.get(i);
        playerAction.attack(player, enemy, i);

        if (enemy.getHp() <= 0) break;
      }
      if (enemy.getHp() <= 0) break;

      Player targetPlayer = selectTargetPlayer();
      int targetIdx = playerList.indexOf(targetPlayer);
      enemyAction.attack(targetPlayer, enemy, targetIdx);
    }

    if (enemy.getHp() <= 0) {
      logger.printPlayerWin();
    } else {
      logger.printPlayerLose();
    }
  }

  private void setPlayers() {
    while (true) {
      try {
        logger.printSetPlayerAmount();
        int numPlayers = sc.nextInt();
        sc.nextLine();

        if (numPlayers <= 0) {
          logger.printGameInputRangeError();
        } else {
          for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            playerAction.setPlayerStatus(player);
            playerList.add(player);
          }
          break;
        }
      } catch (InputMismatchException e) {
        logger.printTypeError();
        sc.nextLine();
      }
    }
  }

  private void setEnemy() {
    enemyAction.enemyInit(this.enemy, playerList.size());
  }

  private boolean turnCheck() {
    playerList.removeIf(player -> player.getHp() <= 0);
    return !playerList.isEmpty() && enemy.getHp() > 0;
  }

  private Player selectTargetPlayer() {
    return playerList.get(random.nextInt(playerList.size()));
  }
}