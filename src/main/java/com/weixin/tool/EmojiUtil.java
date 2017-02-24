package com.weixin.tool;

/**
 * Emoji表情工具类
 * Emoji表情有很多种版本，包括Unified、DoCoMo、KDDI、Softbank和Google，而且不同版本的表情代码也不一样，
 * 不同的手机操作系统、甚至是同一操作系统的不同版本所支持的emoji表情又不一样。
 * QQ表情编码：http://blog.csdn.net/lyq8479/article/details/9229631
 * Unified表情编码：http://blog.csdn.net/lyq8479/article/details/9229637
 * SoftankB表情编码：http://blog.csdn.net/lyq8479/article/details/9393097
 * Created by White on 2017/2/21.
 */
public class EmojiUtil {

    /**
     * Unified表情
     * emoji表情转换(hex -> utf-16)
     * @param hexEmoji
     * @return
     */
    public static String unifiedEmoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }
}
