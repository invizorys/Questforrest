package com.mediahack.presentation.view;

/**
 * Created by Roma on 09.10.2016.
 */

public interface QuestView {
    void onSuccessfulVkShare();

    void onFailureVkShare();

    void showMessageDialog(String text);
}
