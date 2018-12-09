package com.home.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtils {

    
    public static <T> List<T> parserResultSet(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<T>();
        Field[] fields = clazz.getDeclaredFields();
        Field.setAccessible(fields, true);
        if(rs != null) {
            while(rs.next()){
                T t = clazz.newInstance();
                for(Field field : fields) {
                    String fieldname = field.getName().toLowerCase();
                    String fieldType = field.getGenericType().toString();
                    try {
                    	if(fieldType.equals("long")){
                    		field.setLong(t, rs.getLong(fieldname));
                    	} else if(fieldType.equals("int")){
                    		field.setInt(t, rs.getInt(fieldname));
                    	} else if(fieldType.equals("byte")){
                    		field.setByte(t, rs.getByte(fieldname));
                    	} else if(fieldType.equals("short")){
                    		field.setShort(t, rs.getShort(fieldname));
                    	} else if(fieldType.equals("float")){
                    		field.setFloat(t, rs.getFloat(fieldname));
                    	} else if(fieldType.equals("double")){
                    		field.setDouble(t, rs.getDouble(fieldname));
                    	} else if(fieldType.equals("boolean")){
                    		field.setBoolean(t, rs.getBoolean(fieldname));
                    	} else if(fieldType.equals("class java.lang.Integer")){
                    		field.set(t, rs.getInt(fieldname));
                    	} else if(fieldType.equals("class java.lang.Long")){
                    		field.set(t, rs.getLong(fieldname));
                    	} else if(fieldType.equals("class java.lang.Boolean")){
                    		field.set(t, rs.getBoolean(fieldname));
                    	} else if(fieldType.equals("class java.lang.Byte")){
                    		field.set(t, rs.getByte(fieldname));
                    	} else if(fieldType.equals("class java.lang.Double")){
                    		field.set(t, rs.getDouble(fieldname));
                    	} else if(fieldType.equals("class java.lang.Float")){
                    		field.set(t, rs.getFloat(fieldname));
                    	} else if(fieldType.equals("class java.lang.Short")){
                    		field.set(t, rs.getShort(fieldname));
                    	} else if(fieldType.equals("class java.lang.Character")){
                    		field.set(t, rs.getObject(fieldname));
                    	} else if(fieldType.equals("class java.lang.Number")){
                    		field.set(t, rs.getObject(fieldname));
                    	} else if(fieldType.equals("class java.lang.String")){
                    		if(null ==rs.getString(fieldname)){
                    			field.set(t, " ");
                    		}else{
                    			field.set(t, rs.getObject(fieldname));
                    		}                    		
                    	}
                    } catch (Exception e) {
                    }
                }
                result.add(t);
            }
        }
        return result;
    }

}
