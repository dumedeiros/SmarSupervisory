package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Admin extends Controller {

    @Before
    public static void addUsuario() {
        Usuario usuario = usuarioLogado();
        if (usuario != null) {
            renderArgs.put("usuarioLogado", usuario);
        }
    }

    public static Usuario usuarioLogado() {
        if (renderArgs.get("usuarioLogado") != null) {
            return renderArgs.get("usuarioLogado", Usuario.class);
        }

        String login = session.get("login");
        return (Usuario) (login != null ? Usuario.find("byLogin", login).first() : null);
    }

    @Before
    public static void checkUsuarioLogado() {
        if (usuarioLogado() == null) {
            flash.error("Logue-se primeiro");
            Application.index();
        }
    }

    public static void conectar(Usuario usuario) {
        session.put("login", usuario.login);
    }
}
