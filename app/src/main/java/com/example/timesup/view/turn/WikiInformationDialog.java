package com.example.timesup.view.turn;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timesup.R;
import com.example.timesup.util.ComponentDimensionUtil;
import com.example.timesup.util.WikiRequestHandler;

public class WikiInformationDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity activity;
    private String title;
    private WikiRequestHandler wikiRequestHandler;
    private ComponentDimensionUtil componentDimensionUtil;

    public WikiInformationDialog(Activity activity, String title) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.componentDimensionUtil = new ComponentDimensionUtil(activity.getWindowManager());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.wiki_dialog);
        prepareView();
        prepareButtonOkLayout();
        wikiRequestHandler = new WikiRequestHandler(activity.getApplicationContext());
        getWikiInfo(title);
    }

    private void prepareView() {
        prepareDialogLayout();
        prepareTitleLayout();
        prepareTextLayout();
        prepareButtonOkLayout();
        findViewById(R.id.wiki_dialog_ok).setOnClickListener(this);
        ((TextView)findViewById(R.id.wiki_dialog_title)).setText(title);
    }

    private void prepareDialogLayout() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) findViewById(R.id.wikidialog).getLayoutParams();
        params.width = componentDimensionUtil.getScreenWidth() * 3/4;
        params.height = componentDimensionUtil.getScreenHeight() * 3/4;
        findViewById(R.id.wikidialog).setLayoutParams(params);
        findViewById(R.id.wikidialog).requestLayout();
    }

    private void prepareTitleLayout() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.wiki_dialog_title).getLayoutParams();
        params.height = componentDimensionUtil.getScreenHeight() * 1/20;
        params.topMargin = componentDimensionUtil.getFrameHeight() * 1/20;
        scaleAndCenterHorizontally(params, 0.9);
        findViewById(R.id.wiki_dialog_title).setLayoutParams(params);
        findViewById(R.id.wiki_dialog_title).requestLayout();
    }
    private void prepareTextLayout() {
        RelativeLayout.LayoutParams scrollViewParams = (RelativeLayout.LayoutParams) findViewById(R.id.wiki_dialog_scrollview).getLayoutParams();
        scrollViewParams.height = componentDimensionUtil.getFrameHeight() * 13/20;
        scrollViewParams.topMargin = componentDimensionUtil.getFrameHeight() * 1/8;
        scaleAndCenterHorizontally(scrollViewParams, 0.9);
        findViewById(R.id.wiki_dialog_scrollview).setLayoutParams(scrollViewParams);
        findViewById(R.id.wiki_dialog_scrollview).requestLayout();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) findViewById(R.id.wiki_dialog_text).getLayoutParams();
        scaleAndCenterHorizontally(params, 0.6);
        TextView wikiDialogTextview = findViewById(R.id.wiki_dialog_text);
        wikiDialogTextview.setAutoSizeTextTypeUniformWithConfiguration(5, 16, 2, TypedValue.COMPLEX_UNIT_SP);
        wikiDialogTextview.setLayoutParams(params);
        wikiDialogTextview.requestLayout();
    }

    private void prepareButtonOkLayout() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.wiki_dialog_ok).getLayoutParams();
        scaleAndCenterHorizontally(params, 0.6);
        params.topMargin = componentDimensionUtil.getFrameHeight() * 17/20;
        findViewById(R.id.wiki_dialog_ok).setLayoutParams(params);
        findViewById(R.id.wiki_dialog_ok).requestLayout();
    }

    private void scaleAndCenterHorizontally(RelativeLayout.LayoutParams params, double factor) {
        params.width = (int) ((double)  componentDimensionUtil.getScreenWidth() * factor);
        params.leftMargin = (int) ((double) componentDimensionUtil.getScreenWidth() * ((1d-factor)/2d));
        params.rightMargin = (int) ((double)  componentDimensionUtil.getScreenWidth() * ((1d-factor)/2d));
    }

    private void scaleAndCenterHorizontally(FrameLayout.LayoutParams params, double factor) {
        params.width = (int) ((double)  componentDimensionUtil.getScreenWidth() * factor);
        params.leftMargin = (int) ((double) componentDimensionUtil.getScreenWidth() * ((1d-factor)/2d));
        params.rightMargin = (int) ((double)  componentDimensionUtil.getScreenWidth() * ((1d-factor)/2d));
    }

    private void getWikiInfo(String title) {
        wikiRequestHandler.getWikiInformation(title, findViewById(R.id.wiki_dialog_text));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wiki_dialog_ok:
                dismiss();
                break;
        }
        dismiss();
    }

}
