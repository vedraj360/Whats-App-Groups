
package infinitives.whatsappgroups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sheet1 {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
