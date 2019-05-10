package com.swain.core.dal.manager.impl;

import com.swain.core.dal.domain.Product;
import com.swain.core.dal.dao.ProductDao;
import com.swain.core.dal.manager.ProductManager;
import com.swain.core.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

@Component
public class ProductManagerImpl extends BaseManagerImpl<ProductDao, Product> implements ProductManager{

}
