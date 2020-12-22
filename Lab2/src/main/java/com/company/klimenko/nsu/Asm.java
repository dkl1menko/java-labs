package com.company.klimenko.nsu;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;

public class Asm {
    public static void main(String[] args) throws IOException {
        ASMifier.main(new String[]{GuessGame2.class.getName()});
    }
}
