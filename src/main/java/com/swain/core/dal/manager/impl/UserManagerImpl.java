package com.swain.core.dal.manager.impl;

import com.swain.core.dal.domain.User;
import com.swain.core.dal.dao.UserDao;
import com.swain.core.dal.manager.UserManager;
import com.swain.core.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl extends BaseManagerImpl<UserDao, User> implements UserManager{

}
