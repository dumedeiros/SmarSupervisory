package Connnector;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teles
 */
public class TestExceptionManager {

    public static void log(String message, Exception ex) {
        Logger.getLogger(RDAConnection.class.getName()).log(Level.SEVERE, null, ex);
        System.err.println(message);
        System.err.println("Teste encerrado.");
        System.exit(0);
    }
}
