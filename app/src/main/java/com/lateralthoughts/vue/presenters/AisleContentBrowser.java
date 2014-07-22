package com.lateralthoughts.vue.presenters;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.lateralthoughts.vue.R;


public class AisleContentBrowser extends ViewFlipper {
    private String mAisleUniqueId;
    int mCurrentIndex;


    int mCount;
    boolean mLoadImage;
    private int mScrollIndex;
    private Context mContext;
    
    public boolean mAnimationInProgress;
    private int mDebugTapCount = 0;
    private long mDownPressStartTime = 0;
    private final int MAX_ELAPSED_DURATION_FOR_TAP = 200;
    public static final int SWIPE_MIN_DISTANCE = 30;
    private IAisleContentAdapter mSpecialNeedsAdapter;
    
    public int mFirstX;
    public int mLastX;
    public int mFirstY;
    public int mLastY;
    private boolean mTouchMoved;
    private int mTapTimeout;
    private boolean mSetPosition;
    public boolean isLeft;
    public boolean isRight;
    private GestureDetector mDetector;
    
    public AisleContentBrowser(Context context) {
        super(context);
        mContext = context;
       // mAisleUniqueId = AisleWindowContent.EMPTY_AISLE_CONTENT_ID;
        mScrollIndex = 0;
    }
    
    public AisleContentBrowser(Context context, AttributeSet attribs) {
        super(context, attribs);
       // mAisleUniqueId = AisleWindowContent.EMPTY_AISLE_CONTENT_ID;
        mScrollIndex = 0;
        mAnimationInProgress = false;
        mContext = context;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        mTapTimeout = ViewConfiguration.getTapTimeout();
        this.setBackgroundColor(Color.WHITE);
        mDetector = new GestureDetector(AisleContentBrowser.this.getContext(),
                new mListener());
    }
    
    public void setUniqueId(String id) {
        mAisleUniqueId = id;
    }
    
    public String getUniqueId() {
        return mAisleUniqueId;
    }
    
    public void setScrollIndex(int scrollIndex) {
        mScrollIndex = scrollIndex;
        mCurrentIndex = scrollIndex;
    }
    
    public int getScrollIndex() {
        return mScrollIndex;
    }
    
    public int getCurrentIndex() {
        return mCurrentIndex;
    }
    

    

    
    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final AisleContentBrowser aisleContentBrowser = (AisleContentBrowser) this;
        mSpecialNeedsAdapter = AisleContentAdapter.getInstance();
        boolean result = mDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCurrentIndex = aisleContentBrowser
                    .indexOfChild(aisleContentBrowser.getCurrentView());
            mAnimationInProgress = false;
            mFirstX = (int) event.getX();
            mFirstY = (int) event.getY();
            mDownPressStartTime = System.currentTimeMillis();
            return super.onTouchEvent(event);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mTouchMoved) {
                mTouchMoved = false;
                return true;
            }
            mAnimationInProgress = false;
            
            mFirstX = 0;
            mLastX = 0;
            return super.onTouchEvent(event);
        }
        
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mLastX = (int) event.getX();
            mLastY = (int) event.getY();
            if (mFirstY - mLastY > SWIPE_MIN_DISTANCE
                    || mLastY - mFirstY > SWIPE_MIN_DISTANCE) {
                return super.onTouchEvent(event);
            }
            if (mFirstX - mLastX > SWIPE_MIN_DISTANCE) {
                
                // In this case, the user is moving the finger right to left
                // The current image needs to slide out left and the "next"
                // image
                // needs to fade in
                mTouchMoved = true;
                requestDisallowInterceptTouchEvent(true);
                if (!mAnimationInProgress) {
                    View nextView = null;
                    final int currentIndex = aisleContentBrowser
                            .indexOfChild(aisleContentBrowser.getCurrentView());
                    nextView = (View) aisleContentBrowser
                            .getChildAt(currentIndex + 1);
                    
                    mLoadImage = false;
                    if (/* null != mSpecialNeedsAdapter && */null == nextView
                            || getImageListCount() == 1) {
                        mLoadImage = true;
                       
                        if (!mSpecialNeedsAdapter.setAisleContent(
                                AisleContentBrowser.this, currentIndex,
                                currentIndex + 1, true, getImageListCount())) {
                            mAnimationInProgress = true;
                            Animation cantWrapRight = AnimationUtils
                                    .loadAnimation(mContext,
                                            R.anim.cant_wrap_right);
                            cantWrapRight
                                    .setAnimationListener(new Animation.AnimationListener() {
                                        public void onAnimationEnd(
                                                Animation animation) {
                                            Animation cantWrapRightPart2 = AnimationUtils
                                                    .loadAnimation(
                                                            mContext,
                                                            R.anim.cant_wrap_right2);
                                            aisleContentBrowser
                                                    .getCurrentView()
                                                    .startAnimation(
                                                            cantWrapRightPart2);
                                        }
                                        
                                        public void onAnimationStart(
                                                Animation animation) {
                                            
                                        }
                                        
                                        public void onAnimationRepeat(
                                                Animation animation) {
                                            
                                        }
                                    });
                            aisleContentBrowser.getCurrentView()
                                    .startAnimation(cantWrapRight);
                            return super.onTouchEvent(event);
                        }
                    }
                    
                    Animation currentGoLeft = AnimationUtils.loadAnimation(
                            mContext, R.anim.right_out);
                    final Animation nextFadeIn = AnimationUtils.loadAnimation(
                            mContext, R.anim.fade_in);
                    mAnimationInProgress = true;
                    aisleContentBrowser.setInAnimation(nextFadeIn);
                    aisleContentBrowser.setOutAnimation(currentGoLeft);
                    currentGoLeft
                            .setAnimationListener(new Animation.AnimationListener() {
                                public void onAnimationEnd(Animation animation) {
                                    mCurrentIndex = currentIndex + 1;
                                }
                                
                                public void onAnimationStart(Animation animation) {
                                    
                                }
                                
                                public void onAnimationRepeat(
                                        Animation animation) {
                                    
                                }
                            });
                    
                    aisleContentBrowser.setDisplayedChild(currentIndex + 1);
                    // aisleContentBrowser.invalidate();
                    return super.onTouchEvent(event);
                }
            } else if (mLastX - mFirstX > SWIPE_MIN_DISTANCE) {
                
                requestDisallowInterceptTouchEvent(true);
                mTouchMoved = true;
                if (!mAnimationInProgress) {
                    final int currentIndex = aisleContentBrowser
                            .indexOfChild(aisleContentBrowser.getCurrentView());
                    View nextView = null;
                    nextView = (View) aisleContentBrowser
                            .getChildAt(currentIndex - 1);
                    mLoadImage = false;
                    if (/* null != mSpecialNeedsAdapter && */null == nextView
                            || getImageListCount() == 1) {
                        mLoadImage = true;
                        if (!mSpecialNeedsAdapter.setAisleContent(
                                AisleContentBrowser.this, currentIndex,
                                currentIndex - 1, true, getImageListCount())) {
                            
                            Animation cantWrapLeft = AnimationUtils
                                    .loadAnimation(mContext,
                                            R.anim.cant_wrap_left);
                            
                            cantWrapLeft
                                    .setAnimationListener(new Animation.AnimationListener() {
                                        public void onAnimationEnd(
                                                Animation animation) {
                                            Animation cantWrapLeftPart2 = AnimationUtils
                                                    .loadAnimation(
                                                            mContext,
                                                            R.anim.cant_wrap_left2);
                                            aisleContentBrowser
                                                    .getCurrentView()
                                                    .startAnimation(
                                                            cantWrapLeftPart2);

                                        }
                                        
                                        public void onAnimationStart(
                                                Animation animation) {
                                            
                                        }
                                        
                                        public void onAnimationRepeat(
                                                Animation animation) {
                                            
                                        }
                                    });
                            aisleContentBrowser.getCurrentView()
                                    .startAnimation(cantWrapLeft);
                            return super.onTouchEvent(event);
                        }
                    }
                    
                    Animation currentGoRight = AnimationUtils.loadAnimation(
                            mContext, R.anim.left_in);
                    final Animation nextFadeIn = AnimationUtils.loadAnimation(
                            mContext, R.anim.fade_in);
                    mAnimationInProgress = true;
                    aisleContentBrowser.setInAnimation(nextFadeIn);
                    aisleContentBrowser.setOutAnimation(currentGoRight);
                    currentGoRight
                            .setAnimationListener(new Animation.AnimationListener() {
                                public void onAnimationEnd(Animation animation) {
                                    mCurrentIndex = currentIndex - 1;
                                    // aisleContentBrowser.setDisplayedChild(currentIndex-1);
                                }
                                
                                public void onAnimationStart(Animation animation) {
                                    
                                }
                                
                                public void onAnimationRepeat(
                                        Animation animation) {
                                    
                                }
                            });
                    
                    aisleContentBrowser.setDisplayedChild(currentIndex - 1);
                    return super.onTouchEvent(event);
                }
            }
        }
        return super.onTouchEvent(event);
    }
    
    public void setCustomAdapter(IAisleContentAdapter adapter) {
        mSpecialNeedsAdapter = adapter;
    }
    
    public IAisleContentAdapter getCustomAdapter() {
        return mSpecialNeedsAdapter;
    }
    
    class mListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mClickListener != null && null != mSpecialNeedsAdapter) {
                mClickListener.onDoubleTap(mAisleUniqueId);
            }
            return super.onDoubleTap(e);
        }
        
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }
        
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
    }
    
    public interface AisleContentClickListener {
        public void onAisleClicked(String id, int count, int currentPosition);
        public boolean isFlingCalled();
        public boolean onDoubleTap(String id);
        public void refreshList();
    }
    public void setAisleContentClickListener(AisleContentClickListener listener) {
        mClickListener = listener;
    }
    private AisleContentClickListener mClickListener;
    public void setImageListCount(int count) {
        mCount = count;
    }
    
    public int getImageListCount() {
        return mCount;
    }

}
