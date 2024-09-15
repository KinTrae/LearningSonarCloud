package com.mas.kinga.services;

import com.mas.kinga.models.User;

public interface UserService {

    User getUser(long id);

    Boolean userExists(long id);
}
