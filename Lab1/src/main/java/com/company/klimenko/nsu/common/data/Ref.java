package com.company.klimenko.nsu.common.data;

import com.company.klimenko.nsu.common.node.ConstantNode;

import java.util.Vector;

public class Ref extends ResolveData {

    public Ref(String toResolve) {
        super(toResolve);
    }

    @Override
    public String getData() {
        return  "#" + toResolve;
    }

    @Override
    public String resolve(Vector<ConstantNode> nodes) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((Integer.parseInt(toResolve) - 1) >= nodes.size()) return stringBuilder.toString();
        ConstantNode c = nodes.get(Integer.parseInt(toResolve) - 1);
        for (ResolveData s : c.getRefs()){
          stringBuilder.append(s.resolve(nodes));
        }
        return stringBuilder.toString();
    }
}
