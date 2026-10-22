package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonClean;
    private Button buttonPower, buttonRemainder;
    private TextView operation, result;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonClean = (Button) findViewById(R.id.buttonClean);

        buttonPower = (Button) findViewById(R.id.buttonPower);
        buttonRemainder = (Button) findViewById(R.id.buttonRemainder);

        operation = (TextView) findViewById(R.id.operation);
        result = (TextView) findViewById(R.id.result);

        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonClean.setOnClickListener(this);
        buttonPower.setOnClickListener(this);
        buttonRemainder.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        float num1 = 0;
        float num2 = 0;
        float res = 0;

        if (v.getId() == R.id.buttonClean){
            number1.setText("");
            number2.setText("");
            operation.setText("");
            result.setText("");
            return;
        }

        if(number1.getText().toString().isEmpty() || number2.getText().toString().isEmpty()){
            Toast.makeText(this, "Пожалуйста, введите оба числа", Toast.LENGTH_SHORT).show();
            return;
        }

        num1 = Float.parseFloat(number1.getText().toString());
        num2 = Float.parseFloat(number2.getText().toString());

        int id = v.getId();

        if (id == R.id.buttonAdd) {
            operation.setText("+");
            res = num1 + num2;
        } else if (id == R.id.buttonSubtract) {
            operation.setText("-");
            res = num1 - num2;
        } else if (id == R.id.buttonDivide) {
            if (num2 == 0) {
                Toast.makeText(this, "Деление на ноль невозможно!", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                operation.setText("/");
                return;
            }
            operation.setText("/");
            res = num1 / num2;
        } else if (id == R.id.buttonMultiply) {
            operation.setText("*");
            res = num1 * num2;
        }
        // Новая логика: Возведение в степень
        else if (id == R.id.buttonPower) {
            operation.setText("^");
            res = (float) Math.pow(num1, num2);
        }
        // Новая логика: Остаток от деления
        else if (id == R.id.buttonRemainder) {
            if (num2 == 0) {
                Toast.makeText(this, "Деление на ноль невозможно!", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                operation.setText("%");
                return;
            }
            operation.setText("%");
            res = num1 % num2;
        }
        // Вывод красивого отформатированного результата
        // Если результат целое число — отсекаем .0 для красоты
        if (res == (long) res) {
            result.setText(String.format("%d", (long) res));
        } else {
            result.setText(String.valueOf(res));
        }

    }
}