package com.gk.ims.user.plan.constant;

import java.io.Serializable;

/**
 * 搜索常数
 *
 * @author 张亚伟
 * @date 2020/07/10
 */
public class SearchConstant implements Serializable {
    private static final long serialVersionUID = 7092865672127598489L;

    /**
     * 成功返回码
     */
    public static final int SUCCESS_CODE= 0;


    /**
     * 公共搜索方案用户名
     */
    public static final String PUBLIC_PLAN_USER_ACCOUNT= "public_plan";

    /**
     * 默认分页页数
     */
    public static final Integer DEF_PAGE_NUM = 1;
    /**
     * 默认分页条数
     */
    public static final Integer DEF_PAGE_SIZE = 10;
}
