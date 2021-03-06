package com.techyourchance.template.screens.common.dialogs;

import androidx.annotation.UiThread;
import androidx.fragment.app.DialogFragment;

import com.techyourchance.template.MyApplication;
import com.techyourchance.template.common.dependencyinjection.controller.ControllerComponent;
import com.techyourchance.template.common.dependencyinjection.controller.ControllerModule;
import com.techyourchance.template.common.dependencyinjection.controller.ViewMvcModule;

/**
 * Base class for all dialogs
 */
public abstract class BaseDialog extends DialogFragment {

    private boolean mIsControllerComponentUsed = false;

    @UiThread
    protected ControllerComponent getControllerComponent() {
        if (mIsControllerComponentUsed) {
            throw new IllegalStateException("must not use ControllerComponent more than once");
        }
        mIsControllerComponentUsed = true;
        return ((MyApplication)getActivity().getApplication())
                .getApplicationComponent()
                .newControllerComponent(new ControllerModule(getActivity()), new ViewMvcModule());
    }

    /**
     * Get this dialog's ID that was supplied with a call to
     * {@link DialogsManager#showDialogWithId(DialogFragment, String)}
     * @return dialog's ID, or null if none was set
     */
    protected String getDialogId() {
        if (getArguments() == null) {
            return null;
        } else {
            return getArguments().getString(DialogsManager.ARGUMENT_DIALOG_ID);
        }
    }

}
