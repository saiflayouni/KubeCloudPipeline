package com.kubecloud.user;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public UserService() {
        users.put("1", new User("1", "Alice", "alice@example.com"));
        users.put("2", new User("2", "Bob", "bob@example.com"));
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public User get(String id) {
        return users.get(id);
    }

    public void add(User user) {
        users.put(user.id, user);
    }

    public void delete(String id) {
        users.remove(id);
    }
}
