package com.mediahack.util;

import android.app.Activity;

import com.mediahack.model.User;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Roma on 08.10.2016.
 */

public class VkSocialHelper {
    private Activity activity;
    private final String FIELD_PHOTO_NAME = "photo_200";
    private static final String[] scopeVk = new String[]{};
    private static final String ID_KEY = "id";
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String LAST_NAME_KEY = "last_name";
//    private static final String SEX_KEY = "sex";
    private static final String BDAY_KEY = "bdate";

    public VkSocialHelper(Activity activity) {
        this.activity = activity;
    }

    public void login() {
        VKSdk.login(activity, scopeVk);
    }

    public void logout() {
        VKSdk.logout();
    }

    public boolean isConnected() {
        return VKSdk.isLoggedIn();
    }

    public void getUserData(final SocialNetworkCallback socialNetworkCallback) {

        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,
                "id,first_name,last_name,sex,bdate," + FIELD_PHOTO_NAME
        ));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    JSONObject jsonResponse = response.json.getJSONArray("response").getJSONObject(0);
                    User socialUser = getUserData(jsonResponse);
                    socialNetworkCallback.onUserReceive(socialUser);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
    }

    private User getUserData(JSONObject jsonResponse) throws JSONException {
        User socialUser = new User();
        if (jsonResponse.has(ID_KEY)) {
            socialUser.setSocialId(jsonResponse.getString(ID_KEY));
        }
        if (jsonResponse.has(FIRST_NAME_KEY)) {
            socialUser.setName(jsonResponse.getString(FIRST_NAME_KEY));
        }
        if (jsonResponse.has(LAST_NAME_KEY)) {
            socialUser.setSurname(jsonResponse.getString(LAST_NAME_KEY));
        }
        if (jsonResponse.has(FIELD_PHOTO_NAME)) {
            socialUser.setAvatarUrl(jsonResponse.getString(FIELD_PHOTO_NAME));
        }
//        if (jsonResponse.has(SEX_KEY)) {
//            socialUser.setSex(Sex.parseSex(jsonResponse.getString(SEX_KEY)).toString());
//        }
        if (jsonResponse.has(BDAY_KEY)) {
            socialUser.setBirthDate(jsonResponse.getString(BDAY_KEY));
        }
        return socialUser;
    }
}

