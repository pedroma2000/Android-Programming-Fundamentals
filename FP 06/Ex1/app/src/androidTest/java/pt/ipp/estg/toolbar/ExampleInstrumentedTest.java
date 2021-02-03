package pt.ipp.estg.toolbar;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityScenarioRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click onit.

                onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new
                        ViewAction() {
                            @Override
                            public Matcher<View> getConstraints() {
                                return null;
                            }
                            @Override
                            public String getDescription() {
                                return "Click on specific button";
                            }
                            @Override
                            public void perform(UiController uiController, View
                                    view) {
                                View button =
                                        view.findViewById(R.id.message_button);
                                // Maybe check for null
                                button.performClick();
                            }
                        }
                ));

        String itemElementText = "Person 0";
        onView(withId(R.id.texto_view)).check(matches(withText(itemElementText)));
    }
}