/*
        Written & Developed by KAJAL Kiran
*/

package core.api;

import core.utils.PropertyReaders;

import java.lang.reflect.Field;
import java.util.HashMap;

public class APIBase {
    Field header;
    Field path;
    Field pathParameter;
    HashMap<String,String> prop = PropertyReaders.getProperties();
    String className;
    RequestHandler requestHandler;

    public APIBase(String className){
        this.className = "activities." +className;
        System.out.println("Class Name : " +this.className);
        requestHandler = new RequestHandler();
    }


    private String getUrl(){
        String env = prop.get("env");
        if(env.equalsIgnoreCase("stage")){
            return prop.get("stageUrl");
        }else {
            return prop.get("prodUrl");
        }
    }

    private Class getRequestClass(){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isPathPresent(){
        try {
            path = getRequestClass().getDeclaredField("path");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isHeaderPresent(){
        try {
            header = getRequestClass().getDeclaredField("header");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isPathParamPresent(){
        try {
            pathParameter = getRequestClass().getDeclaredField("pathParameter");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isPathParameterised(){
        try {
            getRequestClass().getDeclaredMethod("getPathParameter");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /*
            API request body
    */
    public RequestHandler getRequest(){
        requestHandler.setEndPoint(getUrl());
        try {
            if(isPathPresent())
                requestHandler.setBasePath(path.get(getRequestClass()).toString());

            if(isHeaderPresent())
                requestHandler.setHeader(header.get(getRequestClass()).toString());

            if(isPathParamPresent() && !isPathParameterised())
                requestHandler.setPathParam(pathParameter.get(getRequestClass()).toString());

        } catch (Exception e){
            e.printStackTrace();
        }
        return requestHandler;
    }
}