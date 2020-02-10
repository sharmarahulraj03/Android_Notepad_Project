package ca.rahulsharma.notepad;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.rahulsharma.notepad.R;

/**
 * @author Rahul Raj Sharma
 * created on 9/4/2018
 */
public class NotepadAdapter extends ArrayAdapter<Note> {
    private Activity mContext;
    private ArrayList<Note> notes;

    /**
     * @param context Activity
     * @param list    ArrayList type Note
     */
    NotepadAdapter(Activity context, ArrayList<Note> list) {
        super(context, R.layout.notepad_list_view, list);
        this.mContext = context;
        this.notes = list;
    }


    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.mContext.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.notepad_list_view, parent, false);
        }

        //Set the name
        ((TextView) view.findViewById(R.id.name)).setText(notes.get(position).getName());

        return view;
    }

}
