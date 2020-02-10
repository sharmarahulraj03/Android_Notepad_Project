package ca.rahulsharma.notepad;
import android.view.View;
import android.widget.Button;
import android.graphics.Typeface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import ca.rahulsharma.notepad.R;


/**
 * @author Rahul Sharma
 * created on 02/10/2020
 */
public class NoteActivity extends AppCompatActivity {

    int counter1 = 0;
    int counter2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //Initialization
        final DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        final EditText name = findViewById(R.id.name);
        final EditText message = findViewById(R.id.message);
        ImageButton save = findViewById(R.id.save);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);


        //Get Intent after starting activity (add or update)
        final Intent intent = getIntent();
        final String option = intent.getStringExtra("option");

        //Set the text for the EditText if the option retrieved is "update"
        if (option.equals("update")) {
            //Set the values
            name.setText(intent.getStringExtra("name"));
            message.setText(intent.getStringExtra("message"));
        }


        //Set on click listener for the save button toolbar
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if either name or message has value
                if (name.getText().toString().isEmpty() && message.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Name and message are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if you create a new Note or Update an existing note and go back to main activity
                if (option.equals("add")) {
                    dbHandler.addNote(name.getText().toString(), message.getText().toString());
                    Toast.makeText(NoteActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));
                } else {
                    long receivedId = intent.getLongExtra("id", -1);
                    dbHandler.updateNote(receivedId, name.getText().toString(), message.getText().toString());
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));

                }
            }
        });

        //Set on click listener for the bold button toolbar
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter1%2==0)
                {
                    //convert the message to bold
                    message.setTypeface(button5.getTypeface(), Typeface.BOLD);
                    counter1 = counter1 + 1;
                }
                else
                {
                    //convert the message to bold
                    message.setTypeface(button5.getTypeface(), Typeface.NORMAL);
                    counter1 = counter1 + 1;
                }

            }
        });

        //Set on click listener for the bold button toolbar
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter2%2==0)
                {
                    //convert the message to Italic
                    message.setTypeface(button6.getTypeface(), Typeface.ITALIC);
                    counter2 = counter2 + 1;
                }
                else
                {
                    //convert the message to Italic
                    message.setTypeface(button6.getTypeface(), Typeface.NORMAL);
                    counter2 = counter2 + 1;
                }

            }
        });

    }
}
