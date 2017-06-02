package fuckermonkey.phots.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import fuckermonkey.phots.R;

/**
 * 流程控制的比较严谨，比如setup函数的使用
 * updateShaderMatrix保证图片损失度最小和始终绘制图片正中央的那部分
 * 作者思路是画圆用渲染器位图填充，而不是把Bitmap重绘切割成一个圆形图片。
 */
public class PressableImageView extends AppCompatImageView {

    private Context mContext;
    private AttributeSet mAttributeSet;
    private int mBackgroudId;

    //按下显示的遮罩层
    private final Paint mPressedPaint = new Paint();

    public PressableImageView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PressableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造函数
     */
    public PressableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mAttributeSet = attrs;
        init();
    }

    /**
     * 作用就是保证第一次执行setup函数里下面代码要在构造函数执行完毕时调用
     */
    private void init() {
        mPressedPaint.setAntiAlias(true); //设置遮罩层画笔
        mPressedPaint.setColor(0x33000000);

//        TypedArray typedArray=mContext.obtainStyledAttributes(mAttributeSet, R.styleable.AppCompatImageView);
//        TypedArray params = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        //得到自定义控件的属性值。
        mBackgroudId = mAttributeSet.getAttributeResourceValue("fuckermonkey.phots.view.PressableImageView", "background", R.color.pink_primary_light);
    }


    @Override
    protected void onDraw(Canvas canvas) {

//        setBackgroundResource(mBackgroudId);
        super.onDraw(canvas);
        if (isPressed()) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), mPressedPaint);
        }

    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
        invalidate();
    }


}
