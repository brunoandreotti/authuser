package com.brunoandreotti.authuser.models;

import com.brunoandreotti.authuser.enums.UserStatus;
import com.brunoandreotti.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_USERS")
@Data
public class UserModel extends RepresentationModel<UserModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 256)
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserCourseModel> userCourses;

    public UserCourseModel toUserCourseModel (UUID courseId) {
        UserCourseModel userCourse = new UserCourseModel();
        userCourse.setCourseId(courseId);
        userCourse.setUser(this);

        return userCourse;
    }
}
