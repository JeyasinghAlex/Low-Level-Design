package XMLParser;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppConfig {

    private final String active;
    private final String basePath;
    private final String shortDescription;
    private final String createdBy;
    private final DateTimeFormatter createdOn;
    private final String sysId;
    private final int modCount;
    private final String updatedBy;
    private final DateTimeFormatter updatedOn;
    private final String title;

    private final List<Content> contents;

    private AppConfig(Builder builder) {
        this.active = builder.active;
        this.basePath = builder.basePath;
        this.shortDescription = builder.shortDescription;
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.sysId = builder.sysId;
        this.modCount = builder.modCount;
        this.updatedBy = builder.updatedBy;
        this.updatedOn = builder.updatedOn;
        this.title = builder.title;
        this.contents = builder.contents;
    }

    // Getter methods for AppConfig fields...

    public static class Builder {
        private String active;
        private String basePath;
        private String shortDescription;
        private String createdBy;
        private DateTimeFormatter createdOn;
        private String sysId;
        private int modCount;
        private String updatedBy;
        private DateTimeFormatter updatedOn;
        private String title;

        public List<Content> contents;

        public Builder() {
            // Set default values if needed
        }

        public Builder active(String active) {
            this.active = active;
            return this;
        }

        public Builder basePath(String basePath) {
            this.basePath = basePath;
            return this;
        }

        public Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        // Add setter methods for other fields...

        public AppConfig build() {
            return new AppConfig(this);
        }
    }
}
