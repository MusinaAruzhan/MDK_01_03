package com.example.litleprince;

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
import androidx.core.app.NotificationCompat.Builder;

public class MainActivity extends AppCompatActivity {

    private Button btnMorning, btnDay, btnEvening, btnNight;

    private ImageView imgMorning, imgDay, imgEvening, imgNight;
    private ConstraintLayout layoutMorning;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMorning = (Button) findViewById(R.id.btnMorning);
        btnDay = (Button) findViewById(R.id.btnDay);
        btnEvening = (Button) findViewById(R.id.btnEvening);
        btnNight = (Button) findViewById(R.id.btnNight);

        // Инициализация изображений и макетов локаций
        imgMorning = (ImageView) findViewById(R.id.imageMorning);
        imgDay = (ImageView) findViewById(R.id.imageDay);
        imgEvening = (ImageView) findViewById(R.id.imageEvening);
        imgNight = (ImageView) findViewById(R.id.imageNight);

        layoutMorning = (ConstraintLayout) findViewById(R.id.layoutMorning);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


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
                    CHANNEL_ID,
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(notificationChannel);
        }
    }
    public void btnMorningClick(View view) {
        layoutMorning.setVisibility(View.VISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        sendNotification("Утро", "Привести в порядок свою планету", R.drawable.cleaning);
    }
    public void btnDayClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.VISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        sendNotification("День", "Полить розу", R.drawable.rose);
    }
    public void btnEveningClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.VISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        sendNotification("Вечер", "Закрыть розу ширмой", R.drawable.home);
    }
    public void btnNightClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.VISIBLE);

        sendNotification("Ночь", "Полюбоваться звездами", R.drawable.sunset);
    }


    public void onPrinceClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Маленький принц", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onPlanetClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Астероид Б-612, планета на которой живет Маленький принц", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onVolcanoClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Потухший вулкан. О нем нужно заботиться", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }
    public void onRoseClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Её нужно поливать, а на ночь укрывать ширмой и колпаком", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }

    public void onBreakfastClick(View view) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Действующий вулкан, на нем удобно разогревать завтрак", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.show();
    }
}