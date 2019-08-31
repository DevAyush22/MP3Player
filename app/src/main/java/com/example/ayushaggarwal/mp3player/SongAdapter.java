package com.example.ayushaggarwal.mp3player;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param songs   A List of song objects to display in a list.
     */
    SongAdapter(Activity context, ArrayList<Song> songs) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songs);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.songs_list_item, parent, false);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to
            holder = new ViewHolder();

            // Find the TextView in the songs_list_item.xml layout with the ID name_text_view
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);

            // Find the TextView in the songs_list_item.xml layout with the ID desc_text_view
            holder.descTextView = (TextView) convertView.findViewById(R.id.desc_text_view);

            // Find the ImageView in the songs_list_item.xml layout with the ID image
            holder.iconView = (ImageView) convertView.findViewById(R.id.image);

            // store the holder with the view.
            convertView.setTag(holder);

        } else {
            // We've just avoided calling findViewById() on resource every time
            // just use the viewHolder
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        if (currentSong != null) {
            // Get the name from the current Song object and
            // set this text on the name TextView
            holder.nameTextView.setText(currentSong.getSongName());

            // Get the description from the current Song object and
            // set this text on the desc TextView
            holder.descTextView.setText(currentSong.getSongDesc());

            // Get the image from the current Song object
            holder.iconView.setImageResource(currentSong.getImageResourceId());
        }

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return convertView;
    }

    /**
     * ViewHolder class to hold exact set of views
     */
    static class ViewHolder {
        TextView nameTextView;
        TextView descTextView;
        ImageView iconView;
    }

}