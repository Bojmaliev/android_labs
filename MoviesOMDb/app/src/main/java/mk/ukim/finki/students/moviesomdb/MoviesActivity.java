package mk.ukim.finki.students.moviesomdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mk.ukim.finki.students.moviesomdb.adapters.CustomListAdapter;
import mk.ukim.finki.students.moviesomdb.asynctask.OMDbMoviesAsyncTask;
import mk.ukim.finki.students.moviesomdb.holders.CustomListViewHolder;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;


public class MoviesActivity extends AppCompatActivity implements MoviesInterface {

    List<String> dataset;
    CustomListAdapter adapter;
    Logger logger = Logger.getLogger("MoviesActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataset = new ArrayList<>();
        dataset.add("Search to show results");

        adapter = new CustomListAdapter(dataset, getItemViewOnClickListener());
        recyclerView.setAdapter(adapter);

        OMDbMoviesAsyncTask asyncTask = new OMDbMoviesAsyncTask(this);
        asyncTask.execute("Game");

    }

    @Override
    public void loadedOMDbMovies(OMDbMovies omDbMovies) {
//        for (OMDbMovie movie : omDbMovies.Search) {
//            logger.info(movie.Title);
//        }
    }

    private View.OnClickListener getItemViewOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewHolder holder = (CustomListViewHolder) v.getTag();

                int adapterPosition = holder.getAdapterPosition();

                logger.info("Clicked: " + dataset.get(adapterPosition));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_item1).getActionView();
        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_item1:
//                ///
//                logger.info("Clicked menu item: 1");
//                break;
//            case R.id.menu_item2:
//                ///
//                logger.info("Clicked menu item: 2");
//                break;
//            case R.id.menu_item3:
//                //
//                logger.info("Clicked menu item: 3");
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    private SearchView.OnQueryTextListener getOnQueryTextListener() {

        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                logger.info(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                logger.info("Query text change: " + newText);
                return false;
            }
        };
    }

}
