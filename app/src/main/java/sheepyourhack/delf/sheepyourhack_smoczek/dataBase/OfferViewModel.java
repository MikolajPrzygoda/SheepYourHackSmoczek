package sheepyourhack.delf.sheepyourhack_smoczek.dataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class OfferViewModel extends AndroidViewModel {

    private OfferRepository offerRepository;
    private LiveData<List<Offer>> allOffers;

    public OfferViewModel (Application application) {
        super(application);
        offerRepository = new OfferRepository(application);
        allOffers = offerRepository.getAllOffers();
    }

    public LiveData<List<Offer>> getAllOffers() {
        return allOffers;
    }

    public void insert(Offer word) {
        offerRepository.insert(word);
    }


}
