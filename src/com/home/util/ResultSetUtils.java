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
                        switch(fieldType) {
                        case "long":
                            field.setLong(t, rs.getLong(fieldname));
                            break;
                        case "int" :
                            field.setInt(t, rs.getInt(fieldname));
                            break;
                        case "byte" :
                            field.setByte(t, rs.getByte(fieldname));
                            break;
                        case "short":
                            field.setShort(t, rs.getShort(fieldname));
                            break;
                        case "float":
                            field.setFloat(t, rs.getFloat(fieldname));
                            break;
                        case "double":
                            field.setDouble(t, rs.getDouble(fieldname));
                            break;
                        case "boolean":                                
                            field.setBoolean(t, rs.getBoolean(fieldname));
                            break;
                        case "char":
                            break;
                        case "class java.lang.Integer":
                            field.set(t, rs.getInt(fieldname));
                            break;
                        case "class java.lang.Long":
                            field.set(t, rs.getLong(fieldname));
                            break;
                        case "class java.lang.Boolean":
                            field.set(t, rs.getBoolean(fieldname));
                            break;
                        case "class java.lang.Byte":
                            field.set(t, rs.getByte(fieldname));
                            break;
                        case "class java.lang.Double":
                            field.set(t, rs.getDouble(fieldname));
                            break;
                        case "class java.lang.Float":
                            field.set(t, rs.getFloat(fieldname));
                            break;
                        case "class java.lang.Short":
                            field.set(t, rs.getShort(fieldname));
                            break;
                        case "class java.lang.Character":
                        case "class java.lang.Number":
                            field.set(t, rs.getObject(fieldname));
                            break;
                        case "class java.lang.String":
                            field.set(t, rs.getString(fieldname));
                            break;
                        default:
                            field.set(t, rs.getObject(fieldname));
                            break;
                            
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
