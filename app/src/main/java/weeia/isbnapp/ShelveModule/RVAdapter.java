package weeia.isbnapp.ShelveModule;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import weeia.isbnapp.R;

/**
 * Created by MATEUSZ on 22.05.2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ShelveViewHolder> {
    List<ShelveItem> items;
    int mExpandedPosition = -1;

    public RVAdapter(List<ShelveItem> items) {
        this.items = items;
    }

    @Override
    public ShelveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shelve_item_cardview, parent, false);
        ShelveViewHolder pvh = new ShelveViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ShelveViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.author.setText(items.get(position).getAuthor());
        holder.issueYear.setText("" + items.get(position).getReleaseYear() + "");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(items.get(position).getAddedDate());
        holder.createDate.setText(format);
        holder.isRead.setText(items.get(position).isRead() ? "Przeczytałeś tę książkę" : "Do przeczytania");
//        Drawable myDrawable = ResourcesCompat.getDrawable(Resources.getSystem(),R.drawable.wszystkiewojny,null);//mock
        holder.picture.setImageResource(R.drawable.wszystkiewojny);
//        final boolean isExpanded = position == mExpandedPosition;
//        final int finPosition = position;
//        holder.cv.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
//        holder.itemView.setActivated(isExpanded);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mExpandedPosition = isExpanded ? -1:finPosition;
//                TransitionManager.beginDelayedTransition(holder.cv);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ShelveViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
         TextView title;
         TextView author;
         TextView issueYear;
         TextView createDate;
         TextView isRead;
         ImageView picture;

        ShelveViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            picture = (ImageView) itemView.findViewById(R.id.picture);
            issueYear = (TextView) itemView.findViewById(R.id.issueYear);
            createDate = (TextView) itemView.findViewById(R.id.createDate);
            isRead = (TextView) itemView.findViewById(R.id.isRead);
        }

    }
}
