package com.bereg.serverapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bereg.serverapp.App;
import com.bereg.serverapp.R;
import com.bereg.serverapp.Utils.Screens;
import com.bereg.serverapp.data.ConnectionService;
import com.bereg.serverapp.presentation.presenter.MainPresenter;
import com.bereg.serverapp.ui.fragment.InfoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    MainPresenter mMainPresenter;/* = new MainPresenter(, App.getInstance().getRouter());*/

    @BindView(R.id.tv_server_state)
    TextView textView;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragment_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.INFO_SCREEN:
                    Log.e(TAG, "INFO_SCREEN");
                    return InfoFragment.getInstance();
            }
            Log.e(TAG, "NULL");
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = App.getAppComponent().getMainPresenter();
        //mMainPresenter.onAttachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().getNavigatorHolder().setNavigator(navigator);
        Log.e(TAG, "onResume");
        Toast.makeText(MainActivity.this, "ActivityOnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        App.getInstance().getNavigatorHolder().removeNavigator();
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @OnClick(R.id.btn_start)
    public void onStartButtonClick() {
        ConnectionService.startActionFoo(this);
        mMainPresenter.onStartClicked();
    }

    /*public void setText(String ip) {
        textView.setText(ip);
    }*/
}
