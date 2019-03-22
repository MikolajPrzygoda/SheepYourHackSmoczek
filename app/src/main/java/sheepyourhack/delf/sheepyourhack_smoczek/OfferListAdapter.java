package sheepyourhack.delf.sheepyourhack_smoczek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sheepyourhack.delf.sheepyourhack_smoczek.dataBase.Offer;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.OfferViewHolder> {

    class OfferViewHolder extends RecyclerView.ViewHolder {
        private final TextView offerItemView;

        private OfferViewHolder(View itemView) {
            super(itemView);
            offerItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Offer> offers; // Cached copy of offers

    OfferListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new OfferViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        if (offers != null) {
            Offer current = offers.get(position);
            holder.offerItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.offerItemView.setText("No offer");
        }
    }

    void setOffers(List<Offer> offers){
        this.offers = offers;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // offers has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (offers != null)
            return offers.size();
        else return 0;
    }
}