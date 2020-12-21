package com.company.klimenko.nsu.common.data;

import com.company.klimenko.nsu.common.node.ConstantNode;

import java.util.Vector;

public class StaticData extends ResolveData {


    public StaticData(String toResolve) {
        super(toResolve);
    }

    @Override
    public String getData() {
        return toResolve;
    }

    @Override
    public String resolve(Vector<ConstantNode> nodes) {
       return toResolve;
    }
}
