package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.JOptionPane;
import models.*;

public class Action extends Controller {

    public static void redirectHome(String message) {
        flash.success(message);
        Application.index();
    }

    public static void conectar(Usuario usuario) {
        session.put("login", usuario.login);
    }

    public static void logout() {
        session.clear();
        Application.index();
    }
}
