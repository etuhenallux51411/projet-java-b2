package main.modelPackage;

public class MediaModel {
    private Integer id;
    private String url;
    private DirectMessageModel directMessage;
    private MediaTypeModel format;

    public MediaModel(Integer id, String url, DirectMessageModel directMessage, MediaTypeModel format) {
        setId(id);
        setUrl(url);
        setDirectMessage(directMessage);
        setFormat(format);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
