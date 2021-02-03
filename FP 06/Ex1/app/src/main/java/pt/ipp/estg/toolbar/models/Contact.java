package pt.ipp.estg.toolbar.models;

import java.io.Serializable;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Contact implements Serializable {
    private String Name;
    private Boolean Status;

    public Contact(String name, Boolean status) {
        Name = name;
        Status = status;
    }

    public String getNome() {
        return Name;
    }

    public Boolean isOnline() {
        return Status;
    }
}
