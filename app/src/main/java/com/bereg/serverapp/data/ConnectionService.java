package com.bereg.serverapp.data;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.bereg.serverapp.App;
import com.bereg.serverapp.domain.ConnectionInteractor;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ConnectionService extends IntentService {

    private static final String TAG = ConnectionService.class.getSimpleName();

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.bereg.serverapp.data.action.FOO";
    private static final String ACTION_BAZ = "com.bereg.serverapp.data.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.bereg.serverapp.data.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.bereg.serverapp.data.extra.PARAM2";

    private ConnectionInteractor mConnectionInteractor;

    public ConnectionService() {
        super("ConnectionService");
        mConnectionInteractor = App.getAppComponent().getConnectionInteractor();
        Log.e(TAG, "ConnectionService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context/*, String param1, String param2*/) {
        Log.e(TAG, "startActionFoo in");
        Intent intent = new Intent(context, ConnectionService.class);
        intent.setAction(ACTION_FOO);
        //intent.putExtra(EXTRA_PARAM1, param1);
        //intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
        Log.e(TAG, "startActionFoo out");
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ConnectionService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent");
        if (intent != null) {
            Log.e(TAG, "intent != null");
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                Log.e(TAG, "ACTION_FOO");
                //final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                //final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(/*param1, param2*/);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(/*String param1, String param2*/) {
        // TODO: Handle action Foo
        //throw new UnsupportedOperationException("Not yet implemented");
        try {
            Log.e(TAG, "handleActionFoo in");
            mConnectionInteractor.startServer();
            Log.e(TAG, "handleActionFoo out");
        }catch (Exception e) {
            Log.e(TAG, e.toString());

        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
