package dspm.dc.ufc.br.cadesaude;

import android.test.ActivityInstrumentationTestCase2;

//import static org.junit.Assert.*;
import com.robotium.solo.Solo;

/**
 * Created by RH on 07/07/2016.
 */
public class ApresentacaoActivityTest extends ActivityInstrumentationTestCase2<ApresentacaoActivity>{

    private Solo solo;

    public ApresentacaoActivityTest(){
        super(ApresentacaoActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    //@org.junit.Test
    public void testOnCreate() throws Exception {
        solo.unlockScreen();
    }

    ///@Test
    public void testOnClickInfoPosto() throws Exception {
        solo.clickOnText("Ir para info posto");
        solo.assertCurrentActivity("Expected PostoActivity", PostoActivity.class);
    }

    //@org.junit.Test
    public void testOnClickMaps() throws Exception {
        solo.clickOnText("Mapas");
        solo.assertCurrentActivity("Expected MapsActivity", MapsActivity.class);
    }

    //@org.junit.Test
    public void testOnClickZerarSharedPrefs() throws Exception {

    }
}