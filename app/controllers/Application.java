package controllers;

import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        flash.put("error", "EDUUU");
        session.clear();
        render();
    }
}
