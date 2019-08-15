package com.example.eyepetizer.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BaseViewPager extends ViewPager
{
    private boolean isCanScroll = true;
    public BaseViewPager(Context context)
    {
        super(context);
    }
    public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setScrollble(boolean scrollble) {
        isCanScroll = scrollble;
    }
    @Override  public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(event);
        }
    else {  return false;  }
    }
}
