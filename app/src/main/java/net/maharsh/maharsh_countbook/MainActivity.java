package net.maharsh.maharsh_countbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "data.sav";
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

        loadData(this);
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
    public static void loadData(Context context){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException();
        }


    }
    public static void saveData(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counters, out);
            out.flush();

            fos.close();

            System.out.println("Save");
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}