package com.ra.session03_dm4.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;
    private Double price;
    private String description;
    private boolean status;
    private String image;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date(); // Đặt giá trị cho updatedAt khi tạo
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date(); // Cập nhật thời gian khi bản ghi được cập nhật
    }

}
