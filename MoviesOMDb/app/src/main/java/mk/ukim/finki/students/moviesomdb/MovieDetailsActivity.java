package mk.ukim.finki.students.moviesomdb;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;
import mk.ukim.finki.students.moviesomdb.viewmodels.MoviesViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    MoviesViewModel moviesViewModel;

    TextView title;
    TextView year;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setSupportActionBar(findViewById(R.id.custom_toolbar));
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initViews();
        initMovie();
    }

    private void initViews() {
        title = findViewById(R.id.title);
        imageView = findViewById(R.id.image);
        year = findViewById(R.id.year);

    }
    private void initMovie() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        moviesViewModel.getById(id).observe(this, data->{

            assert data != null;
            title.setText(data.Title);
            year.setText(data.Year);
            Picasso.with(getApplicationContext()).load(data.Poster).into(imageView);
        });

    }

}
