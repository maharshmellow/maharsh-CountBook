package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by maharshmellow on 2017-09-29.
 */

public class Dialog{
    private Context context;

    public Dialog(Context context){
        this.context = context;
    }
    public void newCounterDialog(){
        // set up the alert dialog
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.add_counter_dialog, null);
        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);

        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(promptView)
                .setTitle("New Counter")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (counter_name.getText().toString().trim().length() == 0){
                            counter_name.setError("Please provide a counter name");
                        }
                        else if (counter_value.getText().toString().trim().length() == 0){
                            counter_value.setError("Please provide a counter value");
                        }
                        else{
                            System.out.println("OK" + counter_name.getText() + counter_value.getText() + counter_comment.getText());
                            // add the new counter
                            Counter c = new Counter(counter_name.getText().toString(),
                                    Integer.parseInt(counter_value.getText().toString()),
                                    counter_comment.getText().toString());
                            MainActivity.counters.add(c);
                            MainActivity.counterAdapter.notifyDataSetChanged();
                            MainActivity.totalCountersField.setText(MainActivity.counters.size() + " counters");

                            MainActivity.saveData(context);
                            d.dismiss();
                        }
                    }
                });
            }
        });
        d.show();

    }

    public void editCounterDialog(final Counter counter){
        // set up the alert dialog
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.edit_counter_dialog, null);

        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText initial_counter_value = (EditText) promptView.findViewById(R.id.initial_counter_value_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);
        final Button reset_button = (Button) promptView.findViewById(R.id.reset_button);
        final Button delete_button = (Button) promptView.findViewById(R.id.delete_button);

        //pre-fill the values into the edit dialog
        counter_name.setText(counter.getName());
        initial_counter_value.setText(Integer.toString(counter.getInitialValue()));
        counter_value.setText(Integer.toString(counter.getCurrentValue()));
        counter_comment.setText(counter.getComment());

        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(promptView)
                .setTitle("Edit Counter")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();


        d.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (counter_name.getText().toString().trim().length() == 0){
                            counter_name.setError("Please provide a counter name");
                        }
                        else if (counter_value.getText().toString().trim().length() == 0){
                            counter_name.setError("Please provide a counter value");
                        }
                        else if (initial_counter_value.getText().toString().trim().length() == 0){
                            initial_counter_value.setError("Please provide an initial counter value");
                        }
                        else{
                            System.out.println("OK" + counter_name.getText() + counter_value.getText() + counter_comment.getText());

                            counter.editCounter(counter_name.getText().toString(),
                                    Integer.parseInt(initial_counter_value.getText().toString()),
                                    Integer.parseInt(counter_value.getText().toString()),
                                    counter_comment.getText().toString());

                            MainActivity.counterAdapter.notifyDataSetChanged();
                            MainActivity.saveData(context);
                            d.dismiss();
                        }
                    }
                });

            }
        });
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.reset();
                MainActivity.counterAdapter.notifyDataSetChanged();
                MainActivity.saveData(context);
                d.dismiss();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.counters.remove(counter);
                MainActivity.counterAdapter.notifyDataSetChanged();
                MainActivity.totalCountersField.setText(MainActivity.counters.size() + " counters");
                MainActivity.saveData(context);
                // close the alert since the counter is now deleted
                d.dismiss();

            }
        });

        d.show();



    }
}
