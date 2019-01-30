package com.test.vdb;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.test.vdb.R;
import com.test.vdb.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by deepanshurustagi on 1/30/19.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity =null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

        View view = mainActivity.findViewById(R.id.rv_data);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity =null;
    }

}