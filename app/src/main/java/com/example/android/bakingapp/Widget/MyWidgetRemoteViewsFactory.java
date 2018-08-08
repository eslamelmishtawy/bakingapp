package com.example.android.bakingapp.Widget;

import android.arch.lifecycle.LiveData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.android.bakingapp.Database.AppDatabase;
import com.example.android.bakingapp.Database.Entry;
import com.example.android.bakingapp.Provider.RecipeContract;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.UI.MainActivity;
import com.example.android.bakingapp.UI.MasterListAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private Cursor mCursor;
    private String recipeName;

    public MyWidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        recipeName = MasterListAdapter.getRecipePositionName();

        if (mCursor != null) {
            mCursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();
        Uri uri = RecipeContract.RecipeEntry.CONTENT_URI;
        mCursor = mContext.getContentResolver().query(uri,
                null,
                "names=?",
                new String[]{recipeName},
                null, null);

        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public int getCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                mCursor == null || !mCursor.moveToPosition(position)) {
            return null;
        }
        String arr[] = mCursor.getString(2).split(",");
        String ing = "";
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.app_widget_list);
        for(int i = 0; i < arr.length; i++) {
            ing += arr[i] + "\n";
        }
        rv.setTextViewText(R.id.widgetItemTaskNameLabel, ing);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return mCursor.moveToPosition(position) ? mCursor.getLong(0) : position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
