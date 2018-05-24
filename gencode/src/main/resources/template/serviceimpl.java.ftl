package ${basePackage}.${resourceName}.service.impl;


import ${basePackage}.${resourceName}.entity.${modelName}Entity;
import ${basePackage}.${resourceName}.dao.I${modelName}Dao;
import ${basePackage}.${resourceName}.service.I${modelName}Service;
import com.kyle.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 *  Created by kyle.
 *
 */
@Service
public class ${modelName}ServiceImpl extends BaseServiceImpl<I${modelName}Dao, ${modelName}Entity> implements I${modelName}Service {

}
