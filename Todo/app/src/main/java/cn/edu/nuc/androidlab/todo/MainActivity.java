package cn.edu.nuc.androidlab.todo;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderPDF();
                Snackbar.make(view, "Click", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void builderPDF() {
        Document doc = new Document();
        doc.open();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.pdf";
            Log.i(TAG, path);
            File file = new File(path);
            if(!file.exists())
                file.createNewFile();
            if(file.exists()){
                FileOutputStream fs = new FileOutputStream(file);
                PdfWriter.getInstance(doc, fs);
                doc.open();
                Log.i(TAG, "builderPDF");
                Paragraph par = new Paragraph("PDF 文件测试");
                doc.add(par);
                doc.close();
                Log.i(TAG, "builder");
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

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
}
