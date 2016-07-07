package dspm.dc.ufc.br.cadesaude.custom_widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by jonas on 07/07/16.
 */
public class MyCustomButton extends Button {
    public MyCustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/MODERNA_.TTF"));
    }
}
