package com.jipf.core.common.event.demo;

import com.jipf.core.common.event.JEventCenter;
import com.jipf.core.common.event.JEventInfo;


public class DemoMain {

    public static void main(String[] args) {

        JEventInfo jEventInfo = new JEventInfo(args, "demoEvent", "Hello,Event");
        JEventCenter jEventCenter = new JEventCenter();
        DemoListener demoListener = new DemoListener();
        //注册监听器
        jEventCenter.addListener(demoListener);

        jEventCenter.fireEvent(jEventInfo);
    }
}
