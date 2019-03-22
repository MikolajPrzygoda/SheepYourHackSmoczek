package sheepyourhack.delf.sheepyourhack_smoczek;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import sheepyourhack.delf.sheepyourhack_smoczek.dataBase.Offer;
import sheepyourhack.delf.sheepyourhack_smoczek.dataBase.OfferViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_OFFER_ACTIVITY_REQUEST_CODE = 1;

    private OfferViewModel offerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final OfferListAdapter adapter = new OfferListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        offerViewModel = ViewModelProviders.of(this).get(OfferViewModel.class);
        offerViewModel.getAllOffers().observe(this, new Observer<List<Offer>>() {
            @Override
            public void onChanged(@Nullable final List<Offer> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setOffers(words);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_OFFER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] offerStrings = data.getStringExtra(NewOfferActivity.EXTRA_REPLY).split("\\|");
            Offer word = new Offer( offerStrings[0], offerStrings[1], Integer.valueOf(offerStrings[2]));
            offerViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.noTitleError,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void createNewOffer(View view) {
        Intent intent = new Intent(MainActivity.this, NewOfferActivity.class);
        startActivityForResult(intent, NEW_OFFER_ACTIVITY_REQUEST_CODE);
    }
}
