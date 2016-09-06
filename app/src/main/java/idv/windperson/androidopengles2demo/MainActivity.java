package idv.windperson.androidopengles2demo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView _surfaceView;
    private TextView _textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _surfaceView = new GLSurfaceView(this);
        _surfaceView.setEGLContextClientVersion(2);
        _surfaceView.setRenderer(new GLES2Renderer());
        setContentView(_surfaceView);

        LinearLayout layout = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParamsUpDown =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setGravity(Gravity.CENTER | Gravity.END);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linearLayoutView = inflater.inflate(R.layout.updown, layout, false);
        layout.addView(linearLayoutView);

        View textView = inflater.inflate(R.layout.counter, layout, false);
        layout.addView(textView);

        addContentView(layout, layoutParamsUpDown);

        _textView = (TextView) findViewById(R.id.counter);
        setUpDownBtnClickListeners();
    }

    private void setUpDownBtnClickListeners() {
        Button upBtn, dwnBtn;
        upBtn = (Button) findViewById(R.id.up);
        dwnBtn = (Button) findViewById(R.id.down);

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.getUpDownNextValue();
                _textView.setText(String.valueOf(Counter.getUpDownValue()));
            }
        });

        dwnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.getUpDownPreviousValue();
                _textView.setText(String.valueOf(Counter.getUpDownValue()));
            }
        });
    }
}
