package net.maharsh.maharsh_countbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final ArrayList<Counter> counters = new ArrayList<>();
    private ListView countersListView;
    private CounterAdapter counterAdapter;

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

//        final ArrayAdapter<Counter> arrayAdapter = new ArrayAdapter<Counter>
//                (this, R.layout.counter_item, R.id.counter_text, counters);

        counterAdapter = new CounterAdapter(this, counters);
        countersListView.setAdapter(counterAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Testing", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                System.out.println("FAB Clicked");
                // add new counter
                Counter c = new Counter("Counter 1", 0, "comment");
                counters.add(c);
                System.out.println("First: " + counters.size());

                counterAdapter.notifyDataSetChanged();

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
}