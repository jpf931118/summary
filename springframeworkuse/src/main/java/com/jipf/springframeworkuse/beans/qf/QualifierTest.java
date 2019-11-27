package com.jipf.springframeworkuse.beans.qf;


public abstract class QualifierTest {
    public void test() {
        getIQualifier().print();
    }
    public abstract IQualifier getIQualifier();
}
