package com.jipf.core.common.event;

import java.util.EventListener;

/**
 * 事件监听顶级接口
 * @author jipf
 */
public interface JListener extends EventListener {

    /**
     * 触发事件
     * @param jEventInfo
     */
    public void fireEvent(JEventInfo jEventInfo);

}
