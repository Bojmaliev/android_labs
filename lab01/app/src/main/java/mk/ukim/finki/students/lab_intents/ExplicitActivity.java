package mk.ukim.finki.students.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExplicitActivity extends AppCompatActivity {

    Button buttonOk;
    Button buttonCancel;
    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
        initViews();
        initListeners();
    }

    private void initViews() {
        buttonOk = findViewById(R.id.buttonOK);
        buttonCancel = findViewById(R.id.buttonCancel);
        editText = findViewById(R.id.editText);
    }

    private void initListeners() {
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", editText.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });
    }
}
