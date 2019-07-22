package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @date 2019/7/19 18:35
 */
public interface UserMapper {
    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户id获取用户角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户-使用useGeneratedKeys方式
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户-使用selectKey方式
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteById(Long id);
    int deleteById(SysUser sysUser);

    /**
     * 根据id和角色的enabled状态获取用户的角色
     * @param id
     * @param enabled
     * @return
     */
    List<SysRole> selectRoleByUserIdAndRoleEnable(@Param("userId") Long id, @Param("enabled") Integer enabled);

    /**
     * 根据动态条件查询用户信息
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键更新
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 根据用户id或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * 批量插入用户信息
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 通过map更新列
     * @param map
     * @return
     */
    int updateByMap(@Param("map") Map<String, Object> map);

    /**
     * 根据用户id获取用户信息和用户的角色信息
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 查询所有的用户以及对应的所有角色
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
     * @param id
     * @return
     */
    SysUser selectUserAndRolesSelectById(Long id);
}
