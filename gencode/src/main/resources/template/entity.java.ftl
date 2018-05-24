package ${basePackage}.${resourceName}.entity;

import com.kyle.framework.entity.BaseEntity;
import lombok.Data;
/**
 *
 *  Created by kyle.
 *
 */
@Data
public class ${modelName}Entity  extends BaseEntity {

<#list fieldList as field>
    <#if (field.fieldJava != "id" && field.fieldJava != "createAt" && field.fieldJava != "createBy" && field.fieldJava != "createName" && field.fieldJava != "updateAt" && field.fieldJava != "updateBy" && field.fieldJava != "updateName" && field.fieldJava != "isDel") >
    /**
    *
    * ${field.comment}
    */
    private ${field.typeJava} ${field.fieldJava};
    </#if>
</#list>

}
