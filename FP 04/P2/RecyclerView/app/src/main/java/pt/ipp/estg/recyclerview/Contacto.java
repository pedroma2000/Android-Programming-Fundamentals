package pt.ipp.estg.recyclerview;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Contacto implements Serializable {

    private String Nome;
    private Boolean Status;

    public Contacto(String nome, Boolean status) {
        Nome = nome;
        Status = status;
    }

    public String getNome() {
        return Nome;
    }

    public Boolean isOnline() {
        return Status;
    }
}
