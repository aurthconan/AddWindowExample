package individual.aurthconan.addwindowexample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private WindowManager windowManager;
    private TextView floatingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                200, 200,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.setTitle("individual.aurthconan.addwindowexample.floatingwindow");
        params.x = 0; params.y = 200;
        params.gravity = Gravity.TOP | Gravity.LEFT;
        floatingView = new TextView(this);
        floatingView.setText("BOOM!");
        int textColor = randomColor();
        floatingView.setTextColor(textColor);
        floatingView.setBackgroundColor(invertColor(textColor));

        floatingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch( event.getAction()&MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        int textColor = randomColor();
                        floatingView.setTextColor(textColor);
                        floatingView.setBackgroundColor(invertColor(textColor));
                        break;
                }
                return true;
            }
        });
        windowManager.addView(floatingView, params);
    }

    private static int randomColor() {
        return Color.argb(255, (int)(Math.random()*255.0), (int)(Math.random()*255.0), (int)(Math.random()*255.0));
    }
    private static int invertColor(int color) {
        return Color.argb(255, 255-Color.red(color), 255-Color.green(color), 255-Color.blue(color));
    }
    @Override
    protected void onPause() {
        super.onPause();
        windowManager.removeView(floatingView);
    }

}
