
package weeia.isbnapp.activities;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import android.util.SparseArray;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.Toast;

import weeia.isbnapp.R;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import weeia.isbnapp.api.GoogleBooksApi;
import weeia.isbnapp.book.info.BookInfo;
import weeia.isbnapp.lbmodule.IOpinionsPresenter;
import weeia.isbnapp.lbmodule.OpinionsPresenter;
import weeia.isbnapp.lbmodule.models.BookSuggestion;

public class MainActivity extends AppCompatActivity {
    private Uri fileUri;
    TextView titleOrISBN;
    public IOpinionsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView proposedBookListView = (ListView) findViewById(R.id.proposed_books_listview);

        final ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);
        presenter = new OpinionsPresenter();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                titleOrISBN = (TextView) findViewById(R.id.titleOrISBN);


                presenter = new OpinionsPresenter();
                ListViewAdapter listViewAdapter = null;
                try {
                    ArrayList<BookSuggestion> suggestions = presenter.ProvideBookSuggestion(titleOrISBN.getText().toString());
                    listViewAdapter = new ListViewAdapter(getBaseContext(),suggestions);
                    proposedBookListView.setAdapter(listViewAdapter);
                    if(suggestions.size()==0)
                        Toast.makeText(getApplication(),"No such book found",Toast.LENGTH_SHORT);
                }catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                proposedBookListView.setAdapter(listViewAdapter);
            }
        });
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        proposedBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object item = parent.getItemAtPosition(position);
                BookSuggestion suggestion = (BookSuggestion)item;
                Intent startIntent = new Intent(getApplicationContext(),BookDetailsActivity.class);
                Bundle b = new Bundle();
                b.putString("titleOrISBN", titleOrISBN.getText().toString());
                b.putString("bookGeneralInfoId", suggestion.id.toString());
                b.putString("bookGeneralInfoAuthors", suggestion.authors.toString());
                b.putString("bookGeneralInfoTitle", suggestion.title.toString());
                b.putString("bookGeneralInfoCoverUrl", suggestion.coverUrl.toString());
                startIntent.putExtras(b);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri();
        takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT, fileUri );
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File imgFile = new  File(fileUri.getPath());
            if(imgFile.exists() && imgFile.canRead()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getPath());
                BarcodeDetector detector =
                        new BarcodeDetector.Builder(getApplicationContext())
                                .setBarcodeFormats(Barcode.ALL_FORMATS)
                                .build();
                if (!detector.isOperational()) {
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Barcode> barcodes = detector.detect(frame);
                if (barcodes.size() > 0) {
                    Barcode thisCode = barcodes.valueAt(0);
                    GoogleBooksApi api = new GoogleBooksApi();
                    String bookTitle = api.GetBookTitleByISBN(thisCode.rawValue);
                    System.out.println(thisCode.rawValue);
                    if(bookTitle!=null)
                    {
                        EditText txt = (EditText) findViewById(R.id.titleOrISBN);
                        txt.setText("");
                        txt.setText(bookTitle);
                    }
                }
            }
        }
    }
    private static Uri getOutputMediaFileUri(){
        return Uri.fromFile(getOutputMediaFile());
    }
    private static File getOutputMediaFile(){
        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        boolean canRead =  Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).canRead();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
        return mediaFile;

    }

}