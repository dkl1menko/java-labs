package com.company.klimenko.nsu.tagshandlers;

import com.company.klimenko.nsu.common.node.ConstantNode;
import com.company.klimenko.nsu.common.node.Tags;
import com.company.klimenko.nsu.common.data.ResolveData;
import com.company.klimenko.nsu.common.data.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class Utf8 extends TagsHandler {
    {
        tag = Tags.UTF8;
    }
    @Override
    public ConstantNode compute(InputStream inputStream)  throws IOException {
        Vector<ResolveData> refs = new Vector<>();
        int count = twoByteInInt((byte)inputStream.read(), (byte)inputStream.read());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < count; i++){
           stringBuilder.appendCodePoint((byte)inputStream.read());
        }
        refs.add(new StaticData(stringBuilder.toString()));
        return new ConstantNode(tag, refs);
    }
}
