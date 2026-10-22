package com.example.myapplication_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button lastimg, nextimg, randomimg;
    private ImageView img;
    private TextView textView;

    // Массив ресурсов изображений (должны быть добавлены в res/drawable)
    private final int[] p = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    // Массив строк с названиями картинок для выполнения самостоятельной работы
    private final String[] names = {"Тадж-Махал", "Колизей", "Статуя свободы", "Пизанская башня", "Эйфелевая башня"};

    private int Index = 0;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = p.length - 1;
        init();
        updateUI(); // Первоначальное отображение данных
    }

    private void init() {
        lastimg = findViewById(R.id.lastimg);
        nextimg = findViewById(R.id.nextimg);
        randomimg = findViewById(R.id.randomimg);
        img = findViewById(R.id.image);
        textView = findViewById(R.id.textView);

        lastimg.setOnClickListener(this);
        nextimg.setOnClickListener(this);
        randomimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.lastimg) {
            if (Index == 0) {
                Index = count;
            } else {
                Index = Index - 1;
            }
        } else if (id == R.id.nextimg) {
            if (Index == count) {
                Index = 0;
            } else {
                Index = Index + 1;
            }
        } else if (id == R.id.randomimg) {
            // Реализация кнопки "Случайное изображение"
            Random random = new Random();
            int newIndex;
            // Цикл гарантирует, что новая случайная картинка не совпадет с текущей
            do {
                newIndex = random.nextInt(p.length);
            } while (newIndex == Index && p.length > 1);
            Index = newIndex;
        }

        updateUI();
    }

    // Вынос обновления интерфейса в отдельный метод во избежание дублирования кода
    private void updateUI() {
        img.setImageResource(p[Index]);
        // Вывод номера и названия изображения на новой строке (\n)
        String infoText = (Index + 1) + "/" + p.length + "\nНазвание: " + names[Index];
        textView.setText(infoText);
    }
}