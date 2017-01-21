package com.tsj.dat153.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsj.dat153.model.Person;
import com.tsj.dat153.oblig1.R;

import java.util.ArrayList;

import static android.support.design.R.styleable.View;

/**
 * Created by Sindre on 21.01.2017.
 */

public class PersonListViewAdapter extends ArrayAdapter<Person> {

    private static class ViewHolder {
        TextView name;
    }

    public PersonListViewAdapter(Context context, ArrayList<Person> persons){
        super(context, 0, persons);
    }

    @Override
    public android.view.View getView(int position, View convertView, ViewGroup parent){
        Person person = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_main_listview, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(person.getName());

        return convertView;
    }
}
