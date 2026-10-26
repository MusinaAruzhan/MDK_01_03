package com.example.animals_4;

import android.os.Bundle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnSavanna, btnJungle, btnOcean;
    private ImageView imgSavanna, imgJungle, imgOcean;
    private ConstraintLayout layoutSavanna;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "KIDS_ZOO_CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSavanna = (Button) findViewById(R.id.btnSavanna);
        btnJungle = (Button) findViewById(R.id.btnJungle);
        btnOcean = (Button) findViewById(R.id.btnOcean);

        // Инициализация графических слоев
        imgSavanna = (ImageView) findViewById(R.id.imageSavanna);
        imgJungle = (ImageView) findViewById(R.id.imageJungle);
        imgOcean = (ImageView) findViewById(R.id.imageOcean);

        layoutSavanna = (ConstraintLayout) findViewById(R.id.layoutSavanna);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        imgSavanna.setImageResource(R.drawable.savanna);
        imgJungle.setImageResource(R.drawable.jungle);
        imgOcean.setImageResource(R.drawable.ocean);

    }

    private void sendNotification(String title, String text, int iconResId) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        createChannelIfNeeded(notificationManager);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(iconResId)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }
    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Zoo Guide Tasks",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(notificationChannel);
        }
    }
    public void btnSavannaClick(View view) {
        layoutSavanna.setVisibility(View.VISIBLE);
        imgJungle.setVisibility(View.INVISIBLE);
        imgOcean.setVisibility(View.INVISIBLE);

        sendNotification("Добро пожаловать в Саванну!", "Найди царя зверей и погладь самое высокое животное.", R.drawable.cleaning);
    }

    public void btnJungleClick(View view) {
        layoutSavanna.setVisibility(View.INVISIBLE);
        imgJungle.setVisibility(View.VISIBLE);
        imgOcean.setVisibility(View.INVISIBLE);

        sendNotification("Исследуем Джунгли!", "Здесь очень влажно. Поищи попугаев на ветках деревьев.", R.drawable.rose);
    }
    public void btnOceanClick(View view) {
        layoutSavanna.setVisibility(View.INVISIBLE);
        imgJungle.setVisibility(View.INVISIBLE);
        imgOcean.setVisibility(View.VISIBLE);

        sendNotification("Погружение в Океан!", "Осторожно! Под водой плавают зубастые акулы и огромные киты.", R.drawable.home);
    }

    // Обучающие факты о животных Саванны (Всплывающие сообщения Toast)
    public void onLionClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Лев — царь зверей! Его громкий рык слышно на целых 8 километров.", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onGiraffeClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Жираф — самое высокое животное на Земле! У него очень длинный синий язык.", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }
    public void onElephantClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Слон — самый большой сухопутный житель. Своим хоботом он может поднять целое бревно!", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onMonkeyClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Обезьянка — очень ловкий зверек. Она обожает прыгать по лианам и есть бананы.", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onBaobabClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Баобаб — это огромное дерево саванны. В своем толстом стволе оно хранит воду.", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

}