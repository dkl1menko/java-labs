package com.company.klimenko.nsu.classreader;

import com.company.klimenko.nsu.tagshandlers.*;
import com.company.klimenko.nsu.tagshandlers.Class;

import java.util.Vector;

public class HandlerFabricy {
    Vector<TagsHandler> tagsHandlers = new Vector<>();
    public HandlerFabricy(){
        tagsHandlers.add(new Class());
        tagsHandlers.add(new Doubleinfo());
        tagsHandlers.add(new Fieldref());
        tagsHandlers.add(new Integerinfo());
        tagsHandlers.add(new InterfaceMethodref());
        tagsHandlers.add(new Longinfo());
        tagsHandlers.add(new MethodHandle());
        tagsHandlers.add(new Methodref());
        tagsHandlers.add(new Methodtype());
        tagsHandlers.add(new NameAndType());
        tagsHandlers.add(new Stringinfo());
        tagsHandlers.add(new Utf8());
        tagsHandlers.add(new InvokeDynamic());
    }
    public TagsHandler getTagsHandler(int tag) throws Exception{
        for (TagsHandler h : tagsHandlers) {
            if (h.tag.getId() == tag) return h;
        }
        System.out.println(tag);
        throw new Exception();
    }
}
