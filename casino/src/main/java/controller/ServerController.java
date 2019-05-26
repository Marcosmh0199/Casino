package controller;

import java.util.ArrayList;
import serverlogic.BetRules;
import serverlogic.Game;
import serverlogic.JackpotRules;
import serverlogic.SerializeObject;
import serverlogic.SpinesRules;

public class ServerController {
  private Game game;
  private BetRules betRules;
  private JackpotRules jackpotRules;
  private SpinesRules spinesRules;
  private SerializeObject serializer;
  
  public ServerController() {
    serializer = new SerializeObject();
    getRules();
    game = new Game(betRules, jackpotRules, spinesRules);
  }
  
  public Game getGame() {
    return this.game;
  }
  
  void getRules() {
    betRules = new BetRules();
    jackpotRules = new JackpotRules();
    spinesRules = new SpinesRules();
    betRules = (BetRules) serializer.convertFromJson("Rules", "BETRULES", betRules.getClass());
    jackpotRules = (JackpotRules) serializer.convertFromJson("Rules", "JACKPOTRULES", jackpotRules.getClass());
    spinesRules = (SpinesRules) serializer.convertFromJson("Rules", "SPINESRULE", spinesRules.getClass());
  }
  
  public ArrayList<ArrayList<String>> getGameBoard() {
    return game.generateBoard();
  }
  
  public ArrayList<Object> play(){
    return game.play();
  }
}
