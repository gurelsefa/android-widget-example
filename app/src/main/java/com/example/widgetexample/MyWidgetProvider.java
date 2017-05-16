package com.example.widgetexample;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String CLICK_ACTION = "myCustomAction";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();

        System.out.println("onReveice action: " + action);

        if (CLICK_ACTION.equals(action)) {
            System.out.println("clicked");
            Intent intent2 = new Intent(context, LoginActivity.class);
            context.startActivity(intent2);
        }

    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        System.out.println("onEnabled");

    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        System.out.println("on-update widget");

        for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Intent intent = new Intent(context, MyWidgetProvider.class);

            intent.setAction(CLICK_ACTION);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            remoteViews.setOnClickPendingIntent(R.id.WidgetLayout, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
