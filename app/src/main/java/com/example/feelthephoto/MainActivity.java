package com.example.feelthephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageButton getButton;
    ImageView imageView;
    Intent intent;
    Bitmap bitmap;
    final static int picbycamera = 10;//static becoz before main it should be allocated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        getButton = findViewById(R.id.imageButton);
        InputStream inputStream=getResources().openRawResource(R.drawable.image_four);
        bitmap= BitmapFactory.decodeStream(inputStream);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          try {
                                              getApplicationContext().setWallpaper(bitmap);
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                          Toast.makeText(MainActivity.this, "Feel", Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );


        getButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                             startActivityForResult(intent, picbycamera);
                                             Toast.makeText(MainActivity.this, "Look", Toast.LENGTH_SHORT).show();
                                         }
                                     }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item2:
                Toast.makeText(MainActivity.this, "Hii", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
