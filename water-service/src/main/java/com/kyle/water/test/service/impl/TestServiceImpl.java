package com.kyle.water.test.service.impl;


import com.kyle.water.test.entity.TestEntity;
import com.kyle.water.test.dao.ITestDao;
import com.kyle.water.test.service.ITestService;
import com.kyle.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 *  Created by kyle.
 *
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<ITestDao, TestEntity> implements ITestService {

}
