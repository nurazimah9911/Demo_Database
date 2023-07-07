package sg.edu.rp.c346.id22037444.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView tv;
    EditText editTaskName, editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        tv = findViewById(R.id.tv);
        editTaskName = findViewById(R.id.editTaskName);
        editDate = findViewById(R.id.editDate);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2021");
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> alTasks = db.getTasks();
                db.close();

                String txt = "";
                String name = "";
                String date = "";
                for(int i = 0; i < data.size(); i++){
                    Log.d("Database Content", i + ". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n" + name + "\n" + date;
                }
                tvResults.setText(txt);

                ArrayAdapter<Task> aaTasks = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, alTasks);
                tv.setAdapter(aaTasks);
            }
        });
    }
}