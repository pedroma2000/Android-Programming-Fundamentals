package pt.ipp.estg.toolbarandpreferences.models.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pt.ipp.estg.toolbarandpreferences.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
@Dao
public interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContacts(Contact... contact);

    @Delete
    public void deleteContacts(Contact... contacts);

    @Query("SELECT * FROM contact")
    public List<Contact> loadAllContacts();
}
