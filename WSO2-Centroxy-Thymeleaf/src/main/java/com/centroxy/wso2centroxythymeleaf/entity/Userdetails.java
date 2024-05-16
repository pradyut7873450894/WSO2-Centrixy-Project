package com.centroxy.wso2centroxythymeleaf.entity;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "userdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String mobile;
    private String password;
    private String role;

    @Column(columnDefinition = "BYTEA")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;





}
