package main.modelPackage;

public class MediaModel {
    private int id;

    private String url;

    private DirectMessageModel directMessage;

    private MediaTypeModel format;

    public MediaModel(String url, DirectMessageModel directMessage, MediaTypeModel format) {
        this.url = url;
        this.directMessage = directMessage;
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DirectMessageModel getDirectMessage() {
        return directMessage;
    }

    public void setDirectMessage(DirectMessageModel directMessage) {
        this.directMessage = directMessage;
    }

    public MediaTypeModel getFormat() {
        return format;
    }

    public void setFormat(MediaTypeModel format) {
        this.format = format;
    }



}
