package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navView;
    private static final SparseIntArray NAV_TAB_DIRECTIONS = new SparseIntArray();

    static {
        NAV_TAB_DIRECTIONS.put(R.id.navigation_home, R.id.homeFragment);
        NAV_TAB_DIRECTIONS.put(R.id.navigation_dashboard, R.id.dashFragment);
        NAV_TAB_DIRECTIONS.put(R.id.navigation_notifications, R.id.notificationsFragment);
        NAV_TAB_DIRECTIONS.put(R.id.navigation_search, R.id.searchFragment);
        NAV_TAB_DIRECTIONS.put(R.id.navigation_account, R.id.accountFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.dashFragment,
                R.id.notificationsFragment,
                R.id.searchFragment,
                R.id.accountFragment)
                .build();

        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        setBottomNavigationView();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                int currentId = NAV_TAB_DIRECTIONS.get(id);
                if (navController.getCurrentDestination().getId() != currentId) {
                    navController.popBackStack();
                    navController.navigate(currentId);
                    navView.setItemTextAppearanceActive(R.style.bottomTitleTextSelected);
                    return true;
                }
                return false;
            }
        });


    }

    private void setBottomNavigationView() {
        navView.setItemTextAppearanceInactive(R.style.bottomTitleTextDefault);
        navView.setItemTextAppearanceActive(R.style.bottomTitleTextSelected);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

    }


}
