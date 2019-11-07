package mk.ukim.finki.students.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.logging.Logger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mk.ukim.finki.students.lab_intents.service.LoggingService;

public class MainActivity extends AppCompatActivity {
    public final static int OPEN_EXPLICIT_ACTIVITY = 1;
    public final static int PICK_IMAGE = 2;
    private Logger logger = Logger.getLogger("MainActivity");
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initListeners() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExplicitActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImplicitActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                choosePicture();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();
            }
        });
    }

    private void startService() {
        Intent intent = new Intent(MainActivity.this, LoggingService.class);
        startService(intent);
    }

    private void openPicture(String picture) {

    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    };

    private void shareContent() {
        Intent shareContentIntent = new Intent();
        shareContentIntent.setAction(Intent.ACTION_SEND);

        shareContentIntent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title ");
        shareContentIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity");
        shareContentIntent.setType("text/plain");
//        implicitIntent.addCategory("android.intent.category.DEFAULT");

        Intent chooser = Intent.createChooser(shareContentIntent, "Choose the desired app!");

        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    private void openImplicitActivity() {
        Intent shareContentIntent = new Intent();
        shareContentIntent.setAction("mk.ukim.finki.students.lab_intents.IMPLICIT_ACTION");

//        implicitIntent.addCategory("android.intent.category.DEFAULT");

        Intent chooser = Intent.createChooser(shareContentIntent, "Choose the desired app!");

        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }


    private void openExplicitActivity() {
        Intent intent = new Intent(this, ExplicitActivity.class);
        startActivityForResult(intent, OPEN_EXPLICIT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case OPEN_EXPLICIT_ACTIVITY:
                if (resultCode == Activity.RESULT_OK) {
                    textView.setText(data.getStringExtra("result"));
                } else {
                    textView.setText("Task was canceled");
                }
                break;
            case PICK_IMAGE:
                if(resultCode==RESULT_CANCELED)
                {
                    textView.setText("Task was canceled");
                }else if(resultCode==RESULT_OK) {
                    Uri selectedImg = data.getData();
                    openImage(selectedImg);
                }
                break;

        }
    }

    private void openImage(Uri selectedImg) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(selectedImg, "image/*");
        startActivity(intent);
    }

    private void initViews() {
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        textView = findViewById(R.id.textView);
    }


}
