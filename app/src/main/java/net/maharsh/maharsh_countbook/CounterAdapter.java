package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom ArrayAdapter for the ListView. When anything is updated, this class is in charge of
 * updating the text that is shown each row of the list view. When the increment or decrement
 * buttons are clicked, the values will be updated here. When the row itself is clicked,
 * it will open an edit counter dialog of class Dialog from here as well. After incrementing and
 * decrementing, it will call the static saveData method from the MainActivity class to save the
 * data locally.
 */

public class CounterAdapter extends ArrayAdapter<Counter> {

    private Context context;
    private List<Counter> counterList = new ArrayList<>();

    /**
     * Initializes the CounterAdapter with the values of the list provided by the MainActivity
     * @param context
     * @param list
     */
    public CounterAdapter(Context context, ArrayList<Counter> list) {
        super(context, 0 , list);
        this.context = context;
        this.counterList = list;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.counter_item, parent, false);

        final Counter counter = MainActivity.counters.get(position);

        final TextView name = (TextView) listItem.findViewById(R.id.counter_text);
        name.setText(counter.getName());

        final TextView counterValue = (TextView) listItem.findViewById(R.id.counter_value);
        counterValue.setText(counter.getCurrentValue() + "");

        final TextView date = (TextView) listItem.findViewById(R.id.counter_date);
        date.setText("Date Modified: " + counter.getDateModified());

        final TextView initialValue = (TextView) listItem.findViewById(R.id.counter_initial_value);
        initialValue.setText("Initial Value: " + counter.getInitialValue());

        final TextView comment = (TextView) listItem.findViewById(R.id.counter_comment);
        comment.setText("Comment: " + counter.getComment());

        // event listeners for the increment and decrement buttons
        Button incrementButton = (Button) listItem.findViewById(R.id.increment_counter_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter.increment();
                counterValue.setText(counter.getCurrentValue() + "");
                MainActivity.saveData(context);
            }
        });

        Button decrementButton = (Button) listItem.findViewById(R.id.decrement_counter_button);
        decrementButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter.decrement();
                counterValue.setText(counter.getCurrentValue() + "");
                MainActivity.saveData(context);
            }
        });
        // event listener for the row - when clicked open the edit counter dialog
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new Dialog(context);
                d.editCounterDialog(counter);
                System.out.println(counter.getName());
            }
        });

        return listItem;
    }
}
