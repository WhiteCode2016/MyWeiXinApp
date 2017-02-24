package com.weixin.tool;

/**
 * Enum工具类
 * Created by White on 2017/2/24.
 */
public class EnumUtil {

    //设置请求类型为小写
    //例如: EnumUtil.toLowerCaseEnum(MsgTypeEnum.TEXT)
    // TEXT -> text
    public static String toLowerCaseEnum(Enum typeEnum) {
        return typeEnum.toString().toLowerCase();
    }
}
