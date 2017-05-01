package weeia.isbnapp.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompatBase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import weeia.isbnapp.R;

public class InformationFragment extends Fragment {

    private ImageView cover;
    private TextView title;
    private TextView author;
    private TextView originalLanguage;
    private TextView originalTitle;
    private TextView yearOfFirsEdition;
    private TextView category;
    private TextView genre;
    private TextView form;
    private TextView publisherNote;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        initializeInfoFields(view);
        fillInfoFields();
        return view;
    }

    private void initializeInfoFields(View view){
        cover = (ImageView) view.findViewById(R.id.imageView);
        title = (TextView) view.findViewById(R.id.title);
        author = (TextView) view.findViewById(R.id.author);
        originalLanguage = (TextView) view.findViewById(R.id.book_language);
        yearOfFirsEdition = (TextView) view.findViewById(R.id.year);
        category = (TextView) view.findViewById(R.id.category);
        genre = (TextView) view.findViewById(R.id.genre);
        form = (TextView) view.findViewById(R.id.form);
        publisherNote = (TextView) view.findViewById(R.id.publisher_note);
    }

    private void fillInfoFields(){
        final BookDetailsActivity bookDetailsActivity = (BookDetailsActivity) this.getActivity();

        title.setText(bookDetailsActivity.bookInfo.getTitle());
        author.setText(bookDetailsActivity.bookInfo.getAuthor());
        originalLanguage.setText(bookDetailsActivity.bookInfo.getOriginalLanguage());
        yearOfFirsEdition.setText(bookDetailsActivity.bookInfo.getYearOfFirsEdition());
        category.setText(bookDetailsActivity.bookInfo.getCategory());
        genre.setText(bookDetailsActivity.bookInfo.getGenre());
        form.setText(bookDetailsActivity.bookInfo.getForm());
        publisherNote.setText(bookDetailsActivity.bookInfo.getPublisherNote());
        cover.setImageDrawable(loadImageFromWebOperations(bookDetailsActivity.bookInfo.getPicturePath()));
    }

    public static Drawable loadImageFromWebOperations(String url) {
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
