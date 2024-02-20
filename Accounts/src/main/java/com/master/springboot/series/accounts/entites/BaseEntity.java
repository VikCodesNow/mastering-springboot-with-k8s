package com.master.springboot.series.accounts.entites;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;



@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntity
{
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private String createdBy;
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    private String updatedBy;
}
