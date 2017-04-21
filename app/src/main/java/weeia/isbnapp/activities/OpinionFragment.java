package weeia.isbnapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import weeia.isbnapp.R;
import weeia.isbnapp.book.opinions.BookGrade;
import weeia.isbnapp.book.opinions.BookOpinion;

public class OpinionFragment extends Fragment {

    private LinearLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opinion, container, false);
        tabLayout = (LinearLayout) view.findViewById(R.id.opinion_layout);
        addGradesToView();
        addOpinionsToView();
        return view;
    }

    private void addOpinionsToView(){
        List<BookOpinion> bookOpinions = ((BookDetailsActivity) this.getActivity()).bookOpinionsTest.getBookOpinions();

        for(BookOpinion bookOpinion: bookOpinions){
            addOpinionBoxToView(bookOpinion);
        }
    }

    private void addOpinionBoxToView(BookOpinion bookOpinion){
        LinearLayout opinionBox = (LinearLayout) View.inflate(getContext(), R.layout.opinion_box_template, null);

        ((TextView) opinionBox.getChildAt(0)).setText(bookOpinion.getOpinionTitle());
        ((TextView) opinionBox.getChildAt(1)).setText(bookOpinion.getOpinionFragment());

        Button opinionRedirection = (Button) opinionBox.getChildAt(2);
        opinionRedirection.setText(opinionRedirection.getText() + " " + bookOpinion.getSourceSiteName());

        tabLayout.addView(opinionBox);
    }

    private void addGradesToView(){
        List<BookGrade> bookGrades = ((BookDetailsActivity) this.getActivity()).bookOpinionsTest.getBookGrades();

        for(BookGrade bookGrade : bookGrades){
            addGradeBoxToView(bookGrade);
        }
    }

    private void addGradeBoxToView(BookGrade bookGrade){
        LinearLayout opinionBox = (LinearLayout) View.inflate(getContext(), R.layout.grade_box_template, null);

        ((TextView) opinionBox.getChildAt(0)).setText(bookGrade.getSourceSite());
        String grade = bookGrade.getBookGrade().toString() + " / " + bookGrade.getMaxGrade().toString();
        ((TextView) opinionBox.getChildAt(1)).setText(grade);

        tabLayout.addView(opinionBox);
    }
}