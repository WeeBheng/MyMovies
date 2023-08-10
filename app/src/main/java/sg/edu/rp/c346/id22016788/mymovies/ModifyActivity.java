package sg.edu.rp.c346.id22016788.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    TextView tvContent1, tvContent2, tvContent3, tvContent4, tvContent5;
    EditText etContent1, etContent2, etContent3, etContent4;
    Button btnUpdate, btnDelete, btnCancel;
    Movies movie;
    RadioButton radioButton1, radioButton2, radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btnDelete = findViewById(R.id.btnShowList);
        btnUpdate = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        tvContent1 = findViewById(R.id.tvContent1);
        tvContent2 = findViewById(R.id.tvContent2);
        tvContent3 = findViewById(R.id.tvContent3);
        tvContent4 = findViewById(R.id.tvContent4);
        tvContent5 = findViewById(R.id.tvContent5);
        etContent1 = findViewById(R.id.etContent1);
        etContent2 = findViewById(R.id.etContent2);
        etContent3 = findViewById(R.id.etContent3);
        etContent4 = findViewById(R.id.etContent4);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        Intent i = getIntent();
        movie = (Movies) i.getSerializableExtra("data");


        tvContent1.setText("ID: " + movie.getId());
        etContent2.setText(movie.getMovieTitle());
        etContent3.setText(movie.getGenre());
        etContent4.setText(movie.getMovieYear());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                if (radioButton1.isChecked()){
                    movie.setMovieContent(Integer.parseInt(etContent1.getText().toString()), etContent2.getText().toString(), etContent3.getText().toString(), etContent4.getText().toString(), "M18");
                } else if (radioButton2.isChecked()){
                    movie.setMovieContent(Integer.parseInt(etContent1.getText().toString()), etContent2.getText().toString(), etContent3.getText().toString(), etContent4.getText().toString(), "NC16");
                } else {
                    movie.setMovieContent(Integer.parseInt(etContent1.getText().toString()), etContent2.getText().toString(), etContent3.getText().toString(), etContent4.getText().toString(), "PG13");
                }
                dbh.updateMovie(movie);
                dbh.close();
                Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + movie.getMovieTitle() + "?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyActivity.this);
                        dbh.deleteMovie(movie.getId());
                    }
                });

                myBuilder.setNeutralButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvContent1.setText("ID: " + movie.getId());
                        etContent2.setText(movie.getMovieTitle());
                        etContent3.setText(movie.getGenre());
                        etContent4.setText(movie.getMovieYear());
                    }
                });

                myBuilder.setNegativeButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}