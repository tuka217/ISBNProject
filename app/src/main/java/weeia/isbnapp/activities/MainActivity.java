
package weeia.isbnapp.activities;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.util.SparseArray;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import weeia.isbnapp.R;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import weeia.isbnapp.api.GoogleBooksApi;

public class MainActivity extends AppCompatActivity {
    private Uri fileUri;
    TextView titleOrISBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                titleOrISBN = (TextView) findViewById(R.id.titleOrISBN);

                Intent startIntent = new Intent(getApplicationContext(),BookDetailsActivity.class);
                Bundle b = new Bundle();
                b.putString("titleOrISBN", titleOrISBN.getText().toString());
                startIntent.putExtras(b);
                startActivity(startIntent);
            }
        });
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
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
                    else{
                        showAlertDialog(R.string.ISBN_not_found, R.string.hint_try_to_search_title);
                    }
                }else{
                    showAlertDialog(R.string.ISBN_not_recognized, R.string.hint_redo_photo);
                }
            }else{
                showAlertDialog(R.string.Cannot_open_photo, R.string.hint_check_rights_to_open_photo);
            }
        }else{
            showAlertDialog(R.string.error_photo, R.string.hint_redo_photo);
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

    private void showAlertDialog(int messageTitle, int message){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage(message);
        dlgAlert.setTitle(messageTitle);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}