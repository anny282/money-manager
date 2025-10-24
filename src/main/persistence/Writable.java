package persistence;

import org.json.JSONObject;


public interface Writable {

    JSONObject toJson();

    // EFFECTS: returns this as JSon object

}
