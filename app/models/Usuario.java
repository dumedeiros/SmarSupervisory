package models;

import javax.persistence.Entity;
import javax.persistence.Transient;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

    @Required(message = "Campo obrigatório")
    public String nome;
    @Required(message = "Campo obrigatório")
    public String login;
    public boolean isAdmin;
    public String senhaHash;
    @Transient
    public String senha;

    @Override
    public String toString() {
        return nome;
    }
}
