package serverlogic;

import java.util.Date;
import java.util.Calendar;

/**
 * Clase para crear objetos tipo jugador
 * @author Marcos
 * @version v19.5.16
 */
public class Player {
  private String nickName;
  private String password;
  private int credit;
  private Date lastLogin;
  
  /**
   * Constructor 
   * @param nickName Nick del jugador
   * @param password Contrasenia del jugador
   */
  public Player(String nickName, String password) {
    setNickName(nickName);
    setPassword(password);
    setCredit(10000);
    setLastLogin();
  }
  
  public String getNickName() {
    return nickName;
  }
  
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public int getCredit() {
    return credit;
  }
  
  public void setCredit(int credit) {
    this.credit = credit;
  }
  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin() {
    Calendar calendar = Calendar.getInstance();
    lastLogin = calendar.getTime();
  }

  /**
   * Metodo para cambiar la forma en la que se imprime un jugador
   * @return Nick del jugador
   */
  public String toString() {
    return getNickName();
  }
}
