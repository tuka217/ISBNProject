package weeia.isbnapp.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.R;
import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.book.info.BookInfoTest;
import weeia.isbnapp.book.info.BookInfoTestExample;
import weeia.isbnapp.book.info.EmptyBookInfo;
import weeia.isbnapp.book.offers.BookOffersTest;
import weeia.isbnapp.book.opinions.BookGrade;
import weeia.isbnapp.book.opinions.BookGradeTest;
import weeia.isbnapp.book.opinions.BookOpinion;
import weeia.isbnapp.book.opinions.BookOpinionsTest;
import weeia.isbnapp.lbmodule.IOpinionsPresenter;
import weeia.isbnapp.lbmodule.OpinionsPresenter;
import weeia.isbnapp.lbmodule.models.BookGeneralInfo;
import weeia.isbnapp.lbmodule.models.BookOpinionOpinionsPresenterDto;

public class BookDetailsActivity extends AppCompatActivity {

    public BookInfo bookInfo;
    public BookOpinionsTest bookOpinionsTest;
    public BookOffersTest bookOffersTest;
    public IOpinionsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        presenter = new OpinionsPresenter();

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
            try {
                String bookTitleOrISBN = b.getString("titleOrISBN");
                BookGeneralInfo generalInfo = new BookGeneralInfo(b.getString("bookGeneralInfoId"),
                        b.getString("bookGeneralInfoAuthors"),b.getString("bookGeneralInfoTitle"), b.getString("bookGeneralInfoCoverUrl"));
                initializeBookClasses(bookTitleOrISBN,generalInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
                redirect();
            } catch (ExecutionException e) {
                e.printStackTrace();
                redirect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                redirect();
            }
            catch (Exception ex) {
                redirect();
            }

            initializeTabs();
        }


    }

    private void initializeBookClasses(String bookTitleOrISBN, BookGeneralInfo bookGeneralInfo) throws InterruptedException, ExecutionException, MalformedURLException {
        bookInfo = presenter.ProvideBookInfo(bookTitleOrISBN,bookGeneralInfo);
        if(bookInfo instanceof EmptyBookInfo)
        {
            redirect();
        }
        List<BookOpinion> bookOpinions= presenter.ProvideOpinions(bookTitleOrISBN,bookGeneralInfo);
        bookOpinionsTest = new BookOpinionsTest(bookTitleOrISBN);
        bookOpinionsTest.setBookOpinions(bookOpinions);
        bookOpinionsTest.addBookGrade(new BookGradeTest(bookInfo.getGrade(), bookInfo.getGrade(), "LubimyCzytać.pl"));
        bookOffersTest = new BookOffersTest(bookTitleOrISBN);
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
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void redirect()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "No book found",Toast.LENGTH_SHORT);
        toast.show();
        Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(startIntent);
    }
}
