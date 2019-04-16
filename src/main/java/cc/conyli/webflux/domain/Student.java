package cc.conyli.webflux.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor(force = true)
@Document
public class Student implements Serializable {


    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Integer courseId;

}
