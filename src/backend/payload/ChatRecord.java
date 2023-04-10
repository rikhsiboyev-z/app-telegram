package backend.payload;

import backend.model.User;

public record ChatRecord(String id, UserRecord firstSide, UserRecord secondSide) {

}
