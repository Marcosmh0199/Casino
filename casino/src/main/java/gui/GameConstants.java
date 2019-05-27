package gui;

/**
 * Constantes utilizadas por el juego.
 * 
 * @author Vega-Luis
 * @version v19.5.27
 *
 */
public interface GameConstants {
  public final String SOURCE = System.getProperty("user.home") + "\\sources";

  public final String GITKRAKEN = SOURCE + "\\gitKraken.png";
  public final String ECLIPSE = SOURCE + "\\eclipse.png";
  public final String BITCOIN = SOURCE + "\\bitcoin.png";
  public final String NETBEANS = SOURCE + "\\netbeans.png";
  public final String BLUEJ = SOURCE + "\\bluej.png";
  public final String XCODE = SOURCE + "\\xcode.png";
  public final String ORACLE = SOURCE + "\\oracle.png";
  public final String WINDOWS = SOURCE + "\\windows.png";
  public final String MAC = SOURCE + "\\mac.png";
  public final String LINUX = SOURCE + "\\linux.png";
  public final String JCREATOR = SOURCE + "\\jcreator.png";
  public final String JAVA = SOURCE + "\\java.png";
  public final String[] ICONS = {GITKRAKEN, ECLIPSE, BITCOIN, NETBEANS, WINDOWS, MAC, LINUX, BLUEJ,
      XCODE, ORACLE, JAVA, JCREATOR};
  public final int[] BET_AMOUNT = {100, 250, 500, 1000, 5000, 10000};
  public final String COLUMN = SOURCE + "\\column.png";
  public final String BACKGROUND = SOURCE + "\\background.png";
  public final String JACKPOT = SOURCE + "\\jackpot.png";
  public final String JACKCOIN = SOURCE + "\\jackcoin.png";
  public final String UP_SIDE = SOURCE + "\\upside.png";
  public final String DOWN_SIDE = SOURCE + "\\downside.png";
  public final String BET = SOURCE + "\\bet.png";

  public final String CHANGE_BET = "-c ";
  public final String PLAY = "-p ";
  public final String CLOSE = "-e ";
}
