package com.project.draggerlogin.login;

public class MemoryRepository implements LoginRepository {

    private User user;

    @Override
    public void saveUser(User user) {
        if (user == null) {
            user = getUser();
        }
        this.user = user;
    }

    @Override
    public User getUser() {
        if (user == null) {
            //user = new User("Antonio","Balderas");
            //user.setId(0);
            //return user;
        } else {
            return user;
        }
        return user;
    }
}
