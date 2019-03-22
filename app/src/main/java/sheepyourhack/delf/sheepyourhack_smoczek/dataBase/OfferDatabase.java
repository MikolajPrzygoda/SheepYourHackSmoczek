package sheepyourhack.delf.sheepyourhack_smoczek.dataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Offer.class}, version = 1)
public abstract class OfferDatabase extends RoomDatabase {
    public abstract OfferDAO offerDAO();

    private static volatile OfferDatabase INSTANCE;

    static OfferDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (OfferDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OfferDatabase.class, "offer_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }



    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final OfferDAO mDao;

        PopulateDbAsync(OfferDatabase db) {
            mDao = db.offerDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            Offer word;

            word = new Offer("Oddam dziecko", "za darmo", 0);
            mDao.insert(word);
            word = new Offer("Kupię dziecko", "odbiór osobisty", -100);
            mDao.insert(word);

            return null;
        }
    }
}
