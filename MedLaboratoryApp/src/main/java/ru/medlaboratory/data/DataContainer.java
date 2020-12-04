package ru.medlaboratory.data;

import ru.medlaboratory.data.entity.User;

public class DataContainer {
    public static User currentUser;
    public static final DBConnect dbConnect = new DBConnect();
    public static User selectedUser;
}
