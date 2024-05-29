package com.example.educationapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ContributionView extends View {

    private Paint paint;
    private Paint textPaint;
    private Paint gridPaint;
    private int[][] contributions;
    private String[] months = {"Янв", "Феб", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Нояб", "Дек"};
    private int cellSize = 50; // Размер квадратиков
    private int textSize = 40;
    private int monthLabelOffset = 100; // Отступ для названий месяцев

    public ContributionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);

        gridPaint = new Paint();
        gridPaint.setColor(Color.LTGRAY); // Сделать сетку менее выделяющейся
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(1); // Уменьшить толщину линии сетки

        contributions = new int[52][7]; // 52 недели по 7 дней

        // Пример заполнения данных активности (замените на реальные данные)
        contributions[0][0] = 1;
        contributions[1][3] = 2;
        contributions[2][5] = 3;
        // Добавьте остальные данные...
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("ContributionView", "onDraw called");

        // Рисуем названия месяцев
        for (int i = 0; i < months.length; i++) {
            canvas.drawText(months[i], monthLabelOffset + (i * 4 * cellSize) + cellSize, textSize, textPaint); // Примерное размещение
        }

        // Рисуем сетку и квадратики активности
        for (int week = 0; week < 52; week++) {
            for (int day = 0; day < 7; day++) {
                int contribution = contributions[week][day];
                paint.setColor(getColorForContribution(contribution));
                float left = (week * cellSize) + cellSize; // Отступ для сетки
                float top = (day * cellSize) + textSize + cellSize; // Отступ для текста
                float right = (week + 1) * cellSize + cellSize;
                float bottom = (day + 1) * cellSize + textSize + cellSize;

                canvas.drawRect(left, top, right, bottom, paint);
                canvas.drawRect(left, top, right, bottom, gridPaint); // Рисуем сетку поверх
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = (52 * cellSize) + cellSize + monthLabelOffset; // 52 недели + отступы + отступ для названий месяцев
        int desiredHeight = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, MeasureSpec.getSize(widthMeasureSpec));
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, MeasureSpec.getSize(heightMeasureSpec));
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    private int getColorForContribution(int contribution) {
        switch (contribution) {
            case 1: return Color.parseColor("#b3d9ff");
            case 2: return Color.parseColor("#66b3ff");
            case 3: return Color.parseColor("#3399ff");
            case 4: return Color.parseColor("#0080ff");
            default: return Color.parseColor("#ebedf0");
        }
    }
}
