package controllers;

import Utilities.ExecThread;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void redirectHome(String message) {
        flash.success(message);
        index();
    }

    public static void redirectHomeLogin() {
        flash.success("Usuario logado com sucesso");
        index();
    }
}