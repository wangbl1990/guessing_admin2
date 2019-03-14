package com.mifan.guessing.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;

/**
 * Description:
 * Created by guoxuechong on 2016/7/26.
 */
public class BeanMapper
{
    /**
     * 复制对象
     * @param source 源对象
     * @param destinationClass 目标对象类型
     * @param <T> 目标对象类型
     * @return  目标对象
     */
    public static <T> T map(Object source, Class<T> destinationClass)
    {
        return copyObject(source,destinationClass);
    }

    /**
     * 复制列表
     * @param sourceList
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass)
    {
        List destinationList = new ArrayList();
        if(null!=sourceList && sourceList.size()>0)
        {
            for (Object sourceObject: sourceList)
            {
                Object destinationObject = null;
                if(isEnumType(sourceObject.getClass(),destinationClass))
                {
                    destinationObject = mapEnum((Enum)sourceObject,(Class)destinationClass);
                }
                else
                {
                    destinationObject = map(sourceObject, destinationClass);
                }

                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }

    /**
     * 将对象转换为map
     * @param source
     * @return
     */
    public static Map<String, Object> toMap(Object source)
    {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(source.getClass());
        for (PropertyDescriptor pd : properties)
        {
            Method readMethod = pd.getReadMethod();
            Object value;
            try
            {
                value = readMethod.invoke(source);
            }
            catch (InvocationTargetException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
            map.put(pd.getName(),value);
        }
        return map;
    }

    /**
     * 复制对象，请使用map方法
     * @param source
     * @param destinationClass
     * @param <B>
     * @return
     */
    public static <B> B copyObject(Object source, Class<B> destinationClass)
    {
        B result;
        String methodName = "";
        try
        {
            result= destinationClass.newInstance();
            if(null != source)
            {
                //取得全部属性
                PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(destinationClass);
                for (PropertyDescriptor targetPd : properties)
                {
                    methodName = targetPd.getName();
                    Method writeMethod = targetPd.getWriteMethod();
                    if (writeMethod != null)
                    {
                        PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                        if (sourcePd != null)
                        {
                            Method readMethod = sourcePd.getReadMethod();
                            if (readMethod != null)
                            {
                                try
                                {
                                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
                                    {
                                        readMethod.setAccessible(true);
                                    }
                                    Object value = getTrueValue(readMethod, source, writeMethod);
                                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                                    {
                                        writeMethod.setAccessible(true);
                                    }
                                    if(null!=value)
                                    {
                                        writeMethod.invoke(result, value);
                                    }
                                }
                                catch (Throwable ex)
                                {
                                    throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (InstantiationException e)
        {
            throw new IllegalArgumentException(destinationClass.getName()+"."+methodName+"赋值失败",e);
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalArgumentException(destinationClass.getName()+"."+methodName+"赋值失败",e);
        }

        return result;
    }


    private static Object getTrueValue(Method getMethod,Object orgObj,Method setMethod)
    {
        Object orgResult = null;
        try {
            orgResult = getMethod.invoke(orgObj);
            if(null!=orgResult)
            {
                Class sourceType = getMethod.getReturnType();
                Class targetType = setMethod.getParameterTypes()[0];

                if(!sourceType.equals(targetType))
                {
                    //源为枚举，目标为String
                    if(sourceType.isEnum() && targetType.equals(String.class))
                    {
                        orgResult = orgResult.toString();
                    }
                    //源为String，目标为枚举
                    else if(targetType.isEnum() && sourceType.equals(String.class))
                    {
                        orgResult = createEnum(orgResult.toString(),targetType);
                    }
                    //源和目标都是枚举
                    else if(isEnumType(sourceType,targetType))
                    {
                        orgResult = mapEnum((Enum)orgResult,targetType);
                    }
                    else
                    {
                        // 有映射关系进行下一个层级的解析
                        orgResult = copyObject(orgResult,setMethod.getParameterTypes()[0]);
                    }
                }
                else if(sourceType.equals(List.class) && targetType.equals(List.class))  //如果都是列表
                {
                    Type sourceParamType = getMethod.getGenericReturnType();
                    Type targetParamType = setMethod.getGenericParameterTypes()[0]; //取得目标list中项目的类型
                    if(!sourceParamType.equals(targetParamType)) //如果源和目标类型不一致
                    {
                        if(targetParamType instanceof ParameterizedType)
                        {
                            ParameterizedType pt = (ParameterizedType) targetParamType;
                            Object genericClazz = pt.getActualTypeArguments()[0];
                            if(genericClazz instanceof Class)
                            {
                                orgResult = mapList((Collection) orgResult,(Class)genericClazz);
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(getMethod.getName()+" 取值方法调用失败");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(getMethod.getName()+" 取值方法调用失败");
        }

        return orgResult;
    }



    protected static <T extends Enum<T>> T createEnum(String source,Class<T> destFieldType)
    {
        return Enum.valueOf(destFieldType, source);
    }

    protected static  <T extends Enum<T>> T mapEnum(Enum<T> srcFieldValue, Class<T> destFieldType)
    {
        String name = srcFieldValue.name();
        T result = null;
        try
        {
            result = Enum.valueOf(destFieldType, name);
        }
        catch (Exception e)
        {
        }
        return result;
    }

    public static boolean isEnumType(Class<?> srcFieldClass, Class<?> destFieldType) {
        return isEnumType(srcFieldClass) && isEnumType(destFieldType);
    }

    public static boolean isEnumType(Class<?> srcFieldClass) {
        if(srcFieldClass.isAnonymousClass()) {
            srcFieldClass = srcFieldClass.getEnclosingClass();
        }
        return srcFieldClass.isEnum();
    }

    protected static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
