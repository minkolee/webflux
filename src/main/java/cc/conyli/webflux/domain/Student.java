package cc.conyli.webflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor(force = true)
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    //这里先不设置外键，否则JSON化之后会来回引用，无尽循环

    private Integer courseId;

}
