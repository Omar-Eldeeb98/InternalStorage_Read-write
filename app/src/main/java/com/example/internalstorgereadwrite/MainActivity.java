package com.example.internalstorgereadwrite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText enteredEditText;
    private Button readBtn ;
    private Button writeBtn ;
    private TextView showTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTextView = (TextView) findViewById(R.id.showtextview);
        enteredEditText=(EditText) findViewById(R.id.editText);
        writeBtn = (Button) findViewById(R.id.button3);
        readBtn =(Button) findViewById(R.id.button);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logic is here :load data
                try {

                    showTextView.setText(readFromFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logic is here :save data
                if(!enteredEditText.getText().toString().equals(""))
                {
                    String message = enteredEditText.getText().toString();
                    writeToFile(message);
                    enteredEditText.setText("");


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Enter a Fucking Thing !!!" +"\n" + "Mother Fucker ...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void writeToFile(String message)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("ToDoList.txt" , Context.MODE_PRIVATE));
          outputStreamWriter.write(message);
          outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readFromFile() throws IOException {
        String result = "" ;
        InputStream inputStream = openFileInput("ToDoList.txt");
        if(inputStream!=null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempString = "" ;
            StringBuilder stringBuilder = new StringBuilder();

            while((tempString = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(tempString);
            }
            inputStream.close();
            result = stringBuilder.toString();

        }
        return result;

    }

}
