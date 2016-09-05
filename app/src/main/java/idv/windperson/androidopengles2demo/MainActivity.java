package idv.windperson.androidopengles2demo;

import android.opengl.GLSurfaceView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView _surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _surfaceView = new GLSurfaceView(this);
        _surfaceView.setEGLContextClientVersion(2);
        _surfaceView.setRenderer(new GLES2Renderer());
        setContentView(_surfaceView);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 200, 0, 0);

        Button buttonUp = new Button(this);
        buttonUp.setText("UP");
        buttonUp.setWidth(110);
        buttonUp.setHeight(85);
        LinearLayout.LayoutParams layoutParamsUpBtn =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsUpBtn.setMargins(0, 0, 0, 20);
        layout.addView(buttonUp, layoutParamsUpBtn);

        Button buttonDown = new Button(this);
        buttonDown.setText("DOWN");
        buttonDown.setWidth(110);
        buttonDown.setHeight(85);
        LinearLayout.LayoutParams layoutParamsDownBtn =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsDownBtn.setMargins(0, 20, 0, 0);
        layout.addView(buttonDown, layoutParamsDownBtn);

        layout.setGravity(Gravity.CENTER | Gravity.END);

        addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }
}
