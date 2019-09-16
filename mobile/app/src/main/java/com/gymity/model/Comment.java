package com.gymity.model;

public class Comment {
    private Long id;
    private String content;
    private Users user;
    private Gym gym;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getContent() {
        return content;
    }

    public Users getUser() {
        return user;
    }
}
