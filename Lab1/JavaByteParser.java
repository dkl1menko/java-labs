package com.company.klimenko.nsu;

import com.company.klimenko.nsu.classreader.ClassReader;
import com.company.klimenko.nsu.common.data.ResolveData;
import com.company.klimenko.nsu.common.data.StaticData;
import com.company.klimenko.nsu.common.node.ConstantNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class JavaByteParser {
    public static void ShowConstant(File file) throws Exception {
        System.out.println("Constant pool:");
        ClassReader reader = new ClassReader();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

       Vector<ConstantNode> nodes = reader.read(fileInputStream);

       int v = 1;
       for (ConstantNode node : nodes){
           if (node.getTag() == null){
               v++;
               continue;
           }
           System.out.print("#" + v++ + " = " + node.getTag() + "   ");
           for (ResolveData h : node.getRefs()){
               System.out.print(h.getData());
           }
           if (node.getRefs().get(0) instanceof StaticData && node.getRefs().size() == 1){
               System.out.println("");
           }
           else System.out.println("    // " + ConstantNode.resolveNote(nodes, nodes.indexOf(node)));
       }
    }

}
