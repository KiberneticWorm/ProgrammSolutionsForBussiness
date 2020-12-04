package ru.medlaboratory.interfaces;

import ru.medlaboratory.data.entity.User;
import ru.medlaboratory.ui.Controller;

public interface SetUser extends Controller {
    void setUser(User user);
}

