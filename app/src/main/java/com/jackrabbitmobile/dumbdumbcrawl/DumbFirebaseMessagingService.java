package com.jackrabbitmobile.dumbdumbcrawl;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by SamMyxer on 6/7/16.
 */

public class DumbFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "onMessageReceived";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}
