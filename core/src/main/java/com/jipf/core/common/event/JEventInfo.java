package com.jipf.core.common.event;

import java.util.EventObject;

/**
 * 事件信息
 *
 * @author jipf
 */
public class JEventInfo extends EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * 事件源
     */
    private Object source;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 事件参数
     */
    private Object eventParam;

    public JEventInfo(Object source, String eventName, Object eventParam) {
        super(source);
        this.source = source;
        this.eventName = eventName;
        this.eventParam = eventParam;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Object getEventParam() {
        return eventParam;
    }

    public void setEventParam(Object eventParam) {
        this.eventParam = eventParam;
    }
}