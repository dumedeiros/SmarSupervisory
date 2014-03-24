package controllers;

import Connnector.RDAConnection;
import Utilities.ExecThread;
import javax.swing.JOptionPane;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        session.clear();
        render();
    }
}