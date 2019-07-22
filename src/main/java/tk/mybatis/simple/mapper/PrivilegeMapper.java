package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysPrivilege;

import java.util.List;

/**
 * @author ASUS
 * @date 2019/7/21 15:33
 */
public interface PrivilegeMapper {
    List<SysPrivilege> selectPrivilegeByRoleId(Long roleId);
}
