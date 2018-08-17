package com.example.st_peter_hq.instrumple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InstrumentAdapter extends ArrayAdapter<Instrument> {

    public InstrumentAdapter(Context context, ArrayList<Instrument> instruments) {
        super(context, 0, instruments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate( R.layout.list_item, parent, false );
        }

        // Get the {@link Instrument} object located at this position in the list
        Instrument currentInstrument = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.item_text_view);

        miwokTextView.setText(currentInstrument.getInstrumentName());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        imageView.setImageResource(currentInstrument.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
