package com.jipf.core.common.event.demo;

import com.jipf.core.common.event.JEventInfo;
import com.jipf.core.common.event.JListener;

public class DemoListener implements JListener {

    @Override
    public void fireEvent(JEventInfo jEventInfo) {
        System.out.println(jEventInfo.getEventName());
    }
}