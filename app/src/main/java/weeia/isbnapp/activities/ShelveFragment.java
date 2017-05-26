package weeia.isbnapp.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weeia.isbnapp.R;
import weeia.isbnapp.ShelveModule.RVAdapter;
import weeia.isbnapp.ShelveModule.ShelveHttpClient;
import weeia.isbnapp.ShelveModule.ShelveItem;
import weeia.isbnapp.ShelveModule.ShelveItemDto;

/**
 * Created by MATEUSZ on 05.05.2017.
 */

public class ShelveFragment extends AppCompatActivity {
    private RecyclerView rv;
    private List<ShelveItem> items;

    private void LoadBooksInShelve()
    {
        //Zamockowane dane:
        items.add(new ShelveItem("Title 1","Author 1",2016,new Date(),"doesn't really matter",true));
        items.add(new ShelveItem("Title 2","Author 2",2011,new Date(),"doesn't really matter",true));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<ShelveItem>();
        LoadBooksInShelve();
        setContentView(R.layout.fragment_shelve);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(items);
        rv.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private  List<ShelveItem> map(List<ShelveItemDto> lista)
    {
        List<ShelveItem> mappedList = new ArrayList<ShelveItem>();
        for (ShelveItemDto  item:lista ) {
            mappedList.add(new ShelveItem(item.getTitle(), item.getAuthor(), item.getPublishDate().getYear(),
                    item.getPublishDate(), "MOCK", false));
        }
        return  mappedList;
    }
    private  ShelveItemDto mapReverse(ShelveItem item)
    {
        return new ShelveItemDto("MOCK", 1, item.getTitle(),
                item.getAuthor(), "MOCK", new java.sql.Date(item.getAddedDate().getTime()));


    }
    private String buildURL(int id)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("https://isbnprojectapi.herokuapp.com/api/user/shelve/");
        sb.append(id);
        return sb.toString();
    }
    class GetShelveAsync extends AsyncTask<Integer, Void, List<ShelveItem>> {

        protected List<ShelveItem> doInBackground(Integer... params) {
            List<ShelveItem> shelveItems = new ArrayList<ShelveItem>();
            try {
                ArrayList<ShelveItemDto> items = new ArrayList<ShelveItemDto>();
                weeia.isbnapp.ShelveModule.IContentProvider contentProvider = new ShelveHttpClient(new URL(buildURL(params[0])));
               JsonArray obj = contentProvider.ProvideContent();
                if(obj!=null)
                {
                    Gson gson = new Gson();
                 List<ShelveItemDto> books =  gson.fromJson(obj, new TypeToken<List<ShelveItemDto>>(){}.getType());
                    shelveItems = map(books);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  shelveItems;
        }
    }
    class PutToShelveAsync extends AsyncTask<ShelveItem, Void, Integer> {

        protected Integer doInBackground(ShelveItem... params) {
            ShelveItemDto shelveItem = mapReverse(params[0]);
            Integer result = 0;
            try {

                weeia.isbnapp.ShelveModule.IPutContent contentPut = new ShelveHttpClient(new URL(buildURL(1)),shelveItem);
                result = contentPut.putContent();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
