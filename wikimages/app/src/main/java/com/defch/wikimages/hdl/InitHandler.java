package com.defch.wikimages.hdl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.defch.wikimages.ifaces.OnStartRequest;

/**
 * Created by DiegoFranco on 2/28/15.
 */
public class InitHandler extends Handler {

    private static final String TAG = "InitHanlder";

    private OnStartRequest onStartRequest;

    public InitHandler(OnStartRequest onStartRequest) {
        setOnStartRequest(onStartRequest);
    }

    private void setOnStartRequest(OnStartRequest onStartRequest) {
        this.onStartRequest = onStartRequest;
    }

    @Override
    public void handleMessage(Message msg) {
        if(msg.what == 403) {
            Log.w(TAG, "possible error");
        }
        if(onStartRequest != null) {
            onStartRequest.onStart();
        }
    }
}
