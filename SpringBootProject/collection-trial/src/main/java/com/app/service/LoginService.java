package com.app.service;

import com.app.dto.UserDTO;
import com.app.entities.User;

public interface LoginService {
	Object authenticate(UserDTO user);

}
