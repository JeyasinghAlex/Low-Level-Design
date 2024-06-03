package XMLParser;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Content {

    private final String active;
    private final String content;
    private final String docResource;
    private final String minVersion;
    private final String requiredPlugin;
    private final String roles;
    private final String servicenowContent;
    private final String shortDescription;
    private final String state;
    private final String createdBy;
    private final DateTimeFormatter createdOn;
    private final String sysId;
    private final int modCount;
    private final String updatedBy;
    private final DateTimeFormatter updatedOn;
    private final String title;

    public List<Media> mediaList;

    private Content(Builder builder) {
        this.active = builder.active;
        this.content = builder.content;
        this.docResource = builder.docResource;
        this.minVersion = builder.minVersion;
        this.requiredPlugin = builder.requiredPlugin;
        this.roles = builder.roles;
        this.servicenowContent = builder.servicenowContent;
        this.shortDescription = builder.shortDescription;
        this.state = builder.state;
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.sysId = builder.sysId;
        this.modCount = builder.modCount;
        this.updatedBy = builder.updatedBy;
        this.updatedOn = builder.updatedOn;
        this.title = builder.title;
    }

    // Getter methods for Content fields...

    public static class Builder {
        private String active;
        private String content;
        private String docResource;
        private String minVersion;
        private String requiredPlugin;
        private String roles;
        private String servicenowContent;
        private String shortDescription;
        private String state;
        private String createdBy;
        private DateTimeFormatter createdOn;
        private String sysId;
        private int modCount;
        private String updatedBy;
        private DateTimeFormatter updatedOn;
        private String title;

        public Builder() {
            // Set default values if needed
        }

        public Builder active(String active) {
            this.active = active;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        // Add setter methods for other fields...

        public Content build() {
            return new Content(this);
        }
    }
}
