package com.jipf.core.common.event;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 事件处理中心
 *
 * @author jipf
 */
public class JEventCenter {

    private Collection listeners;

    /**
     * 注册监听器
     *
     * @param listener AcpListener
     */
    public void addListener(JListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     *
     * @param listener AcpListener
     */
    public void removeListener(JListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 通知所有的AcpListener
     */
    public void fireEvent(JEventInfo jEventInfo) {
        Iterator iterator = listeners.iterator();
        while (iterator.hasNext()) {
            JListener listener = (JListener) iterator.next();
            listener.fireEvent(jEventInfo);
        }
    }
}
