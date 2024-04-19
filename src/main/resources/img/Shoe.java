package com.fz.onlineshoestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Table(name = "Shoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String name;
    @Column
    String description;
    @Column
    Long price;
    @CreationTimestamp
    Date createdTime;
    @UpdateTimestamp
    Date updatedTime;
    @Transient
    @JsonIgnore
    MultipartFile photoFile;
    @Column
    String photoName;
}
