package com.company.klimenko.nsu.tagshandlers;

import com.company.klimenko.nsu.common.node.ConstantNode;
import com.company.klimenko.nsu.common.node.Tags;

import java.io.IOException;
import java.io.InputStream;

public abstract class TagsHandler  {
        public Tags tag;
        public abstract ConstantNode compute(InputStream inputStream) throws IOException;

        protected Integer twoByteInInt(byte b1, byte b2){
                return ((0xFF & b1) << 8) | (0xFF & b2);
        }

        protected Integer fourByteInInt(byte b1, byte b2, byte b3, byte b4){
                return  ((0xFF & b1) << 24) | ((0xFF & b2) << 16) |
                        ((0xFF & b3) << 8) | (0xFF & b4);
        }
}
