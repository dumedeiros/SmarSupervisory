package controllers;

import play.mvc.*;
import java.util.*;
import models.*;
import play.cache.Cache;

public class AdminSupervisory extends Admin {

    public static void redirectHome(String message) {
        flash.success(message);
        index();
    }

    public static void redirectHomeLoginSuccess() {
        flash.success("Usuario logado com sucesso");
        index();
    }

    public static void index() {
        flash.put("error", "Ocorreu um erro");
        render();
    }
}
