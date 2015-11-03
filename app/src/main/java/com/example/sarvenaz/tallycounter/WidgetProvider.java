package com.example.sarvenaz.tallycounter;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;



public class WidgetProvider extends AppWidgetProvider {

    private CounterManager counterManager;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       // counterManager = new CounterManager(context);

        final int N = appWidgetIds.length;
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            counterManager = new CounterManager(context);
            int stingCounter =counterManager.getCounter();
            String counter = Integer.toString(stingCounter);
            views.setTextViewText(R.id.tv_Counterwidget,counter);


            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}





