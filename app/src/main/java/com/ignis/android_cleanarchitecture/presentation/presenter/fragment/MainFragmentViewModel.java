package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.domain.usecase.ProfileUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;
import com.ignis.android_cleanarchitecture.presentation.view.activity.AboutActivity;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Main Fragment ViewModel
 *
 * @author Kensuke
 */
public class MainFragmentViewModel {

    @Inject
    public ProfileUseCase profileUseCase;

    private Context context;
    private MainFragmentListener listener;
    private CompositeSubscription subscriptions;

    public MainFragmentViewModel(Context context, MainFragmentListener listener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.listener = listener;
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void onClickAbout(View view) {
        toAboutActivity();
    }

    /**
     * プロフィールリストを取得する
     *
     * @return
     */
    public void getProfileList() {
        subscriptions.add(profileUseCase.getProfileList() //Profile Modelリストで一括で取得
                .flatMap(Observable::from) //Profile Modelリストを分解
                .map(profileModel -> new ProfileViewModel(context, profileModel)) //Profile Modelを1つずつProfile ViewModelに変換
                .toList() //Profile ViewModelをリスト化
                .subscribeOn(Schedulers.newThread()) //実行はバックグラウンドスレッドで動作
                .observeOn(AndroidSchedulers.mainThread()) //結果はUIスレッドで取得
                .subscribe(result -> listener.onGetProfileList(result))); //Fragmentへ結果をコールバック
    }

    /**
     * About画面をブラウザで開く
     */
    private void toAboutActivity() {
        Intent intent = AboutActivity.newInstance(context);
        context.startActivity(intent);
    }

}
