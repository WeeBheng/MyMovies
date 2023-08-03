package sg.edu.rp.c346.id22016788.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, PG13Movies;
    ArrayList<Movies> al = new ArrayList<Movies>();
    ListView lv;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Movies>();

        al.add(new Movies(0, "Saving Private Ryan", "Drama", "1998", "NC16"));
        al.add(new Movies(1, "Orphan", "Horrow", "2009", "M18"));

        CustomAdapter adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        DBHelper dbh = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(dbh.getAllMovie());
        adapter.notifyDataSetChanged();
        Log.d("hope this works", al.toString());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        ModifyActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(MainActivity.this, AddActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );

        btnEdit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );

        PG13Movies.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             DBHelper dbh = new DBHelper(MainActivity.this);
                                             al.clear();
                                             al.addAll(dbh.getAllMovie("PG13"));
                                             adapter.notifyDataSetChanged();
                                             lv.setAdapter(adapter);
                                         }
                                     }
        );
    }
}
