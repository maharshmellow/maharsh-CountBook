package net.maharsh.maharsh_countbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        countersListView = (ListView) findViewById(R.id.counters);
        totalCountersField = (TextView) findViewById(R.id.total_counters_field);

        // TODO load the counters from the local storage here

        totalCountersField.setText(MainActivity.counters.size() + " counters");

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