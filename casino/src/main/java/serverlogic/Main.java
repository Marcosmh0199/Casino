package serverlogic;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("Exceso de tamanio");
		logger.error("Me pesa mucho la gapin");
		//System.out.println(logger.getLevel());
	}

}
