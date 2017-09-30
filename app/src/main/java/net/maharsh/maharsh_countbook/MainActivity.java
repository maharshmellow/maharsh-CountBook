package net.maharsh.maharsh_countbook;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Counter> counters = new ArrayList<>();
    public static ListView countersListView;
    public static CounterAdapter counterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        countersListView = (ListView) findViewById(R.id.counters);
//        ArrayList<Counter> counterList = new ArrayList<>();
        counters.add(new Counter("Counter 1", 0, "comment"));
        counters.add(new Counter("Counter 2", 0, "comment"));

        final ListView countersListView = (ListView) findViewById(R.id.counters);

        // initialize the counters
        // TODO load the counters from the local storage here

        counterAdapter = new CounterAdapter(this, counters);
        countersListView.setAdapter(counterAdapter);

        final Dialog d = new Dialog(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Testing", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                showNewCounterDialog();

                d.newCounterDialog();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void showEditCounterDialog(final Counter counter){
        // set up the alert dialog
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.edit_counter_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText counter_name = (EditText) promptView.findViewById(R.id.counter_name_input);
        final EditText initial_counter_value = (EditText) promptView.findViewById(R.id.initial_counter_value_input);
        final EditText counter_value = (EditText) promptView.findViewById(R.id.counter_value_input);
        final EditText counter_comment = (EditText) promptView.findViewById(R.id.counter_comment_input);
        final Button delete_button = (Button) promptView.findViewById(R.id.delete_button);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                counters.remove(counter);
                counterAdapter.notifyDataSetChanged();
            }
        });


        // prefill the values into the edit dialog
        counter_name.setText(counter.getName());
        initial_counter_value.setText(counter.getInitialValue());
        counter_value.setText(counter.getCurrentValue());
        counter_comment.setText(counter.getComment());

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("OK" + counter_name.getText() + counter_value.getText() + counter_comment.getText());

                counter.editCounter(counter_name.getText().toString(),
                                    Integer.parseInt(initial_counter_value.getText().toString()),
                                    Integer.parseInt(counter_value.getText().toString()),
                                    counter_comment.getText().toString());

                counterAdapter.notifyDataSetChanged();
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
}