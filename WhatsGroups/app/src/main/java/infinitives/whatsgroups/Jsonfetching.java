
package infinitives.whatsgroups;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jsonfetching {

    @SerializedName("Sheet1")
    @Expose
    private List<Sheet1> sheet1 = null;

    public List<Sheet1> getSheet1() {
        return sheet1;
    }

    public void setSheet1(List<Sheet1> sheet1) {
        this.sheet1 = sheet1;
    }

}