package com.jamlech.blogbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends Auditable{
    @Column(name = "tag_name",nullable = false,unique = true)
    private String tagName;
    @Column(name = "tag_slug",nullable = false,unique = true)
    private String tagSlug;
    @Column(name = "tag_description",columnDefinition = "TEXT")
    private String tagDescription;
    @Column(name = "use_count")
    private Long useCount=0L;

    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts=new ArrayList<>();
    
}
