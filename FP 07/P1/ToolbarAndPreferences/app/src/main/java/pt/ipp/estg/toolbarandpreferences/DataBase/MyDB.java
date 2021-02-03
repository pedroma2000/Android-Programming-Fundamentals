package pt.ipp.estg.toolbarandpreferences.DataBase;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pt.ipp.estg.toolbarandpreferences.models.Contact;
import pt.ipp.estg.toolbarandpreferences.models.interfaces.ContactDAO;

/**
 *
 * @author Pedro Machado pedroma2000
 */
@Database(entities = {Contact.class}, version = 1)
public abstract class MyDB extends RoomDatabase {

    private static MyDB INSTANCE;

    public abstract ContactDAO daoAccess();

    private static final Object sLock = new Object();

    public static MyDB getInstance(Context context){
        synchronized (sLock){
            if(INSTANCE  == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MyDB.class, "Sample.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
