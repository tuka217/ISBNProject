package weeia.isbnapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import weeia.isbnapp.R;
import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.info.BookInfoTest;
import weeia.isbnapp.book.opinions.BookOpinionsTest;

public class BookDetailsActivity extends AppCompatActivity {

    public BookInfo bookInfo;
    public BookOpinionsTest bookOpinionsTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.added_to_library, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle b = getIntent().getExtras();
        if(b != null) {
            String bookTitleOrISBN = b.getString("titleOrISBN");
            initializeBookClasses(bookTitleOrISBN);
            initializeTabs();
        }
    }

    private void initializeBookClasses(String bookTitleOrISBN){
        bookInfo = new BookInfoTest(bookTitleOrISBN);
        bookOpinionsTest = new BookOpinionsTest(bookTitleOrISBN);
    }

    private void initializeTabs(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.information_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.opinions_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.price_tab));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
