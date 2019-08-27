package com.jingsong.ivmd.palyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener;
import com.dueeeke.videoplayer.player.AndroidMediaPlayerFactory;
import com.dueeeke.videoplayer.player.PlayerFactory;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.jingsong.ivmd.R;

import java.lang.reflect.Field;


/**
 * 播放器演示
 * Created by Devlin_n on 2017/4/7.
 */

public class PlayerActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ActionBar actionBar = getSupportActionBar();
        mVideoView = findViewById(R.id.player);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            if (actionBar != null) {
                actionBar.setTitle(name);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            StandardVideoController controller = new StandardVideoController(this);
            controller.setLive();
            String title = intent.getStringExtra(IntentKeys.TITLE);
            controller.setTitle(title);
            mVideoView.setVideoController(controller);

            mVideoView.setUrl(intent.getStringExtra("url"));

            //保存播放进度
            mVideoView.setProgressManager(new ProgressManagerImpl());
            //播放状态监听
            mVideoView.addOnVideoViewStateChangeListener(mOnVideoViewStateChangeListener);

            //使用IjkPlayer解码
            mVideoView.setPlayerFactory(IjkPlayerFactory.create(this));
            //使用ExoPlayer解码
//            mVideoView.setPlayerFactory(ExoMediaPlayerFactory.create(this));
            //使用MediaPlayer解码
//            mVideoView.setPlayerFactory(AndroidMediaPlayerFactory.create(this));

            mVideoView.start();
        }
    }

    private OnVideoViewStateChangeListener mOnVideoViewStateChangeListener = new OnVideoViewStateChangeListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {
            switch (playerState) {
                case VideoView.PLAYER_NORMAL://小屏
                    break;
                case VideoView.PLAYER_FULL_SCREEN://全屏
                    break;
            }
        }

        @Override
        public void onPlayStateChanged(int playState) {
            switch (playState) {
                case VideoView.STATE_IDLE:
                    break;
                case VideoView.STATE_PREPARING:
                    break;
                case VideoView.STATE_PREPARED:
                    //需在此时获取视频宽高
                    int[] videoSize = mVideoView.getVideoSize();
                    Log.d("tag", "视频宽：" + videoSize[0]);
                    Log.d("tag", "视频高：" + videoSize[1]);
                    break;
                case VideoView.STATE_PLAYING:
                    break;
                case VideoView.STATE_PAUSED:
                    break;
                case VideoView.STATE_BUFFERING:
                    break;
                case VideoView.STATE_BUFFERED:
                    break;
                case VideoView.STATE_PLAYBACK_COMPLETED:
                    break;
                case VideoView.STATE_ERROR:
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void screenScaleDefault(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_DEFAULT);
    }

    public void screenScale169(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_16_9);
    }

    public void screenScale43(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_4_3);
    }

    public void screenScaleOriginal(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_ORIGINAL);
    }

    public void screenScaleMatch(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_MATCH_PARENT);
    }

    public void screenScaleCenterCrop(View view) {
        mVideoView.setScreenScale(VideoView.SCREEN_SCALE_CENTER_CROP);
    }

    int i = 0;

    public void setMirrorRotate(View view) {
        mVideoView.setMirrorRotation(i % 2 == 0);
        i++;
    }

    public void setSpeed0_75(View view) {
        mVideoView.setSpeed(0.75f);
    }

    public void setSpeed0_5(View view) {
        mVideoView.setSpeed(0.5f);
    }

    public void setSpeed1_0(View view) {
        mVideoView.setSpeed(1.0f);
    }

    public void setSpeed1_5(View view) {
        mVideoView.setSpeed(1.5f);
    }

    public void setSpeed2_0(View view) {
        mVideoView.setSpeed(2.0f);
    }
}
