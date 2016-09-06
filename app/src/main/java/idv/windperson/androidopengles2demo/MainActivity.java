package idv.windperson.androidopengles2demo;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
//import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GLSurfaceView _surfaceView;
    private float _dxFiltered = 0.0f;
    private float _zAngle = 0.0f;
    private float _zAngleFiltered = 0.0f;

    private int _width;
    private final float _filterSensitivity = 0.1f;
    private final float _TOUCH_SENSITIVITY = 0.6f;
    private float _ANGLE_SPAN = 90.0f;
    private float _touchedX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _surfaceView = new GLSurfaceView(this);
        _surfaceView.setEGLContextClientVersion(2);
        _surfaceView.setRenderer(new GLES2Renderer());
        setContentView(_surfaceView);
        RelativeLayout rl = new RelativeLayout(this);
        rl.setOnTouchListener(this);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        addContentView(rl, rllp);
        getDeviceWidth();
    }

    @Override
    protected void onPause() {
        _surfaceView.onPause();
        super.onPause();
        if (isFinishing()) {
            // save high scores etc
            GLES2Renderer.setZAngle(0);
            _dxFiltered = 0.0f;
            _zAngle = 0.0f;
            _zAngleFiltered = 0.0f;
            this.finish();
        }
    }

    public void getDeviceWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        if (width > height) {
            _width = width;
        } else {
            _width = height;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _touchedX = event.getX();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float touchedX = event.getX();
            float dx = Math.abs(_touchedX - touchedX);
            _dxFiltered = _dxFiltered * (1.0f - _filterSensitivity) + dx
                    * _filterSensitivity;

            if (touchedX < _touchedX) {
                _zAngle = (2 * _dxFiltered / _width) * _TOUCH_SENSITIVITY
                        * _ANGLE_SPAN;
                _zAngleFiltered = _zAngleFiltered * (1.0f - _filterSensitivity)
                        + _zAngle * _filterSensitivity;
                GLES2Renderer.setZAngle(GLES2Renderer.getZAngle()
                        + _zAngleFiltered);
            } else {
                _zAngle = (2 * _dxFiltered / _width) * _TOUCH_SENSITIVITY
                        * _ANGLE_SPAN;
                _zAngleFiltered = _zAngleFiltered * (1.0f - _filterSensitivity)
                        + _zAngle * _filterSensitivity;
                GLES2Renderer.setZAngle(GLES2Renderer.getZAngle()
                        - _zAngleFiltered);
            }
        }
        //Log.d("onTouch","_zAngle="+_zAngle);
        return true;
    }
}
