package dspm.dc.ufc.br.cadesaude;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.robotium.solo.Solo;

/**
 * Created by jonas on 05/07/16.
 */
public class PostoActivityTest
        extends ActivityInstrumentationTestCase2<PostoActivity>{

    Solo solo;

    public PostoActivityTest(){
        super(PostoActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        /**setUp() is run before a test case is started.
         This is where the solo object is created.*/
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        /**tearDown() is run after a test case has finished.
         finishOpenedActivities() will finish all the activities that
         have been opened during the test execution.*/
        solo.finishOpenedActivities();
    }

    @SmallTest
    public void testFirst() throws Exception{
        solo.assertCurrentActivity("This isn't the right Activity to test!",PostoActivity.class);
        solo.assertMemoryNotLow();
        solo.clearLog();
    }

}