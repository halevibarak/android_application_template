package com.techyourchance.template.screens.example.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.techyourchance.template.R;
import com.techyourchance.template.screens.common.ViewMvcFactory;
import com.techyourchance.template.screens.common.controllers.BaseActivity;
import com.techyourchance.template.screens.common.dialogs.DialogsFactory;
import com.techyourchance.template.screens.common.dialogs.DialogsManager;
import com.techyourchance.template.screens.example.mvcviews.ExampleViewMvc;

import javax.inject.Inject;

public class ExampleActivity extends BaseActivity implements ExampleViewMvc.Listener {

    @Inject DialogsManager mDialogsManager;
    @Inject DialogsFactory mDialogsFactory;
    @Inject ViewMvcFactory mViewMvcFactory;

    private ExampleViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);

        super.onCreate(savedInstanceState);

        mViewMvc = mViewMvcFactory.newExampleViewMvc(null);
        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    public void onShowDialogClicked() {
        DialogFragment dialog = mDialogsFactory.newInfoDialog(
                getString(R.string.dialog_title),
                getString(R.string.dialog_message),
                getString(R.string.dialog_button_caption));
        mDialogsManager.showDialog(dialog, null);
    }
}
