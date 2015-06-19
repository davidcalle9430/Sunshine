package com.example.david.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private final static String  FORECAST_SHARE_HASHTAG = "#SunshineApp";
    private  String mForecastString;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);
        MenuItem item = menu.findItem(R.id.action_share);

        ShareActionProvider sap =(ShareActionProvider) MenuItemCompat.getActionProvider(item);

        if(sap!=null){
            sap.setShareIntent(createShareForecastIntent());
        }else{
            Log.d("Error", "Share action provider es nulo");
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        mForecastString = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView textView = (TextView) rootView.findViewById(R.id.forecast_detail);
        textView.setText(mForecastString);
        return rootView;
    }

    private Intent createShareForecastIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastString + " "+ FORECAST_SHARE_HASHTAG);
        return shareIntent;

    }
}
