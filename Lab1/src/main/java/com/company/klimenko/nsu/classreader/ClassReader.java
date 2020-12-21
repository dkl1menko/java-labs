package com.company.klimenko.nsu.classreader;

import com.company.klimenko.nsu.common.node.ConstantNode;

import java.io.InputStream;
import java.util.Vector;

public class ClassReader {
    public Vector<ConstantNode> read(InputStream in) throws Exception{
        if (readHandle(in))
         return HandleTagsController.compute(in);
        throw new Exception();
    }
    
    private boolean readHandle(InputStream inputStream) throws Exception{
        for (int i = 0; i < 8; i++ ){
            inputStream.read();
        }
        return true;
    }
}
