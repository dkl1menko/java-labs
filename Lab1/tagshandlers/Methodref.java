package com.company.klimenko.nsu.tagshandlers;

import com.company.klimenko.nsu.common.node.ConstantNode;
import com.company.klimenko.nsu.common.node.Tags;
import com.company.klimenko.nsu.common.data.Ref;
import com.company.klimenko.nsu.common.data.ResolveData;
import com.company.klimenko.nsu.common.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Methodref extends TagsHandler {
    {
        tag = Tags.METHODREF;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new Ref(twoByteInInt((byte)inputStream.read(), (byte)inputStream.read()).toString()));
        refs.add(new StaticData("."));
        refs.add(new Ref(twoByteInInt((byte)inputStream.read(), (byte)inputStream.read()).toString()));
        return new ConstantNode(tag, refs);
    }
}
