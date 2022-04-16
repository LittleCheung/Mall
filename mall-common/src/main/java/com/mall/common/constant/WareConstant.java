package com.mall.common.constant;

/**
 * 仓储常量类
 * @author littlecheung
 */
public class WareConstant {
    /**
     * 采购单状态枚举类
     */
    public enum PurchaseStatusEnum{

        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        RECEIVE(2,"已领取"),
        FINISH(3,"已完成"),
        HASERROR(4,"有异常");

        private int code;
        private String msg;

        private PurchaseStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 采购单详情状态枚举类
     */
    public enum PurchaseDetailStatusEnum{

        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        BUYING(2,"正在采购"),
        FINISH(3,"已完成"),
        HASERROR(4,"采购失败");

        private int code;
        private String msg;

        private PurchaseDetailStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
