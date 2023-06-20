package repository;

import entity.PhoneNumber;
import entity.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneNumberRepository extends GenericRepository<PhoneNumber> {
    public PhoneNumber linkPhoneNumberToUser(PhoneNumber phoneNumber, User user) {
        phoneNumber.setUserId(user.getId());
        return update(phoneNumber);
    }

    public List<PhoneNumber> getAllByUserId(Long userId) {
        return storage.values().stream()
                .filter(pn -> Objects.equals(pn.getUserId(), userId))
                .collect(Collectors.toList());
    }
}
