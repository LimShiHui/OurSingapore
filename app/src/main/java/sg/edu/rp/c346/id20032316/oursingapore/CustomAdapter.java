package sg.edu.rp.c346.id20032316.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context context;
    int id;
    ArrayList<Island> islandList;

    public CustomAdapter(Context context, int resource, ArrayList<Island> object) {
        super(context, resource, object);

        this.context = context;
        this.id = resource;
        this.islandList = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        TextView tvSize = rowView.findViewById(R.id.tvSize);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);

        Island currentIsland = islandList.get(position);

        tvName.setText(currentIsland.getName());
        tvDescription.setText(currentIsland.getDescription());
        String size = String.valueOf(currentIsland.getSquareKm());
        tvSize.setText(size);
        ratingBar.setEnabled(false);
        float stars = currentIsland.getStars();
        ratingBar.setRating(stars);

        return rowView;
    }
}
