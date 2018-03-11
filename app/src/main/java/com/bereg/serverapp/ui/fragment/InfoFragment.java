package com.bereg.serverapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.serverapp.App;
import com.bereg.serverapp.R;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.presentation.presenter.InfoPresenter;
import com.bereg.serverapp.presentation.view.InfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Router;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#getInstance} factory method to
 * create an instance of this fragment.
 */

public class InfoFragment extends MvpAppCompatFragment implements InfoView {

    @BindView(R.id.tv_connection_info)
    TextView connectionInfo;

    @InjectPresenter
    InfoPresenter mInfoPresenter;

    @ProvidePresenter
    InfoPresenter provideInfoPresenter() {
        ConnectionInteractor mConnectionInteractor = App.getAppComponent().getConnectionInteractor();
        Router router = App.getInstance().getRouter();
        return new InfoPresenter(mConnectionInteractor, router);
    }

    public InfoFragment() {
    }

    public static InfoFragment getInstance(/*String ip*/) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        /*args.putString("IP", ip);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showConnectionInfo(String ip) {

        connectionInfo.setText(ip);
    }
}
