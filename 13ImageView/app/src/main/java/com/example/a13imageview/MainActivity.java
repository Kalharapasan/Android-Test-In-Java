package com.example.a13imageview;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private int current_Image_Index;

    int[] images = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClick();
    }
    public void buttonClick() {
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_Image_Index++;
                current_Image_Index = current_Image_Index % images.length;
                imageView.setImageResource(images[current_Image_Index]);
            }
        });
    }
}
