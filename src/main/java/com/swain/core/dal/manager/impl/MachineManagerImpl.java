package com.swain.core.dal.manager.impl;

import com.swain.core.dal.domain.Machine;
import com.swain.core.dal.dao.MachineDao;
import com.swain.core.dal.manager.MachineManager;
import com.swain.core.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

@Component
public class MachineManagerImpl extends BaseManagerImpl<MachineDao, Machine> implements MachineManager{

}
