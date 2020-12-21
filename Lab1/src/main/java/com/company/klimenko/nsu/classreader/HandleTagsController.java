package com.company.klimenko.nsu.classreader;

import com.company.klimenko.nsu.tagshandlers.TagsHandler;
import com.company.klimenko.nsu.common.node.ConstantNode;
import com.company.klimenko.nsu.common.node.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class HandleTagsController {

    public static Vector<ConstantNode> compute(InputStream inputStream) throws Exception{
        HandlerFabricy handlerFabricy = new HandlerFabricy();
        Vector<ConstantNode> pool = new Vector<>();

        int tag;
        int y = computeSize(inputStream);
        while (--y != 0){
            tag = inputStream.read();
            TagsHandler t = handlerFabricy.getTagsHandler(tag);

            pool.add(handlerFabricy.getTagsHandler(tag).compute(inputStream));

            if (t.tag.equals(Tags.LONG) || t.tag.equals(Tags.DOUBLE)){
                pool.add(new ConstantNode(null, null));
                y--;
            }
        }
        return pool;

    }

    private static int computeSize(InputStream inputStream) throws IOException {
        int b1 = inputStream.read();
        int b2 = inputStream.read();
        return ((0xFF & b1) << 8) | (0xFF & b2);

    }
}
