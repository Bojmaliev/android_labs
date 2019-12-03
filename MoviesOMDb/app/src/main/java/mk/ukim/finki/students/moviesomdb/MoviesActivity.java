package mk.ukim.finki.students.moviesomdb;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.logging.Logger;

import mk.ukim.finki.students.moviesomdb.adapters.CustomListAdapter;
import mk.ukim.finki.students.moviesomdb.holders.CustomListViewHolder;
import mk.ukim.finki.students.moviesomdb.viewmodels.MoviesViewModel;


public class MoviesActivity extends AppCompatActivity {

    CustomListAdapter adapter;
    Logger logger = Logger.getLogger("MoviesActivity");
    MoviesViewModel moviesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        initToolbar();
        initListView();
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

    }

    private void loadData(String query) {
        moviesViewModel.fetchData(query);
        moviesViewModel.getAll().observe(this, data -> {
            adapter.updateDataset(data);
        });
    }

    private void initListView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomListAdapter(getApplicationContext(), getItemViewOnClickListener());
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
    }
    


    private View.OnClickListener getItemViewOnClickListener() {
        return v -> {
             CustomListViewHolder holder = (CustomListViewHolder) v.getTag();
            String selectedTrackId = adapter.getClickedItemId(holder);
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra("id", selectedTrackId);
            startActivity(intent);
            logger.info(selectedTrackId);
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item1).getActionView();
        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener() {

        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadData(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

}
