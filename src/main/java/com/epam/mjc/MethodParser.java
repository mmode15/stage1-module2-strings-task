package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String body = signatureString.substring(0, signatureString.indexOf("("));
        String parameters =  signatureString.substring( signatureString.indexOf("(")+1, signatureString.length()-1);
        List<String> list = new ArrayList<>();
        List<MethodSignature.Argument> params = new ArrayList<>();
        
        StringTokenizer bodyTokenizer = new StringTokenizer(body, " ");
        StringTokenizer paramTokenizer = new StringTokenizer(parameters, ", ");
        while (bodyTokenizer.hasMoreTokens()) {
            list.add(bodyTokenizer.nextToken());
        }
        while (paramTokenizer.hasMoreTokens()) {
            String argType = paramTokenizer.nextToken();
            String argName = paramTokenizer.nextToken();
            MethodSignature.Argument arg = new MethodSignature.Argument(argType, argName);
            params.add(arg);
        }

        MethodSignature methodSignature = new MethodSignature(list.get(list.size()-1), params);
        
        if(list.size() == 3){
            methodSignature.setAccessModifier(list.get(0));
            methodSignature.setReturnType(list.get(1));
        }else{
            methodSignature.setReturnType(list.get(0));
        }
        return methodSignature;
    }
}
