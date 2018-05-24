package ${basePackage}.${resourceName}.service.impl;


import ${basePackage}.${resourceName}.entity.${modelName}Entity;
import ${basePackage}.${resourceName}.dao.I${modelName}Dao;
import ${basePackage}.${resourceName}.service.I${modelName}Service;
import com.zk.shopping.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ${modelName}ServiceImpl extends BaseServiceImpl<I${modelName}Dao, ${modelName}Entity> implements I${modelName}Service {

}
