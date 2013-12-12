package controllers;

import javax.swing.JOptionPane;
import play.mvc.*;
import models.*;
import play.libs.Codec;

public class Ajax extends Controller {
    
    public static void login(String login, String senha) {
        String hashSenha = Codec.hexMD5(senha);
        Usuario usuario = Usuario.find("byLoginAndSenhaHash", login, Codec.hexMD5(senha)).first();
        Erro erro = null;
        if (usuario != null) {
            Action.conectar(usuario);
            flash.success("Login realizado com sucesso");
            renderJSON(erro);
        } else {
            erro = new Erro("Falha no Login. Verifique os dados no formulário.");
            flash.error("Erro no login");
            renderJSON(erro);
        }
    }
}
