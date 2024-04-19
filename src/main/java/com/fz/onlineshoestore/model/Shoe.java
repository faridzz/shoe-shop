package com.fz.onlineshoestore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Long price;
    @CreationTimestamp
    private Date createdTime;
    @UpdateTimestamp
    private Date updatedTime;
    @Transient
    @JsonIgnore
    private MultipartFile photoFile;
    @Column
    private String photoName;
//    @ManyToMany(mappedBy = "shoes")
//    List<UserObj> userObjs;

}
