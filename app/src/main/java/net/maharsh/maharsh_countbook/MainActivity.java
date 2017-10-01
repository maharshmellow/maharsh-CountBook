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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Counter> counters = new ArrayList<>();
    public static ListView countersListView;
    public static CounterAdapter counterAdapter;
    public static TextView totalCountersField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.add_counter_button);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        countersListView = (ListView) findViewById(R.id.counters);
        // the textfield at the bottom of the page that shows the total count of counters
        totalCountersField = (TextView) findViewById(R.id.total_counters_field);


//        ArrayList<Counter> counterList = new ArrayList<>();
        counters.add(new Counter("Counter 1", 0, "comment"));
        counters.add(new Counter("Counter 2", 0, "comment"));


        // initialize the counters
        // TODO load the counters from the local storage here

        counterAdapter = new CounterAdapter(this, counters);
        countersListView.setAdapter(counterAdapter);

        final Dialog d = new Dialog(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.newCounterDialog();
            }
        });

    }
}