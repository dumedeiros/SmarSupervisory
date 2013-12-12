package controllers;

import java.text.ParseException;
import play.mvc.*;
import Utilities.Util;
import java.util.*;
import javax.swing.JOptionPane;
import models.*;
import play.cache.Cache;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.db.Model;
import play.db.jpa.GenericModel;
import play.libs.Codec;

public class AdminAction extends Admin {

    public static void salvarUsuario(@Valid Usuario usuario, String login, String senha, boolean admin) {
        if (validation.hasErrors()) {
            render("@AdminForm.usuario", usuario, login, admin);
        }
        if (usuario.id == null) {
            usuario.create();
            flash.success("Usuário cadastrado com sucesso");
            Application.index();
        } else {
            usuario.save();
            flash.success("Usuário editado com sucesso");
            Application.index();
        }
    }

    public static void removerUsuario(Long id) {
        Usuario u = Usuario.findById(id);
        if (u != null) {
            u.delete();
            flash.success("Usuário removido com sucesso");
        }
    }
}
