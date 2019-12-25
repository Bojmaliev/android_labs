package mk.ukim.finki.students.moviesomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button movies;
    public Button banks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = findViewById(R.id.button);
        banks = findViewById(R.id.openBanksActivity);
        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExplicitActivity();
            }
        });
        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBanksActivity();
            }
        });
    }
    private void openExplicitActivity(){
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }
    private void openBanksActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
