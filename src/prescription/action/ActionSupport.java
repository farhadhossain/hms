package prescription.action;

import org.apache.struts2.components.Include;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import prescription.PrescriptionDAO;
//import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.io.IOException;

/**
 * Created by macintosh on 11/30/15.
 */
public class ActionSupport extends com.opensymphony.xwork2.ActionSupport {

    private String jsonString;

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public  <T> T getEntity(Class<T> genericType){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        T obj = null;
        try {
            obj = mapper.readValue(jsonString, genericType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
