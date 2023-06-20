package controller;

import entity.PhoneNumber;
import entity.User;

import java.util.List;
import java.util.Optional;

import repository.PhoneNumberRepository;
import repository.UserRepository;
import util.Randomizer;
import view.View;

public class Controller {
    private static final int GENERATED_PHONES_COUNT = 10;
    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final View view;

    public Controller(UserRepository userRepository,
                      PhoneNumberRepository phoneNumberRepository,
                      View view) {
        this.userRepository = userRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.view = view;
    }

    public void searchButtonClicked(String name) {
        Optional<User> user = userRepository.getByUsername(name);
        if (user.isEmpty()) {
            view.displayErrorMessageUserNotFound();
            return;
        }
        List<PhoneNumber> usersPhoneNumbers = phoneNumberRepository.getAllByUserId(user.get().getId());
        if (usersPhoneNumbers.isEmpty()) {
            view.displayErrorMessageNoMobileNumbers();
            return;
        }
        view.displayPhoneNumbers(usersPhoneNumbers);
        view.hideTriangle();
    }

    public void initializeData() {
        userRepository.add(new User("Ivan"));
        userRepository.add(new User("Petro"));
        userRepository.add(new User("Danil"));
        userRepository.add(new User("Alex"));
        List<User> users = userRepository.getAll();

        for (int i = 0; i < GENERATED_PHONES_COUNT; i++) {
            PhoneNumber generatedPhoneNumber
                    = phoneNumberRepository.add(new PhoneNumber(Randomizer.getRandomPhone()));
            phoneNumberRepository.linkPhoneNumberToUser(
                    generatedPhoneNumber,
                    users.get(Randomizer.nextInt(users.size())));
        }

        view.setUserTableModel(userRepository.getAll());
        view.setPhoneNumberTableModel(phoneNumberRepository.getAll());
    }
}