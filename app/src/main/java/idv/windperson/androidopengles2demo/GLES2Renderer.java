package idv.windperson.androidopengles2demo;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

/**
 * Created by windperson on 9/5/2016.
 */
public class GLES2Renderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f,1.0f, 0.0f, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        clearScreenWithColor(Counter.getUpDownValue());
    }

    private void clearScreenWithColor(int i) {
        GLES20.glClearColor(0.0f, Math.abs(i) * 0.1f, 0.0f, 1);
    }
}
