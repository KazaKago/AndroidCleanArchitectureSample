package com.kazakago.cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.kazakago.cleanarchitecture.CleanApplication;
import com.kazakago.cleanarchitecture.R;
import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCase;
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener;

import javax.inject.Inject;

/**
 * About Fragment ViewModel
 *
 * @author Kensuke
 */
public class AboutFragmentViewModel {

    public ObservableField<String> verText;
    public ObservableField<String> developByText;
    public ObservableField<String> copyrightText;

    private Context context;
    private AboutFragmentViewModelListener listener;

    @Inject
    public AboutUseCase aboutUseCase;

    public AboutFragmentViewModel(Context context, AboutFragmentViewModelListener listener) {
        CleanApplication.applicationComponent.inject(this);
        this.context = context;
        this.listener = listener;
        this.verText = new ObservableField<>(context.getString(R.string.about_ver, aboutUseCase.getCurrentVersion()));
        this.developByText = new ObservableField<>(context.getString(R.string.about_develop_by, context.getString(R.string.developer_name)));
        this.copyrightText = new ObservableField<>(context.getString(R.string.about_copyright, aboutUseCase.getCurrentYear(), context.getString(R.string.developer_name)));
    }

    public void onClickPlayStore(View view) {
        toPlayStore();
    }

    public void onClickMail(View view) {
        toMailApp();
    }

    public void onClickWebSite(View view) {
        toWebSite();
    }

    private void toPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.getPlayStoreUrl()));
        context.startActivity(intent);
    }

    private void toMailApp() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + aboutUseCase.getMailUrl()));
        context.startActivity(intent);
    }

    private void toWebSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.getWebSiteUrl()));
        context.startActivity(intent);
    }

}
