package repository;

import entity.User;
import java.util.Objects;
import java.util.Optional;

public class UserRepository extends GenericRepository<User> {

    public Optional<User> getByUsername(String userName) {
        return storage.values().stream()
                .filter(u -> Objects.equals(u.getUserName(), userName))
                .findFirst();
    }
}
