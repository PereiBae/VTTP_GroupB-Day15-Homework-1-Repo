package vttp.groupb.ssf.Day15Hw1Repo.models;

import jakarta.validation.constraints.NotBlank;

public class User {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public @NotBlank(message = "Name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
