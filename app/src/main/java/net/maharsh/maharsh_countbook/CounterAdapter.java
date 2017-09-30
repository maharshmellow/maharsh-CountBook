package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maharshmellow on 2017-09-27.
 */

public class CounterAdapter extends ArrayAdapter<Counter> {

    private Context context;
    private List<Counter> counterList = new ArrayList<>();


    public CounterAdapter(Context context, ArrayList<Counter> list) {
        super(context, 0 , list);
        this.context = context;
        this.counterList = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.counter_item,parent,false);

        final Counter counter = MainActivity.counters.get(position);


        final TextView name = (TextView) listItem.findViewById(R.id.counter_text);
        name.setText(counter.getName());

        final TextView counterValue = (TextView) listItem.findViewById(R.id.counter_value);
        counterValue.setText(counter.getCurrentValue() + "");

        Button incrementButton = (Button) listItem.findViewById(R.id.increment_counter_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter.increment();
                counterValue.setText(counter.getCurrentValue() + "");
                System.out.println("Increment, New Value" + counter.getCurrentValue());
            }
        });

        Button decrementButton = (Button) listItem.findViewById(R.id.decrement_counter_button);
        decrementButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter.decrement();
                counterValue.setText(counter.getCurrentValue() + "");
                System.out.println("Decrement, New Value" + counter.getCurrentValue());
            }
        });

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Counter counter = MainActivity.counters.get(position);
                System.out.println("clicked row");
                Dialog d = new Dialog(context);
                d.editCounterDialog(counter);
                System.out.println("clicked row");

                System.out.println(counter.getName());
                //Toast.makeText(null,"Clicked",Toast.LENGTH_LONG);
            }
        });

        return listItem;
    }
}
