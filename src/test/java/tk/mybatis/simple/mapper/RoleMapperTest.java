package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.CreateInfo;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

import java.util.Date;
import java.util.List;

/**
 * @author ASUS
 * @date 2019/7/20 12:17
 */
public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1L);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAll();
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
            Assert.assertNotNull(roleList.get(0).getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRole role = new SysRole();
            role.setRoleName("test");
            role.setEnabled(Enabled.enabled);
            role.setCreateTime(new Date());
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            int result = roleMapper.insert(role);
            Assert.assertEquals(1, result);

            role.setId(null);
            result = roleMapper.insert2(role);
            Assert.assertEquals(1, result);
            System.out.println(role.getId());

            role.setId(null);
            result = roleMapper.insert3(role);
            Assert.assertEquals(1, result);
            System.out.println(role.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            for (SysRole role : roleList) {
                System.out.println("role = " + role.getRoleName());
                CreateInfo createInfo = role.getCreateInfo();
                System.out.println("createInfo.getCreateBy() + createInfo.getCreateTime() = "
                        + createInfo.getCreateBy() + " " + createInfo.getCreateTime());
                for (SysPrivilege privilege : role.getPrivilegeList()) {
                    System.out.println("privilege.getPrivilegeName() = " + privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRole r : roleList) {
                System.out.println("r.getRoleName() = " + r.getRoleName());
                if (r.getId().equals(1L)){
                    Assert.assertNotNull(r.getPrivilegeList());
                } else if (r.getId().equals(2L)){
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }
                for (SysPrivilege privilege : r.getPrivilegeList()) {
                    System.out.println("privilege.getPrivilegeName() = " + privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
