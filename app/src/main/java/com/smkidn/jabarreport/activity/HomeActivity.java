package com.smkidn.jabarreport.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.smkidn.jabarreport.R;
import com.smkidn.jabarreport.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager manager;
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.recyclerFeed)
    RecyclerView recyclerFeed;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    FlowingDrawer drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupBottomNav();

        manager = new SessionManager(this);
        manager.checkLogin();
    }

    private void setupBottomNav() {
        BottomNavigationViewEx bottom = findViewById(R.id.bottom_nav);
        bottom.enableAnimation(false);
        bottom.enableItemShiftingMode(false);
        bottom.enableShiftingMode(false);
        bottom.setTextVisibility(false);
    }

    private void setupDrawer() {
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return false;
    }
}
