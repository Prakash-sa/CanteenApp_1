package com.example.canteenapp.ui.mess.updatemenu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.canteenapp.R;
import com.example.canteenapp.ui.mess.home.HomeViewModel;
import com.example.canteenapp.ui.mess.updatedatabase.UpdateDatabase;

public class UpdateMenu extends Fragment {

    private Intent intent;
    private UpdateMenuViewModel mViewModel;
    private Button update_next_menu,update_next_extra,bt_menu_monday,bt_menu_tuesday,bt_menu_wednesday,bt_menu_thursday,bt_menu_friday,bt_menu_saturday,bt_menu_sunday;
    private Button bt_extra_monday,bt_extra_tuesday,bt_extra_wednesday,bt_extra_thursday,bt_extra_friday,bt_extra_saturday,bt_extra_sunday;

    public static UpdateMenu newInstance() {
        return new UpdateMenu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel =
                ViewModelProviders.of(this).get(UpdateMenuViewModel.class);
        View root = inflater.inflate(R.layout.update_menu_fragment, container, false);
        update_next_extra=root.findViewById(R.id.update_next_extra);
        update_next_menu=root.findViewById(R.id.update_next_menu);
        bt_menu_monday=root.findViewById(R.id.bt_menu_monday);
        bt_menu_tuesday=root.findViewById(R.id.bt_menu_tuesday);
        bt_menu_wednesday=root.findViewById(R.id.bt_menu_wednesday);
        bt_menu_thursday=root.findViewById(R.id.bt_menu_thursday);
        bt_menu_friday=root.findViewById(R.id.bt_menu_friday);
        bt_menu_saturday=root.findViewById(R.id.bt_menu_saturday);
        bt_menu_sunday=root.findViewById(R.id.bt_menu_sunday);

        bt_extra_monday=root.findViewById(R.id.bt_extras_monday);
        bt_extra_tuesday=root.findViewById(R.id.bt_extras_tuesday);
        bt_extra_wednesday=root.findViewById(R.id.bt_extras_wednesday);
        bt_extra_thursday=root.findViewById(R.id.bt_extras_thursday);
        bt_extra_friday=root.findViewById(R.id.bt_extras_friday);
        bt_extra_saturday=root.findViewById(R.id.bt_extras_saturday);
        bt_extra_sunday=root.findViewById(R.id.bt_extras_sunday);

        update_next_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","");
                intent.putExtra("day","");
                startActivity(intent);
            }
        });
        update_next_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","");
                intent.putExtra("day","");
                startActivity(intent);
            }
        });
        bt_menu_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Monday");
                startActivity(intent);
            }
        });
        bt_menu_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Tuesday");
                startActivity(intent);
            }
        });
        bt_menu_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Wednesday");
                startActivity(intent);
            }
        });
        bt_menu_thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Thursday");
                startActivity(intent);
            }
        });
        bt_menu_friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Friday");
                startActivity(intent);
            }
        });
        bt_menu_saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Saturday");
                startActivity(intent);
            }
        });
        bt_menu_sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","menu");
                intent.putExtra("day","Sunday");
                startActivity(intent);
            }
        });
        bt_extra_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Monday");
                startActivity(intent);
            }
        });
        bt_extra_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Tuesday");
                startActivity(intent);
            }
        });
        bt_extra_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Wednesday");
                startActivity(intent);
            }
        });
        bt_extra_thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Thursday");
                startActivity(intent);
            }
        });
        bt_extra_friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Friday");
                startActivity(intent);
            }
        });
        bt_extra_saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Saturday");
                startActivity(intent);
            }
        });
        bt_extra_sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(), UpdateDatabase.class);
                intent.putExtra("type","extra");
                intent.putExtra("day","Sunday");
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateMenuViewModel.class);

        // TODO: Use the ViewModel
    }

}
