package main.modelPackage;

public class MediaTypeModel {
    private int id;
    private String typeName;

    public MediaTypeModel(String typeName) {
        setId(id);
        setTypeName(typeName);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}


