package main.modelPackage;

public class MediaTypeModel {

    private int id;

    private String type_name;

    public MediaTypeModel( String type_name) {
        this.type_name = type_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}


