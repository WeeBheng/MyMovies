package sg.edu.rp.c346.id22016788.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.versionList = objects;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvVersion = rowView.findViewById(R.id.imageViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvRating = rowView.findViewById(R.id.textViewRating);


        // Obtain the Android Version information based on the position
        Movies currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getMovieTitle());
        tvVersion.setText(currentVersion.getGenre());
        tvYear.setText(currentVersion.getMovieYear());
        tvRating.setText(currentVersion.getMovieRating());

        if (currentVersion.getGenre() == "PG13") {
            ivGenre.setImageResource(R.drawable.rating_pg13);
        } else if (currentVersion.getGenre() == "M18"){
            ivGenre.setImageResource(R.drawable.rating_m18);
        } else {
            ivGenre.setImageResource(R.drawable.rating_nc16);
        }

        return rowView;
    }

}