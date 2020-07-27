package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor //Tự động tạo constructor ko tham số
@AllArgsConstructor //Tự động tạo constructor tất cả tham số
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String avatar;

    @Transient
    private MultipartFile image;

//    public Student() {
//    }

    public Student(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public Student(String name, MultipartFile image) {
        this.name = name;
        this.image = image;
    }
}
