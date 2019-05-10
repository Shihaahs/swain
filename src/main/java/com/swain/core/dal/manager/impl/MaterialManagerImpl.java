package com.swain.core.dal.manager.impl;

import com.swain.core.dal.domain.Material;
import com.swain.core.dal.dao.MaterialDao;
import com.swain.core.dal.manager.MaterialManager;
import com.swain.core.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

@Component
public class MaterialManagerImpl extends BaseManagerImpl<MaterialDao, Material> implements MaterialManager{

}
