package entity;

import java.util.List;

public record User(String username, List<String> phoneNumbers) {
}
