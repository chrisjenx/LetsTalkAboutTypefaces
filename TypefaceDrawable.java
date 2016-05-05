package com.owlr.ui.drawables;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public abstract class TypefaceDrawable extends Drawable {

  private final Paint mPaint =
      new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
  private final Resources mResources;
  private final Typeface mTypeface;
  private final String mText;

  private int mIntrinsicWidth;
  private int mIntrinsicHeight;

  public TypefaceDrawable(Context context, Typeface typeface, String text, int textSizeRes) {
    mTypeface = typeface;
    mResources = context.getResources();
    mText = text;
    initPaint(mPaint, textSizeRes);
  }

  protected void initPaint(Paint paint, final int textSizeRes) {
    paint.setTypeface(mTypeface);
    paint.setTextSize(mResources.getDimensionPixelSize(textSizeRes));
    paint.setTextAlign(Paint.Align.CENTER);
    mIntrinsicWidth = (int) mPaint.measureText(mText, 0, mText.length());
    mIntrinsicHeight = mPaint.getFontMetricsInt(null);
  }

  @Override public void draw(Canvas canvas) {
    final Rect bounds = getBounds();
    canvas.drawText(mText, 0, mText.length(), bounds.centerX(), bounds.bottom, mPaint);
  }

  @Override public void setAlpha(int alpha) {
    mPaint.setAlpha(alpha);
  }

  @Override public void setColorFilter(ColorFilter cf) {
    mPaint.setColorFilter(cf);
  }

  @Override public int getOpacity() {
    return PixelFormat.TRANSLUCENT;
  }

  @Override public int getIntrinsicWidth() {
    return mIntrinsicWidth;
  }

  @Override public int getIntrinsicHeight() {
    return mIntrinsicHeight;
  }
}
