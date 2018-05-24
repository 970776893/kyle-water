<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${resourceName}.dao.I${modelName}Dao">

    <resultMap id="${modelName}Map" type="${basePackage}.${resourceName}.entity.${modelName}Entity">
    <#list fieldList as field>
        <result column="${field.fieldDb}" property="${field.fieldJava}" jdbcType="${field.typeJdbc}"/>
    </#list>
    </resultMap>

    <sql id="orderById">
        order by id desc
    </sql>

    <sql id="pageParams">
        <#list fieldList as field>
        <if test="${field.fieldJava} != null">
            and t.${field.fieldDb} = ${r"#"}{${field.fieldJava},jdbcType=${field.typeJdbc}}
        </if>
         <#if (field.typeJdbc == "VARCHAR" && field.fieldJava != "createBy" && field.fieldJava != "createName" && field.fieldJava != "updateBy" && field.fieldJava != "updateName") >
         <if test="${field.fieldJava}Like != null">
                 and t.${field.fieldDb} like concat( '%', ${r"#"}{${field.fieldJava}Like,jdbcType=${field.typeJdbc}}, '%')
         </if>
         </#if>
        </#list>
        <if test="isDel == null">
            and t.is_del = 0
        </if>
    </sql>

    <insert id="insert" parameterType="${basePackage}.${resourceName}.entity.${modelName}Entity" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName} (
            ${r'<trim suffix="" suffixOverrides=",">'}
                <#list fieldList as field>
                    ${field.fieldDb},
                </#list>
            ${r'</trim>'}
        )
        values (
            ${r'<trim suffix="" suffixOverrides=",">'}
                <#list fieldList as field>
                ${r"#"}{${field.fieldJava}, jdbcType=${field.typeJdbc}},
                </#list>
            ${r'</trim>'}
        )
    </insert>

    <insert id="insertSelective" parameterType="${basePackage}.${resourceName}.entity.${modelName}Entity" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName} (
            ${r'<trim suffix="" suffixOverrides=",">'}
                <#list fieldList as field>
                <if test="${field.fieldJava} != null">
                    ${field.fieldDb},
                </if>
                </#list>
            ${r'</trim>'}
        )
        values (
        ${r'<trim suffix="" suffixOverrides=",">'}
            <#list fieldList as field>
            <if test="${field.fieldJava} != null">
                ${r"#"}{${field.fieldJava}, jdbcType=${field.typeJdbc}},
            </if>
            </#list>
        ${r'</trim>'}
        )
    </insert>


    <select id="selectById" parameterType="java.lang.Long" resultMap="${modelName}Map">
        SELECT t.*
        FROM ${tableName} t
        WHERE t.id = ${r"#"}{id}
    </select>

    <select id="countByParams" parameterType="java.util.HashMap" resultType="java.lang.Integer">
        SELECT count(*)
        FROM ${tableName} t
        WHERE 1=1
        ${r'<include refid="pageParams"></include>'}
    </select>

    <select id="listByParams" parameterType="java.util.HashMap" resultMap="${modelName}Map">
        SELECT  t.*
        FROM ${tableName} t
        WHERE 1=1
        ${r'<include refid="pageParams"></include>
        <include refid="orderById"></include>'}
        <if test="offset != null and size !=null">
            limit ${r"#"}{offset}, ${r"#"}{size}
        </if>
    </select>

    <update id="updateSelectiveById" parameterType="${basePackage}.${resourceName}.entity.${modelName}Entity">
        update ${tableName} t set
            ${r'<trim suffix="" suffixOverrides=",">'}
                <#list fieldList as field>
                <#if (field.fieldJava != "id") >
                <if test="${field.fieldJava} != null">
                t.${field.fieldDb} = ${r"#"}{${field.fieldJava},jdbcType=${field.typeJdbc}},
                </if>
                </#if>
                </#list>
            ${r'</trim>'}
        where t.id = ${r"#"}{id}
    </update>

    <update id="deleteById" parameterType="java.lang.Long">
        update ${tableName} t set
            t.is_del = 1
        where t.id = ${r"#"}{id}
    </update>

</mapper>