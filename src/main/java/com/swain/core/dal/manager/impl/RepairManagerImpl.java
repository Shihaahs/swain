package com.swain.core.dal.manager.impl;

import com.swain.core.dal.domain.Repair;
import com.swain.core.dal.dao.RepairDao;
import com.swain.core.dal.manager.RepairManager;
import com.swain.core.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

@Component
public class RepairManagerImpl extends BaseManagerImpl<RepairDao, Repair> implements RepairManager{

}
