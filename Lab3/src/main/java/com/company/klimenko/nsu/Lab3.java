package com.company.klimenko.nsu;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab3 {
    public static List<String> names = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        URL[] urls1 = new URL[]{file.toURI().toURL()};
        ClassLoader cl = new URLClassLoader(urls1);
        getClassNames(file, null);
        for (String s : names) {
            Class<?> aClass = cl.loadClass(s);
            try {

                Method method = aClass.getDeclaredMethod("getSecurityMessage");
                Object object = aClass.getConstructor().newInstance();
                method.setAccessible(true);
                System.out.println(aClass.getName());
                System.out.println(method.invoke(object));

            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {

            }
        }
    }

    public static void getClassNames(File dir, String parentName) throws Exception {
        if (dir == null || dir.listFiles() == null) {
            throw new Exception();
        }

        for (File f : dir.listFiles()) {
            if(!f.isDirectory()) {
                StringBuilder stringBuilder = new StringBuilder();
                List<String> list = Arrays.asList(f.getName().split("\\."));
                for (int i = 0; i < list.size() - 1 ; i++) {
                    stringBuilder.append(list.get(i));
                    if (i != list.size() - 2) {
                        stringBuilder.append(".");
                    }
                }
                if (parentName == null) {
                    names.add(stringBuilder.toString());
                } else {
                    names.add(parentName+ "."+ stringBuilder.toString());
                }
            }
            else {
                if (parentName == null) {
                    getClassNames(f, f.getName());
                } else {
                    getClassNames(f, parentName + "." + f.getName());
                }
            }
        }
    }
}
