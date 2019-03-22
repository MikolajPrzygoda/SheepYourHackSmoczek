package sheepyourhack.delf.sheepyourhack_smoczek.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OfferDAO {

    @Insert
    void insert(Offer offer);

    @Query("DELETE FROM offers;")
    void deleteAll();

    @Query("SELECT * from offers ORDER BY title ASC;")
    LiveData<List<Offer>> getAllOffers();

}
