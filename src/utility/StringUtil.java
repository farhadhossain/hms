package utility;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by macintosh on 5/17/16.
 */
public class StringUtil {

    public static void removeNullFromObject(Object obj){

         try{

             for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors()) {
                if (pd.getPropertyType()==String.class && pd.getReadMethod() != null && (pd.getReadMethod().invoke(obj)==null ||  pd.getReadMethod().invoke(obj).equals("null")))
                        pd.getWriteMethod().invoke(obj,"");
            }
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (IntrospectionException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         }
    }
}
