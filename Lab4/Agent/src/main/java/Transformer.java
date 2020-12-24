import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    private final ClassPool classPool;
    private boolean isAdd = false;

    public Transformer() {
        classPool = ClassPool.getDefault();
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        try {
            CtClass cc = classPool.get("TransactionProcessor");

            if (!isAdd) {
                CtField minimalTime = new CtField(CtClass.doubleType, "minimalTime", cc);
                CtField maximalTime = new CtField(CtClass.doubleType, "maximalTime", cc);
                CtField averageTime = new CtField(CtClass.doubleType, "averageTime", cc);
                CtField c = new CtField(CtClass.intType, "c", cc);

                minimalTime.setModifiers(Modifier.STATIC);
                maximalTime.setModifiers(Modifier.STATIC);
                averageTime.setModifiers(Modifier.STATIC);
                c.setModifiers(Modifier.STATIC);

                cc.addField(minimalTime);
                cc.addField(maximalTime);
                cc.addField(averageTime);
                cc.addField(c);
                isAdded = true;

                CtMethod main = cc.getDeclaredMethod("main");
                main.insertBefore("minimalTime =  Double.MAX_VALUE;");
                main.insertBefore("maximalTime =  Double.MIN_VALUE;");
                main.insertBefore("averageTime =  0.0;");
                main.insertBefore("c =  0;");

                CtMethod processTransaction = cc.getDeclaredMethod("processTransaction");
                processTransaction.addLocalVariable("startTime", CtClass.longType);
                processTransaction.insertBefore("startTime = System.currentTimeMillis();");
                processTransaction.insertAfter("c = c + 1;");

                StringBuilder endBlock = new StringBuilder();

                processTransaction.addLocalVariable("endTime", CtClass.longType);
                processTransaction.addLocalVariable("opTime", CtClass.floatType);
                endBlock.append("endTime = System.currentTimeMillis();");
                endBlock.append("opTime = (float)(endTime-startTime)/1000.0;");
                endBlock.append("if (opTime < minimalTime) {minimalTime = opTime;}");
                endBlock.append("if (opTime > maximalTime) {maximalTime = opTime;}");
                endBlock.append("averageTime += opTime;");

                processTransaction.insertAfter(endBlock.toString());
                main.insertAfter("{ System.out.println( \"minimalTime:\" + minimalTime); }");
                main.insertAfter("{ System.out.println( \"maximalTime:\" + maximalTime); }");
                main.insertAfter("{ System.out.println( \"averageTime:\" + averageTime/ctField); }");
            }
            cc.instrument(
                    new ExprEditor() {
                        public void edit(MethodCall m)
                                throws CannotCompileException {
                            if (m.getMethodName().equals("processTransaction")) {
                                m.replace("{ $1 = $1 + 99; $_ = $proceed($$); }");
                            }
                        }
                    });

            cc.writeFile();

        } catch(NotFoundException | CannotCompileException | IOException e){
            System.out.println("class: " + className);
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}