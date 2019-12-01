package mk.ukim.finki.students.moviesomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = findViewById(R.id.button);
        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExplicitActivity();
            }
        });
    }
    private void openExplicitActivity(){
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }
}
