package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("OK" + counter_name.getText() + counter_value.getText() + counter_comment.getText());
                // add the new counter
                Counter c = new Counter(counter_name.getText().toString(),
                        Integer.parseInt(counter_value.getText().toString()),
                        counter_comment.getText().toString());
                MainActivity.counters.add(c);
                MainActivity.counterAdapter.notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("Cancel");
            }
        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public void editCounterDialog(final Counter counter){
        // set up the alert dialog
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.edit_counter_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText initial_counter_value = (EditText) promptView.findViewById(R.id.initial_counter_value_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);
        final Button delete_button = (Button) promptView.findViewById(R.id.delete_button);

        // prefill the values into the edit dialog
        counter_name.setText(counter.getName());
        initial_counter_value.setText(Integer.toString(counter.getInitialValue()));
        counter_value.setText(Integer.toString(counter.getCurrentValue()));
        counter_comment.setText(counter.getComment());

        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("OK" + counter_name.getText() + counter_value.getText() + counter_comment.getText());

                counter.editCounter(counter_name.getText().toString(),
                        Integer.parseInt(initial_counter_value.getText().toString()),
                        Integer.parseInt(counter_value.getText().toString()),
                        counter_comment.getText().toString());

                MainActivity.counterAdapter.notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("Cancel");
            }
        });
        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.counters.remove(counter);
                MainActivity.counterAdapter.notifyDataSetChanged();
                alert.cancel();
            }
        });

    }
}
