package weeia.isbnapp.activities;

import android.app.Activity;
import android.content.Context;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import weeia.isbnapp.R;
import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.opinions.BookGrade;
import weeia.isbnapp.lbmodule.models.BookSuggestion;

/**
 * Created by sew on 2017-05-22.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<BookSuggestion> mDataSource;

    public ListViewAdapter(Context context, ArrayList<BookSuggestion> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = mInflater.inflate(R.layout.proposed_book_list_item, parent, false);
        TextView  tx = (TextView) rowView.findViewById(R.id.testTextView);
        tx.setText(mDataSource.get(position).title);
        return rowView;
    }
}
