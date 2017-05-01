package weeia.isbnapp.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import weeia.isbnapp.R;
import weeia.isbnapp.book.offers.Offer;

public class PricesFragment extends Fragment {

    private LinearLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prices, container, false);
        tabLayout = (LinearLayout) view.findViewById(R.id.offersLayout);
        addOffersToView();
        return view;
    }

    private void addOffersToView(){
        List<Offer> bookOffers = ((BookDetailsActivity) this.getActivity()).bookOffersTest.getOffers();

        for(Offer offer: bookOffers){
            addOffersBoxToView(offer);
        }
    }

    private void addOffersBoxToView(final Offer offer){
        ConstraintLayout offerBox = (ConstraintLayout) View.inflate(getContext(), R.layout.offer_box_template, null);

        final ImageView cover = (ImageView) offerBox.getChildAt(0);
        cover.setImageDrawable(LoadImageFromWebOperations(offer.getPicturePath()));

        ((TextView) offerBox.getChildAt(1)).setText(offer.getName());
        ((TextView) offerBox.getChildAt(2)).setText(offer.getCondition());
        ((TextView) offerBox.getChildAt(3)).setText(String.format("%.2f", offer.getPrice())+ " " + offer.getCurrency());
        ((TextView) offerBox.getChildAt(4)).setText(String.format("%.2f", offer.getBookAndShipmentPrice()) + " " + offer.getCurrency() + " z dostawą");

        offerBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse(offer.getUrl()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        tabLayout.addView(offerBox);
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exception! ");
            e.printStackTrace();
            return null;
        }
    }
}