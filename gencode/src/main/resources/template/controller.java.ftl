package ${basePackage}.${resourceName}.controller;

import com.kyle.framework.controllor.IBaseControllor;
import ${basePackage}.${resourceName}.entity.${modelName}Entity;
import ${basePackage}.${resourceName}.dao.I${modelName}Dao;
import ${basePackage}.${resourceName}.service.I${modelName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  Created by kyle.
 *
 */
@Controller
@RequestMapping("/${resourceName}")
public class ${modelName}Controller extends IBaseControllor<${modelName}Entity, I${modelName}Dao, I${modelName}Service> {


}
