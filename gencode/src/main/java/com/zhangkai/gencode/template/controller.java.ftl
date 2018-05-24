package ${basePackage}.${resourceName}.controllor;

import com.zk.shopping.base.controllor.BaseControllor;
import ${basePackage}.${resourceName}.entity.${modelName}Entity;
import ${basePackage}.${resourceName}.dao.I${modelName}Dao;
import ${basePackage}.${resourceName}.service.I${modelName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${resourceName}")
public class ${modelName}Controllor extends BaseControllor<${modelName}Entity, I${modelName}Dao, I${modelName}Service> {


}
