###################################Corrency Converter ###################################

package com.example.task01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText etx;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.conButton);
        etx = findViewById(R.id.editTextText);
        text = findViewById(R.id.outputText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = etx.getText().toString(); // Get text from input field

                // Basic validation (optional, but helpful)
                if (!value.isEmpty()) {
                    int mony = Integer.parseInt(value);
                    double out = mony * 300;
                    text.setVisibility(View.VISIBLE);
                    text.setTextColor(Color.BLACK);
                    text.setText(String.valueOf(out)); // Convert double to String properly
                } else {
                    text.setText("Please enter a number.");
                }
            }
        });
    }
}

###################################################################Task02#########################################################

package com.example.task02text_editer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =findViewById(R.id.button);
        editText =findViewById(R.id.editTextText1);
        textView =findViewById(R.id.outPutText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =editText.getText().toString();
                textView.setVisibility(View.VISIBLE);
                textView.setText("You Name is "+name);


            }
        });




    }
}

##############################################################CalculaTER############################

package com.example.task03calculater_add_two_number;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    EditText editText1;
    EditText editText2;
    TextView textView;


    public void clear(){
        textView.setText("");
        editText1.setText("");
        editText2.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        textView = findViewById(R.id.textViwe);
        editText1 = findViewById(R.id.editTextNumber);
        editText2 = findViewById(R.id.editTextNumber2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editText1.getText().toString();
                String input2 = editText2.getText().toString();

                // Check if inputs are not empty
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    int num1 = Integer.parseInt(input1);
                    int num2 = Integer.parseInt(input2);
                    int output = num1 + num2;

                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(output));
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Please enter both numbers.");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editText1.getText().toString();
                String input2 = editText2.getText().toString();

                // Check if inputs are not empty
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    int num1 = Integer.parseInt(input1);
                    int num2 = Integer.parseInt(input2);
                    int output = num1 - num2;

                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(output));
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Please enter both numbers.");
                }
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editText1.getText().toString();
                String input2 = editText2.getText().toString();

                // Check if inputs are not empty
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    int num1 = Integer.parseInt(input1);
                    int num2 = Integer.parseInt(input2);
                    int output = num1 * num2;

                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(output));
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Please enter both numbers.");
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editText1.getText().toString();
                String input2 = editText2.getText().toString();

                // Check if inputs are not empty
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    int num1 = Integer.parseInt(input1);
                    int num2 = Integer.parseInt(input2);
                    double output = num1 / num2;

                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(output));
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Please enter both numbers.");
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clear();
            }
        });



    }
}

####################################

package com.example.task04seedbar;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar.findViewById(R.id.seekBar);
        textView.findViewById(R.id.textViwew);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int value=i+1;

                textView.setText(String.valueOf(value));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}