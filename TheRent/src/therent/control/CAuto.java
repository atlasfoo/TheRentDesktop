package therent.control;

import therent.model.AutoModel;
import therent.model.beans.Auto;

import java.util.List;

public class CAuto {
    public static List<Auto> getAutos() throws Exception{
        return new AutoModel().getAllAutos();
    }

}
