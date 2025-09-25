package com.jamlech.blogbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Auditable {
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String content;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @Column(nullable = false,unique = true)
    private String slug;
    @Column(name = "featured_image_url")
    private String featuredImageUrl;
    @Enumerated(EnumType.STRING)
    private PostStatus status=PostStatus.DRAFT;
    @Column(name = "is_featured")
    private Boolean isFeatured=Boolean.FALSE;
    @Column(name = "view_count")
    private Long viewCount=0L;
    @Column(name = "like_count")
    private Long likeCount=0L;
    @Column(name = "comment_count")
    private Long commentCount=0L;
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",nullable = false)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags=new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PostLike> likes=new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PostView> views=new ArrayList<>();

}
