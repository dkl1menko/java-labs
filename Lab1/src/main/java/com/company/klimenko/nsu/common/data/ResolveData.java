package com.company.klimenko.nsu.common.data;

import com.company.klimenko.nsu.common.node.ConstantNode;

import java.util.Vector;

public abstract class ResolveData {
    public String getToResolve() {
        return toResolve;
    }

    String toResolve;

    public ResolveData(String toResolve){

        this.toResolve = toResolve;
    }
    public abstract String getData();
    public abstract String resolve(Vector<ConstantNode> nodes);
}
