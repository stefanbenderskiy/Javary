package com.reflection.javary.fragments.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.reflection.javary.activities.info.InfoActivity;
import com.reflection.javary.activities.notifications.NotificationsActivity;
import com.reflection.javary.R;
import com.reflection.javary.activities.settings.SettingsActivity;
import com.reflection.javary.ListMenuAdapter;
import com.reflection.javary.ListMenuItem;

import java.util.List;

public class ProfileFragment extends Fragment {


    private List<ListMenuItem> menuItems;
    private ListView listmenu;
    private TextView usernameTitle;
    private SharedPreferences appPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);
        appPreferences = getActivity().getSharedPreferences(getActivity().getString(R.string.app_preferences_name), Context.MODE_PRIVATE);
        listmenu = root.findViewById(R.id.profile_listmenu);
        usernameTitle = root.findViewById(R.id.username_title);
        usernameTitle.setText(appPreferences.getString("user_name","Username"));
        menuItems = List.of(
                new ListMenuItem(
                        getContext().getDrawable(R.drawable.ic_notifications),
                        getString(R.string.title_notifications), NotificationsActivity.class),
                new ListMenuItem(
                        getContext().getDrawable(R.drawable.ic_settings),
                        getString(R.string.title_settings), SettingsActivity.class),
                new ListMenuItem(
                        getContext().getDrawable(R.drawable.ic_info),
                        getString(R.string.title_info), InfoActivity.class)

        );
        ListMenuAdapter listmenuAdapter = new ListMenuAdapter(getContext(),getId(),menuItems);
        listmenu.setAdapter(listmenuAdapter);

        return root;
    }

}