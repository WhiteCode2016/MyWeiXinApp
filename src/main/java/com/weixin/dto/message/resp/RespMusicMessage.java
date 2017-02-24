package com.weixin.dto.message.resp;

import com.weixin.dto.message.Music;

/**
 * 音乐消息
 * Created by White on 2017/2/20.
 */
public class RespMusicMessage extends RespBaseMessage {
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
