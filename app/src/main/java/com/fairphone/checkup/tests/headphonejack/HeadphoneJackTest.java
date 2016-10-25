package com.fairphone.checkup.tests.headphonejack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fairphone.checkup.R;
import com.fairphone.checkup.tests.Test;

/**
 * Created by maarten on 19/09/16.
 */
public class HeadphoneJackTest extends Test {

    private static final String TAG = HeadphoneJackTest.class.getSimpleName();

    View mTestView;
    private BroadcastReceiver receiver;

    public HeadphoneJackTest(Context context) {
        super(context);
    }

    @Override
    protected int getTestTitleID() {
        return R.string.headphone_jack_test_title;
    }

    @Override
    protected int getTestDescriptionID() {
        return R.string.headphone_jack_test_description;
    }

    @Override
    protected void runTest() {
        replaceView();
        setupHeadphoneJackMonitor();
    }

    @Override
    protected void onCleanUp() {
        getContext().unregisterReceiver(receiver);
        receiver = null;
        super.onCleanUp();
    }

    private void replaceView() {
        mTestView = LayoutInflater.from(getContext()).inflate(R.layout.view_headphone_jack_test, null);
        setTestView(mTestView);
    }

    private void setupHeadphoneJackMonitor() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(Intent.ACTION_HEADSET_PLUG)) {
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0:
                            ((TextView) findViewById(R.id.headphone_jack_state_text)).setText(getResources().getString(R.string.headphone_unplugged));
                            break;
                        case 1:
                            ((TextView) findViewById(R.id.headphone_jack_state_text)).setText(getResources().getString(R.string.headphone_plugged));
                            break;
                    }
                }
            }
        };
        getContext().registerReceiver(receiver, filter);
    }
}