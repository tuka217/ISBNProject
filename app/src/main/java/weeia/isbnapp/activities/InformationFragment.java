package weeia.isbnapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import weeia.isbnapp.R;

public class InformationFragment extends Fragment {

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
        title.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getTitle());
        author.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getAuthor());
        originalLanguage.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getOriginalLanguage());
        yearOfFirsEdition.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getYearOfFirsEdition());
        category.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getCategory());
        genre.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getGenre());
        form.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getForm());
        publisherNote.setText(((BookDetailsActivity) this.getActivity()).bookInfo.getPublisherNote());
    }
}
