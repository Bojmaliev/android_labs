package mk.ukim.finki.students.moviesomdb.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import mk.ukim.finki.students.moviesomdb.R;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

public class CustomListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView title;
    private TextView year;

    public CustomListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        year = itemView.findViewById(R.id.year);

        itemView.setTag(this);
    }

    public void setInformations(Context c, OMDbMovie movie, View.OnClickListener listener) {
        this.title.setText(movie.Title);
        this.year.setText(movie.Year);
        Picasso.with(c).load(movie.Poster).into(imageView);
        itemView.setOnClickListener(listener);
    }
}
