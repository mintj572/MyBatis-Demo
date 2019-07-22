package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.EnabledTypeHandler;

import java.util.List;

/**
 * @author ASUS
 * @date 2019/7/20 12:03
 */
@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {
    /**
     * 根据角色id查询角色信息
     * @param id
     * @return
     */
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled", typeHandler = EnabledTypeHandler.class),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select({"select id, role_name, enabled, create_by, create_time from sys_role where id = #{id} "})
    SysRole selectById(Long id);

    SysRole selectRoleById(Long id);

    /**
     * 查询所有角色信息
     * @return
     */
    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    /***
     * 新增角色-不返回主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time)",
                "values (#{id}, #{roleName}, #{enabled, typeHandler = tk.mybatis.simple.type.EnabledTypeHandler},",
                        "#{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    /**
     * 新增角色-返回自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values (#{roleName}, #{enabled, typeHandler = tk.mybatis.simple.type.EnabledTypeHandler},",
            "#{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    /**
     * 新增角色-返回非自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values (#{roleName}, #{enabled, typeHandler = tk.mybatis.simple.type.EnabledTypeHandler},",
            "#{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "select last_insert_id()",
                keyProperty = "id", resultType = Long.class, before = false)
    int insert3(SysRole sysRole);

    List<SysRole> selectAllRoleAndPrivileges();

    List<SysRole> selectRoleByUserId(Long userId);

    List<SysRole> selectRoleByUserIdChoose(Long userId);

    int updateById(SysRole role);
}
