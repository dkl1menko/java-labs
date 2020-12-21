package com.company.klimenko.nsu.tagshandlers;

import com.company.klimenko.nsu.common.node.ConstantNode;
import com.company.klimenko.nsu.common.node.Tags;
import com.company.klimenko.nsu.common.data.ResolveData;
import com.company.klimenko.nsu.common.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Doubleinfo extends TagsHandler {
    {
        tag = Tags.DOUBLE;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        refs.add(new StaticData(fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read()).toString()));
        refs.add(new StaticData(fourByteInInt((byte)inputStream.read(), (byte)inputStream.read(),
                (byte)inputStream.read(), (byte)inputStream.read()).toString()));

        return new ConstantNode(tag, refs);
    }
}
