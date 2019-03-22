package sheepyourhack.delf.sheepyourhack_smoczek.dataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class OfferRepository {

    private OfferDAO offerDAO;
    private LiveData<List<Offer>> allOffers;

    OfferRepository(Application application){
        OfferDatabase db = OfferDatabase.getDatabase(application);
        offerDAO = db.offerDAO();
        allOffers = offerDAO.getAllOffers();
    }

    public LiveData<List<Offer>> getAllOffers(){
        return allOffers;
    }

    public void insert(Offer offer){
        new InsertOfferAsyncTask(offerDAO).execute(offer);
    }

    private static class InsertOfferAsyncTask extends AsyncTask<Offer, Void, Void> {

        private OfferDAO asyncTaskDao;

        InsertOfferAsyncTask(OfferDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Offer... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
