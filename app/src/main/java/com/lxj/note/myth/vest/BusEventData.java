package com.lxj.note.myth.vest;

/**
 * EventBus或者Otto事件对象
 * 传递同一个对象 根据Key来区分
 */
public class BusEventData {

    /**
     * 刷新
     */
    public static  final String KEY_REFRESH = "KEY_REFRESH" ;
    /**
     * 版本更新
     */
    public static  final String KEY_APP_UPDATE= "KEY_APP_UPDATE" ;
    /**
     * 关闭
     */
    public static  final String KEY_CLOSE = "KEY_CLOSE" ;
    /**
     * 支付成功
     */
    public static  final String KEY_PAY_SUCCESS = "KEY_PAY_SUCCESS" ;
    /**
     * 支付失败
     */
    public static  final String KEY_PAY_ERROR = "PAY_ERROR" ;

    private String eventKey;
    private String action;
    private String content;
    private Object object;

    public BusEventData(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}