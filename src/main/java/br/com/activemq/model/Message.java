package br.com.activemq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);

    public Message() {}

    public Message(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}