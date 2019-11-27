package com.jipf.springframeworkuse.beans.qf.impl;

import com.jipf.springframeworkuse.beans.qf.IQualifier;

public class QualifierTest1 implements IQualifier {
    @Override
    public void print() {
        System.out.println("======= QualifierTest1 ==========");
    }
}
