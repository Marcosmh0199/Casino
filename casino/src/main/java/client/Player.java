package client;

import java.util.Calendar;
import java.util.Date;
/**
 * Modela la estructura de los jugadores.
 * @author Vega-Luis
 * @version v19.5.25
 */
public class Player {
  private String name;
  private String password;
  private int credits;
  private Date lastLogin;
  
  /**
   * Constructor de objetos del Clase Player.
   * @param name Nombre del jugador.
   * @param password Contrasenia del jugador.
   */
  public Player(String name, String password) {
    this.name = name;
    this.password = password;
    this.credits = 10000;
    this.setLastLogin();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin() {
    this.lastLogin = Calendar.getInstance().getTime(); 
  }
}
