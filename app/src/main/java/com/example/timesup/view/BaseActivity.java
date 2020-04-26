package com.example.timesup.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.timesup.enums.MessageCode;
import com.example.timesup.util.MessageSourceAccessor;
import com.example.timesup.viewmodel.BaseViewModel;

public class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    protected T viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViewModel();
        prepareView();
        addListenerOnButton();
    }

    private void initViewModel() {
        viewModel =  (T) ViewModelProviders.of(this).get(getViewModelClass());
    }

    protected Class getViewModelClass() {
        throw new UnsupportedOperationException("Define ViewModel class");
    }

    protected void addListenerOnButton() {
    }

    protected void prepareView() {
    }

    protected int getLayoutId() {
        throw new UnsupportedOperationException("Layout ID not defined");
    }

    protected <T extends BaseActivity> void switchActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.overridePendingTransition(0, 0);
        viewModel.changeGameState();
        startActivity(intent);
    }

    protected String getLabelText(MessageCode messageCode) {
        return MessageSourceAccessor.getMessage(messageCode);
    }

    private String getLabelText(MessageCode messageCode, Object... params) {
        return MessageSourceAccessor.getMessage(messageCode, params);
    }

    protected void setLabelText(int textViewId, MessageCode messageCode, Object... params) {
        ((TextView) findViewById(textViewId)).setText(getLabelText(messageCode, params));
    }

    protected void setLabelText(int textViewId, String text) {
        ((TextView) findViewById(textViewId)).setText(text);
    }
}
