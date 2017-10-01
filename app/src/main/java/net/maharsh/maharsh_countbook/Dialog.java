package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Shows the popup dialogs for adding a new counter and editing a counter. Error checking for
 * the inputted values is also done here and when anything is changed, the saveData method from
 * MainActivity is called to save the latest changes locally.
 */

public class Dialog{
    private Context context;

    /**
     * Loads the context before doing any dialogs are shown
     * @param context
     */
    public Dialog(Context context){
        this.context = context;
    }

    /**
     * Shows the popup dialog when the Add button is pressed at the top right of the screen
     * Error checking will also be done, and when everything is proper, the new counter will
     * be added to the array in the MainActivity class and the data will be saved locally
     */
    public void newCounterDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.add_counter_dialog, null);

        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);

        // initialize the dialog
        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(promptView)
                .setTitle("New Counter")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // event listener for the OK button
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // error checking for the fields
                        if (counter_name.getText().toString().trim().length() == 0){
                            counter_name.setError("Please provide a counter name");
                        }
                        else if (counter_value.getText().toString().trim().length() == 0){
                            counter_value.setError("Please provide a counter value");
                        }
                        else{
                            // all the input is proper - add the new counter
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

    /**
     * Shows the popup dialog when a counter is clicked (the row on a list view)
     * Error checking will also be done, and when everything is proper, the edited counter will
     * be updated in the array in the MainActivity class and the new data will be saved locally
     */
    public void editCounterDialog(final Counter counter){
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

        // initialize the dialog
        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(promptView)
                .setTitle("Edit Counter")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                // event listener for the OK button
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // error checking for the fields
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
                            // all the input is proper - edit the existing counter
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
        // action listener for the reset button - when clicked, set value to initial value
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.reset();
                MainActivity.counterAdapter.notifyDataSetChanged();
                MainActivity.saveData(context);
                d.dismiss();
            }
        });

        // action listener for the delete button - when clicked, delete counter and update
        // local storage
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
