package objectMensage;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int Id = 0;
    private String Name = "";
    public void setID(int i) {
        this.Id =i;
    }

    public void setName(String n) {
        this.Name = n;
    }
}
