package com.example.inventory;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.inventory.ItemsList.ItemsListViewImpl;
import com.example.inventory.Login.LoginViewImpl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kotlin.jvm.JvmField;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    @JvmField
    public ActivityTestRule<LoginViewImpl> rule = new ActivityTestRule(LoginViewImpl.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.inventory", appContext.getPackageName());
    }

    @Test
    public void userCanEnterUserName(){
        onView(withId(R.id.username)).perform(typeText("admin123"));
        pressBack();
        onView(withId(R.id.password)).perform(typeText("admin123"));
        pressBack();
        onView(withId(R.id.login)).perform(click());

        Intents.init();
        intended(hasComponent(ItemsListViewImpl.class.getName()));
        Intents.release();
    }
}
