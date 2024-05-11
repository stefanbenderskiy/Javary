package com.reflection.javary.fragments.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;

import java.util.List;

public class ProfileFragment extends Fragment {


    private List<ListMenuItem> menuItems;
    private ListView listmenu;
    private TextView usernameTitle;
    private DataBase appDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);
        appDB = new DataBase(getContext(),getString(R.string.app_database_name));
        Dataset userdata = new Dataset(appDB,"","userdata");
        listmenu = root.findViewById(R.id.profile_listmenu);
        usernameTitle = root.findViewById(R.id.username_title);
        usernameTitle.setText(userdata.getString("username","User"));
        menuItems = List.of(
                new ListMenuItem(
                        getContext().getDrawable(R.drawable.ic_settings),
                        getString(R.string.title_settings), SettingsActivity.class)
        );
        ListMenuAdapter listmenuAdapter = new ListMenuAdapter(getContext(),getId(),menuItems);
        listmenu.setAdapter(listmenuAdapter);

        return root;
    }

}