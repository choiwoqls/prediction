package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.Role;
import com.prediction.prediction.enumerations.UserRole;

public interface RoleService {

    Role findByName(UserRole name);

}
